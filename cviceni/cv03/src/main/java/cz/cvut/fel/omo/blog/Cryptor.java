package cz.cvut.fel.omo.blog;



/**
 * @author Matej
 * @version 1.0
 * @created 13-шнj-2019 17:07:38
 */
public interface Cryptor {

	/**
	 * 
	 * @param plainPassword
	 */
	public String getPassword(String plainPassword);

	/**
	 * 
	 * @param plainPassword
	 * @param user
	 */
	public boolean checkPassword(String plainPassword, User user);

}