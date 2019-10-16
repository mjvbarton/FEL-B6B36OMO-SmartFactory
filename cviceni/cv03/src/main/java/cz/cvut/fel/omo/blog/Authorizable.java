/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.blog;

import cz.cvut.fel.omo.blog.exceptions.UnauthorizedException;

/**
 *
 * @author Matej
 */
public interface Authorizable {
    /**
     * Returns true if user has role of String name given
     * @param roleName - name of role
     * @return true if user is authorized
     * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException when authorization fails
     */    
    public boolean authorize(String roleName) throws UnauthorizedException;
    
    /**
     * Returns true if user has given role
     * @param role role in the blog
     * @return true if user is authorized
     * @throws cz.cvut.fel.omo.blog.exceptions.UnauthorizedException when authorization fails
     */
    public boolean authorize(Role role) throws UnauthorizedException;
    
    /**
     * Returns true if user is permitted according to the permission given.
     * @param permission
     * @return true if user is authorizeds
     * @throws UnauthorizedException 
     */
    public boolean authorize(Permission permission) throws UnauthorizedException;
}
