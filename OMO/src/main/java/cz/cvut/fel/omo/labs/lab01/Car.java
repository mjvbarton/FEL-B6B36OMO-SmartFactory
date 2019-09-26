/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.labs.lab01;

import java.util.ArrayList;

/**
 *
 * @author Matej
 */
public class Car {
    private int manufacturedYear;
    private String color;
    private ArrayList<Wheel> wheels;
    
    public Car(int countWheels, int wheelRadius){
        for(int i = 0; i < countWheels; i++){
            wheels.add(new Wheel(wheelRadius));
        }
    }

    @Override
    public String toString() {
        return "Car{" + "manufacturedYear=" + manufacturedYear + ", color=" + color + 
                ", countWheels=" + wheels.size() + ", wheelInfo=" + wheels.get(0).toString() + '}';
    }
}
