/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.blog.exceptions;

/**
 *
 * @author Matej
 */
public class UnauthorizedException extends Exception {

    /**
     * Creates a new instance of <code>UnauthorizedException</code> without
     * detail message.
     */
    public UnauthorizedException() {
    }

    /**
     * Constructs an instance of <code>UnauthorizedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
