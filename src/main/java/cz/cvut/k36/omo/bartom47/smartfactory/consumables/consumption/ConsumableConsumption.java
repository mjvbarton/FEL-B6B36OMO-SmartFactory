/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import java.util.Objects;

/**
 *
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumableConsumption extends ConsumptionData<Consumable>{
    private Double cost;
    
    private Integer consumed;
    
    public ConsumableConsumption() {
        super();
        cost = new Double(0);
        consumed = 0;        
    }               

    public void setConsumed(Integer consumed) {
        Objects.requireNonNull(consumed);
        this.consumed = consumed;
    }        
    
    public String getName(){
        return parentNode.getName();
    }

    public Double getCost() {
        return parentNode.getUnitCost() * consumed.doubleValue();
    }
    
    public Integer getConsumed() {
        return consumed;
    }
    
    public String getUnitName() {
        return parentNode.getUnitName();
    }
    
    public String getCurrency(){
        return parentNode.getUnitShortcutName();
    }                      
}
