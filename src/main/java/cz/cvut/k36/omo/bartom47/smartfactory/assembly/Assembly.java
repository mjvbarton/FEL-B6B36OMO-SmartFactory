package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.factory.FactoryBuilding;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.AssemblyConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.AssemblyConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.PropagatableEvent;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an assembly in the factory
 * @author Matej
 */
// TODO: Implement toString()
public class Assembly extends HierarchyNode<AssemblyConfiguration, AssemblyConsumption>{    
    private static Logger LOG = LoggerFactory.getLogger(Assembly.class);
    
    private int priority;
    private final String name;
    private final FactoryBuilding factoryBuilding;
    private final Queue<Worker> activeWorkers;
    // private NotWorkingWorkersPool nonActiveWorkers;  // TODO: Implement NonActiveWorkersPool after 2020-01-09
    private AssemblyState state;     

    protected Assembly(int priority, String name, FactoryBuilding factoryBuilding, 
            Queue<Worker> activeWorkers, AssemblyConfiguration configuration, AssemblyConsumption consumption, Queue<Series> workingPlan) {
        super(configuration, consumption);
        this.priority = priority;
        this.state = new IsChangingSeries(this, workingPlan, workingPlan.poll());
        this.name = name;
        this.factoryBuilding = factoryBuilding;
        this.activeWorkers = activeWorkers;        
    }
        
    // TODO: Implement NonActiveWorkersPool after 2020-01-09
    @Override
    public void propagate(PropagatableEvent e) {
        activeWorkers.forEach(aW -> aW.handle(e));
    }    
    
    /**
     * @since 1.0-BETA not supported, throws {@link UnsupportedOperationException}     
     * @throws UnsupportedOperationException always
     */
    @Override    
    protected List<HierarchyNode> getPropagatorChildren() {
        LOG.error("Unsupported method 'getPropagatorChildren' called.", new UnsupportedOperationException("Method is not supported."));
        throw new UnsupportedOperationException("Method is not supported.");
    }

    @Override
    public void handle(Event e) {
        logEvent(e);
    }

    @Override
    public void logEvent(Event e) {
        super.logEvent(e);
        LOG.debug(this + " " + e);        
    }
        
    /**
     * Gets the priority of the assembly.
     * @return 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the assembly
     * @param priority 
     */
    public synchronized void setPriority(int priority) {        
        this.priority = priority;
    }       

    /**
     * Gets the name of the assembly
     * @return the name of the assembly
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the parent factory building of the assembly
     * @return parent factory building
     */
    public FactoryBuilding getFactoryBuilding() {
        return factoryBuilding;
    }

    /**
     * Returns all workers associated with the assembly
     * @return list of workers
     */
    public List<Worker> getWorkers() {
        return activeWorkers.stream().collect(Collectors.toList());
    }    
    
        
    AssemblyState getState() {
        return state;
    }
    
    /**
     * Adds a collection of {@code Series} to working plan
     * @param series 
     */
    public synchronized void addSeriesToWorkingPlan(Collection<Series> series){
        Objects.requireNonNull(series);
        series.forEach(s -> addSeriesToWorkingPlan(s));
    }
    
    /**
     * Adds a single {@link Series} to working plan
     * @param series 
     */
    public synchronized void addSeriesToWorkingPlan(Series series){
        Objects.requireNonNull(series);
        state.workingPlan.add(series);
    }
    
}
