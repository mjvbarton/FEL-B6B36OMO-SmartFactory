package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;
import cz.cvut.k36.omo.bartom47.smartfactory.exceptions.ConsumptionDataException;
import java.util.HashMap;

/**
 * Represents updatable consumption data.
 * @author Matej
 * @param <E> data type of consum
 */
public abstract class UpdatableConsumptionData<E extends Consument> extends ConsumptionData<Consument>{
    
    protected final HashMap<Consumable, Integer> consumedUnits;
    
    /**
     * Creates new UpdatableConsumptionData node with consumer parent node given.    * 
     * @param parentNode consumer parent node
     */
    public UpdatableConsumptionData(Consument parentNode) {
        super();
        this.consumedUnits = new HashMap();        
        parentNode.getConsumables().forEach(consumable -> {
            consumedUnits.put((Consumable) consumable, 0);
        });
    }       
            
    /**
     * Increments the {@link #consumedUnits} by the increment value given.
     * @param consumable consumable associated with the parent {@link Consument}
     * @param incrementValue value the {@link #consumedUnits} is incremented by
     * @throws ConsumptionDataException when {@link #consumedUnits} does not contain 
     * consumable given or when the incrementValue is below zero
     */
    public void updateConsumptionData(Consumable consumable, int incrementValue) throws ConsumptionDataException{
        if(!consumedUnits.keySet().contains(consumable))
            throw new ConsumptionDataException("Unknown consumable " + consumable);
        else if(incrementValue < 0){
            throw new ConsumptionDataException("Invalid increment value format. Given value is below zero");
        }
        else{
            int actualValue = consumedUnits.get(consumable);
            consumedUnits.put(consumable, actualValue + incrementValue);
        }
            
    }                
    
}
