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
public class NoAuthenticationNeededException extends Exception {

    /**
     * Creates a new instance of <code>NoAuthenticationNeededException</code>
     * without detail message.
     */
    public NoAuthenticationNeededException() {
    }

    /**
     * Constructs an instance of <code>NoAuthenticationNeededException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoAuthenticationNeededException(String msg) {
        super(msg);
    }
}
