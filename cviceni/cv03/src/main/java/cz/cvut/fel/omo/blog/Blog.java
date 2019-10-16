package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException;
import cz.cvut.fel.omo.blog.exceptions.ExistingContentException;
import cz.cvut.fel.omo.blog.exceptions.UnauthenticatedException;
import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.blog.exceptions.UsernameExistsException;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 23:51:21
 */
public class Blog {

	private static Cryptor auth = new AuthBCrypt(); // Change this if you use different encryption.
        private final String blogName;
	private final List<Role> roles;	
        private final List<Permission> permissions;
        
        protected List<User> users;
        
        private final Dashboard dashboard;
        
        protected final List<Topic> topics = new ArrayList();
        protected final List<Article> articles = new ArrayList();
	
	public Blog(String blogName, List<Role> roles, List<Permission> permissions){
            this.blogName = blogName;
            this.roles = roles;
            this.permissions = permissions;
            this.dashboard = new Dashboard();            
            this.users = new ArrayList();                    
	}

        public static Cryptor getAuth() {
            return auth;
        }
        
        

        /**
         * 
         * @param permisssionName
         * @return 
         */
        private Permission getPermission(String permissionName){
            Permission perm = new Permission(permissionName);
            if(permissions.indexOf(perm) >= 0){
                return permissions.get(permissions.indexOf(perm));
            } else {
                throw new NullPointerException("Permission " + perm + " not exists.");
            }
        }
        
        /**
         * Checks if role names given are available in the blog
         * @param roles
         * @return 
         */
        private boolean checkRoles(String[] roles){
            for(String roleName : roles){
                Role role = new Role(roleName);
                if(this.roles.indexOf(role) < 0){
                    throw new NullPointerException("Role " + role + " not exists.");
                }                
            }
            return true;
        }
        
        /**
	 * Creates new user account for new blog
	 * @param username - unique username of new user
	 * @param password - password of new user
	 * @param roles - list of roles (role name) of current user
	 */
	public void createNewAccount(String username, String password, String[] roles) throws UsernameExistsException{
            User user = new User(username);
            checkRoles(roles);            
            if(users.contains(user)){
                throw new UsernameExistsException("User " + user + " alredady exists in this blog.");
            } else {
                user.setPassword(password);
                ArrayList<Role> userRoles = new ArrayList();
                for(String roleName : roles){
                    Role role = new Role(roleName);
                    userRoles.add(role);
                }
                user.setRoles(userRoles);
                users.add(user);
            }
	}

	/**
	 * 
	 * @param articleName
	 * @param articleContent
         * @param topicNames
         * @param activeUser
         * @return 
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException
         * @throws cz.cvut.fel.omo.blog.exceptions.ExistingContentException
	 */
	public Article createNewArticle(String articleName, String articleContent, String[] topicNames, User activeUser) throws UnauthorizedException, ExistingContentException, ContentNotFoundException{
            activeUser.authorize(getPermission("createNewArticle"));
            Article article = new Article(articleName, articleContent, activeUser, this);
            if(articles.contains(article)){
                throw new ExistingContentException("Article " + article + " already exists in this blog.");
            } else {
                articles.add(article);
                article.registerToTopic(topicNames);
                return article;
            }
	}

	/**
	 * 
	 * @param topicName - unique name of each topic
	 * @param topicDescription - description of the topic
         * @param activeUser
         * @return new created topic
         * @throws cz.cvut.fel.omo.blog.exceptions.ExistingContentException when entered existing topic name
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException when user has no rights to do 
	 */
	public Topic createNewTopic(String topicName, String topicDescription, Authorizable activeUser) throws ExistingContentException, UnauthorizedException{
            activeUser.authorize(getPermission("createNewTopic"));
            Topic topic = new Topic(topicName, topicDescription);
            if(topics.contains(topic)){
                throw new ExistingContentException("Topic " + topic + " already exists in this blog.");
            } else {
                topics.add(topic);
                return topic;
            }
	}

	public void displayTopics(){
            List<DashboardElement> toDisplay = new ArrayList();
            topics.forEach((topic) -> {
                toDisplay.add(topic);
            });
            dashboard.display(toDisplay, "Topics in blog: " + blogName);
	}

	/**
	 * 
	 * @param articleName
         * @return 
         * @throws cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException
	 */
	public Article findArticle(String articleName) throws ContentNotFoundException{
            Article article = new Article(articleName);
            int pointer = articles.indexOf(article);
            if(pointer < 0){
                throw new ContentNotFoundException("Article " + article + " not found in this blog.");
            } else {
                return articles.get(pointer);
            }
	}

	/**
	 * 
	 * @param topicName
         * @return 
         * @throws cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException
	 */
	public Topic findTopic(String topicName) throws ContentNotFoundException{           
            Topic topic = new Topic(topicName);
            int pointer = topics.indexOf(topic);
            if(pointer < 0){
                throw new ContentNotFoundException("Topic " + topic + " not found in this blog.");
            } else {
                return topics.get(pointer);
            }
	}

	/**
	 * Logs user into blog.
	 * @param username - unique name of each user
	 * @param password
         * @return true when authorized, exception when not authorized
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthenticatedException when non existing username or incorrect password given
	 */
	public User login(String username, String password) throws UnauthenticatedException{            
            User user = new User(username);
            int pointer = users.indexOf(user);
            if(pointer >= 0){
                if(auth.checkPassword(password, users.get(pointer))){
                    return users.get(pointer);
                } else {
                    throw new UnauthenticatedException("Wrong username or password.");
                }
            } else {
                    throw new UnauthenticatedException("Wrong username or password.");
            }            
	}
        
        /**
         * Displays all published articles within the blog
         * @param user 
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException 
         */
	public void readBlog(Authorizable user) throws UnauthorizedException{
            user.authorize(getPermission("readBlog"));
            List<DashboardElement> toDisplay = new ArrayList();
            articles.stream().filter((article) -> (article.getStatus() == ArticleStatus.PUBLISHED)).forEachOrdered((article) -> {
                toDisplay.add(article);
            });
            dashboard.display(toDisplay, blogName);
	}

	/**
	 * 
         * @param user
	 * @param topicName
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException
         * @throws cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException
	 */
	public void readBlog(Authorizable user, String topicName) throws UnauthorizedException, ContentNotFoundException{
            user.authorize(getPermission("readBlog"));
            List<DashboardElement> toDisplay = new ArrayList();
            try{
                Topic topic = findTopic(topicName);
                topic.getArticles().stream().filter((article) -> (article.getStatus() == ArticleStatus.PUBLISHED)).forEachOrdered((article) -> {
                    toDisplay.add(article);
                });
            } catch(ContentNotFoundException ex){
                System.err.println(ex);                
            }   finally{
                dashboard.display(toDisplay, topicName);
            }            
	}	
}