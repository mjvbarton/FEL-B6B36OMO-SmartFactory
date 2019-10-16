/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.blog;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Matej
 */
public class AuthBCrypt implements Cryptor {

    @Override
    public String getPassword(String plainPassword) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainPassword, salt);
    }

    @Override
    public boolean checkPassword(String plainPassword, User user) {
        return BCrypt.checkpw(plainPassword, user.getPassword());
    }
    
}
