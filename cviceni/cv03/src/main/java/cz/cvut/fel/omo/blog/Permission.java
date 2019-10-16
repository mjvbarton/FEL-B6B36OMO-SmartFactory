package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 17:07:23
 */
public class Permission implements Serializable{

	private final String name;
	private final List<Role> roles;
        
        public Permission(String name, Role[] roles){
            ArrayList<Role> rolesToList = new ArrayList();
            for(Role role : roles){
                rolesToList.add(role);
            }
            this.name = name;
            this.roles = rolesToList;
        }
        
	public Permission(String name, List<Role> roles){
            this.name = name;
            this.roles = roles;
	}
        
        public Permission(String name, String[] roleNames){
            roles = new ArrayList();
            this.name = name;
            for(String roleName : roleNames){
                roles.add(new Role(roleName));
            }
        }

    public Permission(String permissionName) {
        this.name = permissionName;
        roles = null;
    }

        public String getName() {
            return name;
        }
                
        public List<Role> getRoles() {
            return roles;
        }
        
        
	/**
	 * 
         * @param roles
     * @return 
         * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException
	 */
	public boolean authorize(List<Role> roles) throws UnauthorizedException{
            boolean contained = false;
            for(Role role : roles){
                contained = this.roles.contains(role);
                if(contained){
                    return true;                    
                }
            }
            if(contained == false){
                throw new UnauthorizedException("Unauthorized access to " + this + " for active user.");
            }
            return false;
	}

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
        
}