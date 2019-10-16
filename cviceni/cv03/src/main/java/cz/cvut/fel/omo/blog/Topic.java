package cz.cvut.fel.omo.blog;


import cz.cvut.fel.omo.blog.exceptions.ExistingContentException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 17:07:27
 */
public class Topic implements DashboardElement, Serializable {

    private final List<Article> articles = new ArrayList();
    private String description;
    private String name;
	

    public String getName() {
        return name;
    }
        
    public Topic(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    public Topic(String name){
        this.name = name;
        this.description = null;
    }

    public boolean equals(Topic topic){
        return topic.getName().equals(name);
    }
    
    public List<Article> getArticles(){
        return articles;
    }
    
    public void addArticle(Article article) throws ExistingContentException{
        if(articles.contains(article)){
            throw new ExistingContentException("Article " + article + " exists");
        } else {
            articles.add(article);
        }        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void display() {
        System.out.println("<h3>" + name + "</h3>");
        System.out.println("<p class='text-muted'>" + description + "</p>");
    }
    
    private void display(ArticleStatus status){        
        articles.stream().filter((article) -> (article.getStatus() == status)).forEachOrdered((article) -> {
            article.display();
        });
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }    
}