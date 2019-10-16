package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.ContentNotFoundException;
import cz.cvut.fel.omo.blog.exceptions.ExistingContentException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 17:07:25
 */
public class Article implements DashboardElement, Editable {

    private User author;
    private String content;
    private Date lastChanged;
    private ArticleStatus status;
    private String title;
    private Blog blog;
    private final List<Topic> topics = new ArrayList();
	

    public Article(String title, String content, User author, Blog blog){
        this.author = author;
        this.title = title;
        this.content = content;
        this.lastChanged = new Date();
        this.blog = blog;
        this.status = ArticleStatus.WIP;
    }
    
    public Article(String title){
        this.author = null;
        this.blog = null;
        this.content = null;
        this.lastChanged = null;
        this.title = title;
        this.status = ArticleStatus.WIP;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }
        
    public void registerToTopic(Topic topic) throws ExistingContentException{
        topic.addArticle(this);
        topics.add(topic);
    }

    public void registerToTopic(Topic[] topics) throws ExistingContentException{
        for(Topic topic : topics){
            registerToTopic(topic);
        }
    }
    
    public void registerToTopic(String topicName) throws ContentNotFoundException, ExistingContentException{
        registerToTopic(blog.findTopic(topicName));
    }
    
    public void registerToTopic(String[] topicNames) throws ContentNotFoundException, ExistingContentException{
        for(String topicName : topicNames){
            registerToTopic(topicName);
        }
    }
    public String getTitle() {
        return title;
    }

    @Override
    public void display() {
        System.out.println("<h2>" + title + "</h2>");
        System.out.println("<p class='text-muted'>" + author + "</p>");
        System.out.println("<p>" + content + "</p>");
    }

    @Override
    public String toString() {
        return title;
    }
        
    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
    
    @Override
    public int hashCode() {
        return title.hashCode();
    }
    
    
    
    public void publish(){
        status = ArticleStatus.PUBLISHED;
    }
}