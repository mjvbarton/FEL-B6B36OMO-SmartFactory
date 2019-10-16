package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException;
import cz.cvut.fel.omo.blog.exceptions.ExistingContentException;
import cz.cvut.fel.omo.blog.exceptions.UnauthenticatedException;
import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.blog.exceptions.UsernameExistsException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws UsernameExistsException, UnauthenticatedException, ExistingContentException, UnauthorizedException, ContentNotFoundException {

        //1.1 CREATING NEW ADMIN AND USER ACCOUNT
        List<Role> roles = new ArrayList();
        Role adminRole = new Role("ADMIN");
        roles.add(adminRole);
        Role commonUserRole = new Role("COMMON_USER");
        roles.add(commonUserRole);
        
        List<Permission> permissions = new ArrayList();
        permissions.add(new Permission("createNewTopic", new Role[]{adminRole}));
        permissions.add(new Permission("createNewArticle", new Role[]{adminRole}));
        permissions.add(new Permission("readBlog", new Role[]{commonUserRole}));
        
        Blog blog = new Blog("My testing blog", roles, permissions);
        String username = "Tom_Hanks";
        String password = "password";        
        blog.createNewAccount(username, password, new String[]{"ADMIN"});
        blog.createNewAccount("Nicolas_Cage", "password", new String[]{"COMMON_USER"});
        
        // 1.2 LOGGING INTO THE ADMIN ACCOUNT
        User user = blog.login("Tom_Hanks", "password");
        
       
        // 1.3 CREATING NEW TOPICS
        blog.createNewTopic("AngularJS", "Typical issues encountered using AngularJS, also known as Angular1", user);
        blog.createNewTopic("React", "Unlocking hidden power of javascript.", user);
        blog.createNewTopic("FrontEnd development", "Various frameworks for frontend development.", user);

        blog.displayTopics();
        
        // 1.4 WRITING NEW POST
        String title;
        String content;
        
        title = "Why Angular JS";
        content = "HTML is great for declaring static documents, but it falters when we try to use it for declaring dynamic views in web-applications. AngularJS lets you extend HTML vocabulary for your application. The resulting environment is extraordinarily expressive, readable, and quick to develop.\n";
        Article angularArticle = blog.createNewArticle(title, content, new String[]{"AngularJS", "FrontEnd development"}, user);
        
        title = "Stairway to React";
        content = "React makes it painless to create interactive UIs. Design simple views for each state in your application, and React will efficiently update and render just the right components when your data changes.\n";
        Article reactArticle = blog.createNewArticle(title, content, new String[]{"React", "FrontEnd development"}, user);

        /*// 1.5 REGISTRING POSTS TO TOPICS
        Post angularPost = blog.findPost("Why Angular JS");
        Post reactPost = blog.findPost("Stairway to React");
        Topic angularTopic = blog.findTopic("AngularJS");
        Topic reactTopic = blog.findTopic("React");
        Topic frontEndTopic = blog.findTopic("FrontEnd development");

        angularPost.registerToTopic(angularTopic);
        angularPost.registerToTopic(frontEndTopic);
        reactPost.registerToTopic(reactTopic);
        reactPost.registerToTopic(frontEndTopic);*/
        
        
        // 1.6 PUBLISHING POST
        
        angularArticle.publish();
        reactArticle.publish();
        
       
        // 1.8 LOGGING INTO USER ACCOUNT
        User userAccount = blog.login("Nicolas_Cage", password);

        // 1.7 READING DIFFERENT SECTIONS OF BLOG
        blog.readBlog(userAccount);
        blog.readBlog(userAccount, "React");
        blog.readBlog(userAccount, "AngularJS");
        blog.readBlog(userAccount, "FrontEnd development");
        blog.readBlog(userAccount, "BackEnd");       
    }
}