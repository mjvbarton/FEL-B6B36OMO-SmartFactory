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
public class ContentNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ContentNotFoundException</code> without
     * detail message.
     */
    public ContentNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ContentNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ContentNotFoundException(String msg) {
        super(msg);
    }
}
