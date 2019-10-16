/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException;
import cz.cvut.fel.omo.blog.exceptions.ExistingContentException;
import cz.cvut.fel.omo.blog.exceptions.UnauthenticatedException;
import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;
import cz.cvut.fel.omo.blog.exceptions.UsernameExistsException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Matej
 */
public class BlogTest {
    private class TestBlog extends Blog{

        public TestBlog(String blogName, List<Role> roles, List<Permission> permissions) {
            super(blogName, roles, permissions);
        }
        
        List<User> getUsers(){
            return super.users;
        }
        
    }
    
    private TestBlog instance;
    
    private static final List<Role> SAMPLE_ROLES = new ArrayList();
    private static final List<Permission> SAMPLE_PERMISSIONS = new ArrayList();
                
    private static String sampleBlogName;
    
    public BlogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {       
        Role adminRole = new Role("ADMIN");
        SAMPLE_ROLES.add(adminRole);
        Role commonUserRole = new Role("COMMON_USER");
        SAMPLE_ROLES.add(commonUserRole);
        
        SAMPLE_PERMISSIONS.add(new Permission("createNewTopic", new Role[]{adminRole}));
        SAMPLE_PERMISSIONS.add(new Permission("createNewArticle", new Role[]{adminRole}));
        SAMPLE_PERMISSIONS.add(new Permission("readBlog", new Role[]{commonUserRole}));
        
        sampleBlogName = "BlogTest Blog";    
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TestBlog(sampleBlogName, SAMPLE_ROLES, SAMPLE_PERMISSIONS);
        
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getAuth method, of class Blog.
     */
    @Test
    public void testGetAuth() {
        System.out.println("testGetAuth");
        Cryptor result = Blog.getAuth();
        assertTrue(result instanceof Cryptor);
    }

    /**
     * Test of createNewAccount method, of class Blog with valid creation of user.
     * @throws java.lang.Exception if any problem occured during testing
     */
    @Test
    public void testCreateNewAccount_NonExistingUsername_userCreatedInBlog() throws Exception {
        System.out.println("testCreateNewAccount_NonExistingUsername_userCreatedInBlog");
        String username = "user1";
        String password = "password";
        String[] roles = new String[]{"COMMON_USER"};
        
        User user = new User(username);
        instance.createNewAccount(username, password, roles);
        assertTrue(instance.getUsers().contains(user));        
    }

    /**
     * Test of createNewAccount method, of class Blog with valid creation of user.
     * @throws java.lang.Exception if any problem occured during testing
     */
    @Test
    public void testCreateNewAccount_ExistingUsername_UsernameExistsExceptionThrown() throws Exception {
        System.out.println("testCreateNewAccount_ExistingUsername_UsernameExistsExceptionThrown");
        String username = "user1";
        String password = "password";
        String[] roles = new String[]{"COMMON_USER"};
        
        User user = new User(username);
        instance.users.add(user);
        try{
            instance.createNewAccount(username, password, roles);
            fail("Blog should not contain two or more users with the same username.");
        } catch(UsernameExistsException ex){
            assertTrue("Exception " + ex + " thrown", true);
        }             
    }
    /**
     * Test of createNewArticle method, of class Blog.
     */
    @Test
    public void testCreateNewArticle_adminUser_existingTopic_nonExistingArticle_articleCreatedAndStored() throws Exception {
        System.out.println("testCreateNewArticle_adminUser_nonExistingArticle_articleCreatedAndStored");
        String articleName = "Article1";
        String articleContent = "Lorem ipsum dolor sit amet";
        String[] topicNames = new String[]{"Lorem"};
        User activeUser = new User("Admin");
        activeUser.setRoles(SAMPLE_ROLES);
        instance.topics.add(new Topic("Lorem"));
        instance.createNewArticle(articleName, articleContent, topicNames, activeUser);
        Article article = new Article(articleName);
        boolean test = instance.articles.contains(article);
        assertTrue("Searching article in blog.articles", instance.articles.contains(article));        
    }
    
    @Test
    public void testCreateNewArticle_commonUser_existingTopic_nonExistingArticle_UnauthorizedExceptionThrown() throws Exception {
        System.out.println("testCreateNewArticle_commonUser_existingTopic_nonExistingArticle_UnauthorizedExceptionThrown");
        String articleName = "Article1";
        String articleContent = "Lorem ipsum dolor sit amet";
        String[] topicNames = new String[]{"Lorem"};
        User activeUser = new User("NonAdmin");
        activeUser.setRoles(new ArrayList());
        activeUser.getRoles().add(new Role("COMMON_USER"));
        instance.topics.add(new Topic("Lorem"));
        
        try{
            instance.createNewArticle(articleName, articleContent, topicNames, activeUser);            
            fail("Common user should not have permission to create articles.");
        } catch(UnauthorizedException ex){
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }

    @Test
    public void testCreateNewArticle_adminUser_nonExistingTopic_nonExistingArticle_ContentNotFoundExceptionThrown() throws Exception {
        System.out.println("testCreateNewArticle_adminUser_nonExistingTopic_nonExistingArticle_ContentNotFoundExceptionThrown");
        String articleName = "Article1";
        String articleContent = "Lorem ipsum dolor sit amet";
        String[] topicNames = new String[]{"Lorem"};
        User activeUser = new User("Admin");
        activeUser.setRoles(SAMPLE_ROLES);        
        
        try{
            instance.createNewArticle(articleName, articleContent, topicNames, activeUser);            
            fail("Article should not be added to non existing topic.");
        } catch(ContentNotFoundException ex){
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }
    
    @Test
    public void testCreateNewArticle_adminUser_existingTopic_existingArticle_ExistingContentExceptionThrown() throws Exception {
        System.out.println("testCreateNewArticle_adminUser_nonExistingTopic_nonExistingArticle_ContentNotFoundExceptionThrown");
        String articleName = "Article1";
        String articleContent = "Lorem ipsum dolor sit amet";
        String[] topicNames = new String[]{"Lorem"};
        User activeUser = new User("Admin");
        activeUser.setRoles(SAMPLE_ROLES);
        instance.articles.add(new Article("Article1"));
        
        try{
            instance.createNewArticle(articleName, articleContent, topicNames, activeUser);            
            fail("Article should not be added to non existing topic.");
        } catch(ExistingContentException ex){
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }
    
    /**
     * Test of createNewTopic method, of class Blog.
     */
    @Test
    public void testCreateNewTopic_nonExistingTopic_adminUser_topicCreated() throws Exception {
        System.out.println("testCreateNewTopic_nonExistingTopic_adminUser_topicCreated");
        String topicName = "Topic1";
        String topicDescription = "Lorem ispum";
        User activeUser = new User("Admin");
        activeUser.setRoles(SAMPLE_ROLES);
        Topic newTopic = new Topic(topicName);
        instance.createNewTopic(topicName, topicDescription, activeUser);
        assertTrue("Searching topic " + newTopic + " in the blog:", instance.topics.contains(newTopic));
    }
    
    /**
     * Test of createNewTopic method, of class Blog.
     */
    @Test
    public void testCreateNewTopic_existingTopic_adminUser_ExistingContentExceptionThrown() throws Exception {
        System.out.println("testCreateNewTopic_existingTopic_adminUser_ExistingContentExceptionThrown");
        String topicName = "Topic1";
        String topicDescription = "Lorem ispum";
        User activeUser = new User("Admin");
        activeUser.setRoles(SAMPLE_ROLES);
        Topic newTopic = new Topic(topicName);
        instance.topics.add(newTopic);
        try{
            instance.createNewTopic(topicName, topicDescription, activeUser);
            fail("Blog should not accept existing topic.");
        } catch(ExistingContentException ex){
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }
    
    /**
     * Test of createNewTopic method, of class Blog.
     */
    @Test
    public void testCreateNewTopic_nonExistingTopic_commonUser_UnauthorizedExceptionThrown() throws Exception {
        System.out.println("testCreateNewTopic_existingTopic_adminUser_ExistingContentExceptionThrown");
        String topicName = "Topic1";
        String topicDescription = "Lorem ispum";
        User activeUser = new User("NonAdmin");
        activeUser.setRoles(new ArrayList());
        activeUser.getRoles().add(new Role("COMMON_USER"));        
        try{
            instance.createNewTopic(topicName, topicDescription, activeUser);
            fail("Non-admin user should not be able to create new topic.");
        } catch(UnauthorizedException ex){
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }
    
    
    /**
     * Removed from tests
     */
    @Test   
    public void testDisplayTopics_blogWithTopics_somethingDisplayed() throws Exception{
        System.out.println("testDisplayTopics_blogWithTopics_somethingDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));
        
        loadBlogWithContents();
        
        try{
            instance.displayTopics();
            Assert.assertNotEquals(newOut.toString("UTF-8"), "There is no content to display.\r\n");
        } finally {
            System.setOut(oldOut);
        }
    }
    
    @Test   
    public void testDisplayTopics_blogWithNoTopics_nothingNoteDisplayed() throws Exception{
        System.out.println("testDisplayTopics_blogWithNoTopics_nothingNoteDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));               
        
        try{
            instance.displayTopics();
            Assert.assertEquals("There is no content to display.\r\n", newOut.toString("UTF-8"));
        } finally {
            System.setOut(oldOut);
        }
    }

    /**
     * Test of findArticle method, of class Blog.
     */
    @Test
    public void testFindArticle_existingArticle_articleFound() throws Exception {
        System.out.println("testFindArticle_existingArticle_articleFound");
        String articleName = "article1";
        Article article = new Article(articleName);
        instance.articles.add(article);
        Article foundArticle = instance.findArticle(articleName);        
        assertEquals(article, foundArticle);                        
    }

    @Test
    public void testFindArticle_nonExistingArticle_ContentNotFoundExceptionThrown() throws Exception {
        System.out.println("testFindArticle_nonExistingArticle_ContentNotFoundExceptionThrown");
        String articleName = "article1";
        Article article = new Article(articleName);        
        try{
            instance.findArticle(articleName);
            fail("Not allowed to find non-existing article in the blog.");
        } catch (ContentNotFoundException ex) {
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }
    
    /**
     * Test of findArticle method, of class Blog.
     */
    @Test
    public void testFindTopic_existingTopic_topicFound() throws Exception {
        System.out.println("testFindTopic_existingTopic_topicFound");
        String topicName = "topic1";
        Topic topic = new Topic(topicName);
        instance.topics.add(topic);
        Topic foundArticle = instance.findTopic(topicName);        
        assertEquals(topic, foundArticle);                        
    }

    @Test
    public void testFindTopic_nonExistingTopic_ContentNotFoundExceptionThrown() throws Exception {
        System.out.println("testFindTopic_nonExistingTopic_ContentNotFoundExceptionThrown");
        String topicName = "topic1";
        Topic topic = new Topic(topicName);        
        try{
            instance.findTopic(topicName);
            fail("Not allowed to find non-existing topic in the blog.");
        } catch (ContentNotFoundException ex) {
            assertTrue("Exception " + ex + " thrown.", true);
        }
    }

    /**
     * Test of login method, of class Blog.
     */
    @Test
    public void testLogin_validUsername_validPassword_userInstanceReturned() throws Exception {
        System.out.println("testLogin_validUsername_validPassword_userInstanceReturned");
        String username = "testUser";
        String password = "password";
        
        User expectedResult = new User(username, password, SAMPLE_ROLES);
        instance.users.add(expectedResult);
        User result = instance.login(username, password);
        
        assertEquals(expectedResult, result);        
    }

    /**
     * Test of login method, of class Blog.
     */
    @Test
    public void testLogin_invalidUsername_validPassword_UnauthenticatedExceptionThrown() throws Exception {
        System.out.println("testLogin_invalidUsername_validPassword_UnauthenticatedExceptionThrown");
        String username = "testUser";
        String password = "password";
        
        try{
            instance.login(username, password);
            fail("Blog should not allow login to non-existing user.");
        } catch (UnauthenticatedException ex){
            assertTrue("Exception " + ex + " thrown.", ex instanceof UnauthenticatedException);
        }
    }
    
    /**
     * Test of login method, of class Blog.
     */
    @Test
    public void testLogin_validUsername_invalidPassword_UnauthenticatedExceptionThrown() throws Exception {
        System.out.println("testLogin_invalidUsername_validPassword_UnauthenticatedExceptionThrown");
        String username = "testUser";
        String validPassword = "password";
        String invalidPassword = "non-password";
        
        User user = new User(username, validPassword, SAMPLE_ROLES);
        instance.users.add(user);
        try{
            instance.login(username, invalidPassword);
            fail("Blog should not allow login to existing user with incorrect password.");
        } catch (UnauthenticatedException ex){
            assertTrue("Exception " + ex + " thrown.", ex instanceof UnauthenticatedException);
        }
    }  
    
    private void loadBlogWithContents(){
        // Preparing blog with some contents
        Topic topic1 = new Topic("Topic1", "Lorem");
        Topic topic2 = new Topic("Topic2", "Ipsum");
        Topic emptyTopic = new Topic("emptyTopic", "Dolor");
        Article article1 = new Article("Article1", "Lorem ipsum dolor sit amet...", new User("author"), instance);
        Article article12 = new Article("Article12", "Lorem ipsum dolor sit amet...", new User("author"), instance);
        article1.publish();
        article12.publish();
        topic1.getArticles().add(article1);
        topic1.getArticles().add(article12);
        topic2.getArticles().add(article12);
        instance.topics.add(topic1);
        instance.topics.add(topic2);
        instance.topics.add(emptyTopic);
        instance.articles.add(article1);
        instance.articles.add(article12);
    }
    
    /**
     * Test of readBlog method, of class Blog.
     */
    
    @Test
    public void testReadBlog_validUser_somethingDisplayed() throws Exception {
        System.out.println("testReadBlog_validUser_somethingDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));
        
        // Creating user in the blog
        User user = new User("user", "password", SAMPLE_ROLES);
        instance.users.add(user);
        loadBlogWithContents();
        
        try{
            instance.readBlog(user);           
            Assert.assertNotEquals(newOut.toString("UTF-8"), "There is no content to display.\r\n");
        } finally {
            System.setOut(oldOut);
        }        
    }
    
    /**
     * Test of readBlog method, of class Blog.
     */
    @Test
    public void testReadBlog_invalidUser_UnauthorizedExceptionThrown() throws Exception {
        System.out.println("testReadBlog_invalidUser_UnauthorizedExceptionThrown");
                       
        // Creating user in the blog
        User user = new User("nonValidUser", "password", new ArrayList());
        user.getRoles().add(new Role("ADMIN"));
        instance.users.add(user);
        
        try{
            instance.readBlog(user);
            fail("Non valid user should not be able to read blog.");
        } catch(UnauthorizedException ex) {
            assertTrue("Exception " + ex + " thrown.", true);
        }        
    }
    
    /**
     * Test of readBlog method, of class Blog.
     */
    @Test
    public void testReadBlogWithTopic_validUser_existingTopic_topicHasArticles_somethingDisplayed() throws Exception {
        System.out.println("testReadBlogWithTopic_validUser_existingTopic_topicHasArticles_somethingDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));
        
        // Creating user in the blog
        User user = new User("user", "password", SAMPLE_ROLES);
        instance.users.add(user);
        
        // Preparing topic in blog
        loadBlogWithContents();
                
        try{
            instance.readBlog(user, "Topic1");
            Assert.assertNotEquals(newOut.toString("UTF-8"), "There is no content to display.\r\n");
        } finally {
            System.setOut(oldOut);
        }        
    }
    
    /**
     * Test of readBlog method, of class Blog.
     */
    @Test
    public void testReadBlogWithTopic_invalidUser_existingTopic_topicHasArticles_UnauthorizedExceptionThrown() throws Exception {
        System.out.println("testReadBlogWithTopic_invalidUser_existingTopic_topicHasArticles_UnauthorizedExceptionThrown");
                        
        // Creating user in the blog
        User user = new User("user", "password", new ArrayList());
        user.getRoles().add(new Role("ADMIN"));
        instance.users.add(user);
        
        // Preparing topic in blog
        loadBlogWithContents();
                
        try{
            instance.readBlog(user, "Topic1");
            fail("Non-valid user should not be able to read blog.");
        } catch(UnauthorizedException ex) {
            assertTrue("Exception " + ex + " thrown.", true);
        }        
    }
    
    /**
     * Test of readBlog method, of class Blog.
     */
    @Test
    public void testReadBlogWithTopic_validUser_nonExistingTopic_topicHasArticles_nothingNoteDisplayed() throws Exception {
        System.out.println("testReadBlogWithTopic_validUser_nonExistingTopic_topicHasArticles_nothingNoteDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));
        
        // Creating user in the blog
        User user = new User("user", "password", SAMPLE_ROLES);
        instance.users.add(user);
        
        // Preparing topic in blog
        loadBlogWithContents();
                
        try{
            instance.readBlog(user, "Non-existing topic");
            Assert.assertEquals("There is no content to display.\r\n", newOut.toString("UTF-8"));
        } finally {
            System.setOut(oldOut);
        }        
    }
    
     /**
     * Test of readBlog method, of class Blog.
     */
    @Test
    public void testReadBlogWithTopic_validUser_existingTopic_topicHasNoArticles_nothingNoteDisplayed() throws Exception {
        System.out.println("testReadBlogWithTopic_validUser_existingTopic_topicHasNoArticles_nothingNoteDisplayed");
        
        // Redirecting system out
        PrintStream oldOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();        
        System.setOut(new PrintStream(newOut));
        
        // Creating user in the blog
        User user = new User("user", "password", SAMPLE_ROLES);
        instance.users.add(user);
        
        // Preparing topic in blog
        loadBlogWithContents();
                
        try{
            instance.readBlog(user, "emptyTopic");
            Assert.assertEquals("There is no content to display.\r\n", newOut.toString("UTF-8"));
        } finally {
            System.setOut(oldOut);
        }        
    }
}
