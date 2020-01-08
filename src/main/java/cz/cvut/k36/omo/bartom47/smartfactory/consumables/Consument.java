package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationDataContainer;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionDataContainer;
import java.util.HashMap;

/**
 * Represents consument of consumables
 * @author Matej
 * @param <K> configuration data model node
 * @param <T> consumption data model node
 */
public abstract class Consument<K extends ConfigurationData, T extends ConsumptionData>
        implements ConfigurationDataContainer<K>, ConsumptionDataContainer<T>{
    
    protected final K configuration;
    protected final T consumption;
    protected final HashMap<Consumable, Integer> unitConsumptionPerTick;
    
    /**
     * Creates new Consument
     * @param configuration node of configuration data model
     * @param consumption node of consumption data model
     * @param unitConsumptionPerTick table of consumables and 
     * their consumption per tick
     */
    protected Consument(K configuration, T consumption, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        this.configuration = configuration;
        this.consumption = consumption;
        this.unitConsumptionPerTick = unitConsumptionPerTick;
    }
    
    /**
     * Updates the consumption stored in {@code Consument.consumption} defined 
     * by values from {@code unitConsumptionPerTick}
     */
    protected abstract void updateConsumption();
    
}
