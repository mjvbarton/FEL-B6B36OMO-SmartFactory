package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationDataContainer;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionDataContainer;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.EventPropagator;
import cz.cvut.k36.omo.bartom47.smartfactory.events.PropagatableEvent;
import java.util.List;

/**
 *
 * @author Matej
 * @param <K> type of the configuration model node
 * @param <T> type of the consumption model node
 */
public abstract class HierarchyNode<K extends ConfigurationData, T extends ConsumptionData> implements EventPropagator, 
        ConfigurationDataContainer<K>, 
            ConsumptionDataContainer<T>{    
    
    /**
     * Get list of children nodes events are propagated to.
     * @return list of children nodes for propagator
     */
    protected abstract List<HierarchyNode> getPropagatorChildren();
       
    @Override
    public void propagate(PropagatableEvent e) {
        getPropagatorChildren().forEach(childrenNode -> childrenNode.handle(e));
    }

    @Override
    public void handle(PropagatableEvent e) {
        handle((Event) e);
        propagate(e);
    }    
}
