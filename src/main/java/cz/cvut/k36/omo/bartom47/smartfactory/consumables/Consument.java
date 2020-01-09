package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.WorkerConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.HierarchyNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents consument of consumables
 * @author Matej
 * @param <K> configuration data model node
 * @param <T> consumption data model node
 */
// TODO: Fix Javadoc
public abstract class Consument<K extends WorkerConfiguration, T extends WorkerConsumption>
        extends HierarchyNode<K, T>{
    private static final Logger LOG = LoggerFactory.getLogger(Consument.class);
        
    private final HashMap<Consumable, Integer> unitConsumptionPerTick;
    
    /**
     * Creates new Consument
     * @param unitConsumptionPerTick table of consumables and 
     * their consumption per tick
     * @param configuration
     * @param consumption
     */
    protected Consument(HashMap<Consumable, Integer> unitConsumptionPerTick, K configuration, T consumption) {                        
        super(configuration, consumption);
        this.unitConsumptionPerTick = unitConsumptionPerTick;
    }
    
    /**
     * Updates the consumption stored in {@code Consument.consumption} defined 
     * by values from {@code unitConsumptionPerTick}. Shall be triggered by {@link Tick} event.
     */
    protected void updateConsumption(){        
        unitConsumptionPerTick.forEach((key, value) -> {            
             key.getConsumption().setConsumed(value);            
        });                              
    }

    /**
     * Get consumption per ticks table
     * @return 
     */
    public Set<Consumable> getConsumables() {
        return new HashSet(unitConsumptionPerTick.keySet());
    }     
}
