package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.core.exceptions.NotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents consument of consumables
 * @author Matej
 * @param <P> type of parent {@link HierarchyNode}
 * @param <C> type of children nodes
 * @param <K> configuration data model node
 * @param <T> consumption data model node
 */
// TODO: Fix Javadoc
public abstract class Consument<P extends HierarchyNode, C extends Consumable, K extends WorkerConfiguration, T extends WorkerConsumption>
        extends HierarchyNode<P, C, K, T>{
    private static final Logger LOG = LoggerFactory.getLogger(Consument.class);
        
    private final HashMap<Consumable, Integer> unitConsumptionPerTick;
    
    /**
     * Creates new Consument
     * @param parent
     * @param name
     * @param configuration
     * @param consumption
     * @see HierarchyNode
     * @param unitConsumptionPerTick
     */
    protected Consument(P parent, String name, K configuration, T consumption, HashMap<Consumable, Integer> unitConsumptionPerTick ) {                        
        super(parent, name, configuration, consumption);
        this.unitConsumptionPerTick = unitConsumptionPerTick;
    }
    
    /**
     * Gets consumable increment for consumable given.
     * @param consumable consumable in the consument
     * @return increment value for consumable given
     */
    public Integer getConsumableIncrement(Consumable consumable){
        if(unitConsumptionPerTick.containsKey(consumable))
            return unitConsumptionPerTick.get(consumable);
        else
            throw NotFoundException.create(consumable);
    }
    
    protected void addConsumable(Consumable consumable, int unitsConsumedPerTick){
        unitConsumptionPerTick.put(consumable, unitsConsumedPerTick);
    }
            
    @Override
    public Set<C> getChildren() {
        return new HashSet(unitConsumptionPerTick.keySet());
    }     
    
    /**
     * Public API for extracting consumables.
     * @return collection of consumables
     * @see #getChildren() 
     */
    public Collection<Consumable> getConsumables(){
        return (Collection<Consumable>) getChildren();
    }
}
