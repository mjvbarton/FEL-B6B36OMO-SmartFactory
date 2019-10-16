package cz.cvut.fel.omo.blog;


import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;
import java.io.Serializable;
import java.util.List;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 17:07:17
 */
public final class User implements Authorizable, Serializable{

    private int id = 0; // Prepared field for database connection, currently unused
    private boolean isActive = true;
    private String password;
    private String username;
    private List<Role> roles;

    public User(String username, String plainPassword, List<Role> roles){
        setUsername(username);
        setPassword(plainPassword);
        setRoles(roles);
    }
    
    public User(String username){
        setUsername(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String plainPassword) {
        this.password = Blog.getAuth().getPassword(plainPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    /**
     * TODO: Change this for ID implementation when connecting to database !!!
     * @param user
     * @return 
     */
    public boolean equals(User user){
        return user.getUsername().equals(username);
    }

    @Override
    @Deprecated
    public boolean authorize(String roleName) throws UnauthorizedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Deprecated
    public boolean authorize(Role role) throws UnauthorizedException {
        return true;
    }

    @Override
    public boolean authorize(Permission permission) throws UnauthorizedException {
        return permission.authorize(roles);
    }

    @Override
    public String toString() {
        return username;
    }
    
    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}