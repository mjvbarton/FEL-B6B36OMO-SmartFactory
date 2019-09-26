/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.omo.labs.hw.hw01;

/**
 *
 * @author Matej Barton (bartom47@fel.cvut.cz)
 */
public class Homework1 {
    private int countH;
    private static int countI = 0;
    
    public Homework1(){
        countH = 0;
    }
    
    public boolean f(){
        return true;
    }
    
    public boolean g(){
        return false;
    }
    
    public int h(){
        countH++;
        return countH;
    }
    
    public int i(){
        countI++;
        return countI;
    }
}
