package cz.cvut.fel.omo.blog;



/**
 * @author Matej
 * @version 1.0
 * @created 13-шнj-2019 17:07:35
 */
public class Comment {

	private Article article;
	private User author;
	private String content;
	private int id;
	public User m_User;
	public Article m_Article;

	public Comment(){

	}

	public void finalize() throws Throwable {

	}

}