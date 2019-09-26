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
public class Factory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    public static Car vytvorNakladni(){
        return new Car(6, 50);
    }
    
    public static Car vytvorOsobni(){
        return new Car(4, 17);
    }
    
}
