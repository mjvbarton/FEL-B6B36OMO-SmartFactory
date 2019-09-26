/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.labs.lab01;

/**
 *
 * @author Matej
 */
public class Wheel {
    private int radius;
    public Wheel(int radius){
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Wheel{" + "radius=" + radius + '}';
    }
}
