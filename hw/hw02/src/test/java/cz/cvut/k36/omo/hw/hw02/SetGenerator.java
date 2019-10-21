/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.hw.hw02;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Matej
 */
public class SetGenerator {
    public SetGenerator(){
        setNatural = new HashSet();
        setEven = new HashSet();
        for(int i = 1; i <= 20; i++){
            setNatural.add(i);
            if(i % 2 == 0){
                setEven.add(i);
            }
        }
    };
    
    private final HashSet<Integer> setNatural;
    private final HashSet<Integer> setEven;
   
    public OMOSetBase getOMOSetNatural() {       
        return buildOMOSet(setNatural);
    }

    public OMOSetBase getOMOSetEven() {      
        return buildOMOSet(setEven);
    }
    
    public static OMOSetBase buildOMOSet(HashSet<Integer> rawSet){
        OMOSetBase result = new OMOSet();
        rawSet.forEach((element) -> {
            result.add(element);
        });
        return result;
    }
}
