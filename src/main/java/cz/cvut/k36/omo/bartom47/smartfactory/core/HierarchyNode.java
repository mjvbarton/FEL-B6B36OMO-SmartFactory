package cz.cvut.k36.omo.bartom47.smartfactory.core;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.EventLog;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.EventPropagator;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.PropagatableEvent;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class representing node in a responsibility chain
 * @author Matej
 * @param <P> type of parent {@link HierarchyNode} 
 * @param <C> type of children nodes
 * @param <K> type of the configuration model node
 * @param <T> type of the consumption model node
 */
public abstract class HierarchyNode<P extends HierarchyNode, C extends HierarchyNode, 
        K extends ConfigurationData, T extends ConsumptionData> implements EventPropagator, 
        ConfigurationDataContainer<K>, 
            ConsumptionDataContainer<T>, EventLog{    
    private static Logger LOG = LoggerFactory.getLogger(HierarchyNode.class);
    private final P parent;
    private final String name;
    private final K configuration;
    private final T consumption;
    
    /**
     * Event history. Elements should be added directly via method 
     * {@link #logEvent(Event) }.
     * For creating reports based on event history use 
     * {@link #getHistory()}
     */
    private final Queue<Event> eventHistory;

    /**
     * Default constructor for hierarchy node.
     * @param parent parent node
     * @param name unique name of the node
     * @param configuration configuration data for the node, <i>Notice: Acts as a view!</i>
     * @param consumption 
     * @since 1.0-BETA configuration acts as a view only
     */
    protected HierarchyNode(P parent, String name, K configuration, T consumption) {
        Objects.requireNonNull(name);        
        Objects.requireNonNull(configuration);
        Objects.requireNonNull(consumption);        
        this.parent = parent;  
        this.name = name;
        this.eventHistory = new LinkedList();
        this.configuration = configuration;
        configuration.setParent(this);
        this.consumption = consumption; 
        consumption.setParent(this);
    }            
    
    /**
     * Returns children nodes of this hierarchy node
     * @return an immutable set of children nodes
     */
    public abstract Set<C> getChildren();
    
    /**
     * Returns parent node of this hierarchy node     
     * @return parent node
     * @throws UnsupportedOperationException if this node does not have
     * any parent node
     */
    public P getParent() throws UnsupportedOperationException{
        if(hasParent())
            return parent;
        else
            throw new UnsupportedOperationException("This node " + this + " does not have any parent node");        
    }
    
    /**
     * Checks if this node has parent node.
     * @return {@true} if the parent node is present otherwise {@code false}
     */
    protected boolean hasParent(){
        return !(parent == null);
    }
    
    /**
     * Returns the name of the node. This is used for report view.
     * @return the name of the node
     */
    public String getName(){
        return name;
    }
       
    @Override
    public void propagate(PropagatableEvent e) {
        LOG.debug(this + " propagated event " + e);
        getChildren().forEach(children -> {
            children.handle(e);
        });
    }

//    @Override
//    public void handle(PropagatableEvent e) {
//        LOG.debug(this + " handled propagatable event " + e);
//        handle((Event) e);
//        propagate(e);
//    }   
        
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
