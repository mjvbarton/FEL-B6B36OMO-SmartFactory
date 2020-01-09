package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.ConfigurationDataContainer;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionData;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionDataContainer;
import cz.cvut.k36.omo.bartom47.smartfactory.events.EventLog;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.EventPropagator;
import cz.cvut.k36.omo.bartom47.smartfactory.events.PropagatableEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Matej
 * @param <K> type of the configuration model node
 * @param <T> type of the consumption model node
 */
public abstract class HierarchyNode<K extends ConfigurationData, T extends ConsumptionData> implements EventPropagator, 
        ConfigurationDataContainer<K>, 
            ConsumptionDataContainer<T>, EventLog{    
    private final K configuration;
    private final T consumption;
    
    /**
     * Event history. Elements should be added directly via method 
     * {@link #logEvent(Event) }.
     * For creating reports based on event history use 
     * {@link #getHistory()}
     */
    private final Queue<Event> eventHistory;

    protected HierarchyNode(K configuration, T consumption) {
        this.eventHistory = new LinkedList();
        this.configuration = configuration;
        configuration.setParent(this);
        this.consumption = consumption; 
        consumption.setParent(this);
    }            
    
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
        
    @Override
    public Queue<Event> getHistory(){
        return new LinkedList(eventHistory);
    }

    @Override
    public K getConfiguration() {
        return configuration;
    }

    @Override
    public T getConsumption() {
        return consumption;
    }   

    @Override
    public void logEvent(Event e) {
        eventHistory.add(e);
    }
    
}
