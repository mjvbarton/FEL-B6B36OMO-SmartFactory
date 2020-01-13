package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.PropagatableEvent;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Product;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an assembly in the factory
 * @author Matej
 */
// TODO: Fix Javadoc
public class Assembly extends HierarchyNode<Building, Worker, AssemblyConfiguration, AssemblyConsumption>{    
    private static Logger LOG = LoggerFactory.getLogger(Assembly.class);
    private int priority;
    private final Queue<Worker> activeWorkers;
    private final NotWorkingWorkers nonActiveWorkers;  // TODO: Implement NonActiveWorkersPool after 2020-01-09
    private AssemblyState state; 

    /**
     * Creates new Assembly with empty work plan.
     * @param building parent building of the assembly
     * @param name the name of the assembly
     * @param priority the priority of the assembly,
     * higher value means higher priority
     * @return new assembly
     */
    // TODO: Add name management
    public static Assembly create(Building building, String name, int priority) {
        return new Assembly(building, name, priority, new LinkedList());
    }
    
    /**
     * Creates new Assembly with specified work plan.
     * @param building parent building of the assembly
     * @param name the name of the assembly
     * @param priority the priority of the assembly,
     * higher value means higher priority
     * @param workPlan the work plan for the assembly
     * @return new assembly
     */
    // TODO: Add name management
    public static Assembly create(Building building, String name, int priority, Queue<Series> workPlan) {
        return new Assembly(building, name, priority, workPlan);
    }
    
    /**
     * Default constructor for the assembly.
     * @param building parent building of the assembly
     * @param name the name of the assembly
     * @param priority the prioritz of the assembly, higher value means higher priority
     * @param workPlan work plan for the assembly
     */
    private Assembly(Building building, String name, int priority, Queue<Series> workPlan){
        super(building, name, new AssemblyConfiguration(), new AssemblyConsumption());        
        building.addAssembly(this);        
        this.priority = priority;        
        state = new IsChangingSeries(this, workPlan, workPlan.poll());
        activeWorkers = new LinkedList();  
        nonActiveWorkers = new NotWorkingWorkers(this);
    }
    
    /**
     * Constructor for testing.
     * @param building building mock
     * @param name any string
     * @param priority any integer
     * @param state state mock
     * @param workPlan workplan mock
     * @param activeWorkers activeWorkers mock
     * @param nonActiveWorkers nonActiveWorkers mock
     */
    Assembly(Building building, String name, int priority, AssemblyState state, 
            Queue<Series> workPlan, Queue<Worker> activeWorkers, NotWorkingWorkers nonActiveWorkers){
        super(building, name, new AssemblyConfiguration(), new AssemblyConsumption());
        this.priority = priority;
        this.state = state;
        this.activeWorkers = activeWorkers;
        this.nonActiveWorkers = nonActiveWorkers;
    }
    
    /**
     * Gets the priority of the assembly.
     * @since 1.0-BETA not used
     * @return returns the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the assembly
     * @since 1.0-BETA not used
     * @param priority priority of the assembly
     */
    public synchronized void setPriority(int priority) {
        this.priority = priority;
    }                   
    
    AssemblyState getState() {
        return state;
    }

    /**
     * Adds a collection of {@code Series} to working plan
     * @param series
     */
    public synchronized void addSeriesToWorkingPlan(Collection<Series> series) {
        Objects.requireNonNull(series);
        series.forEach(s -> addSeriesToWorkingPlan(s));
    }

    /**
     * Adds a single {@link Series} to working plan     
     * @param series
     */
    public synchronized void addSeriesToWorkingPlan(Series series) {
        Objects.requireNonNull(series);
        state.workingPlan.add(series);
    }

    /**
     * Adds a collection of workers to the assembly.
     * @param workers collection of workers
     */
    public synchronized void addWorker(Collection<Worker> workers) {
        activeWorkers.addAll(workers);
    }

    /**
     * Adds a worker to the assembly
     * @param worker worker
     */
    public synchronized void addWorker(Worker worker) {
        activeWorkers.add(worker);
    }
    
    /**
     * Reorganizes assembly to produce product given
     * @param product to be produced at assembly
     */
    synchronized void reorganizeWorkers(Product product){        
        while(!activeWorkers.isEmpty()){
            nonActiveWorkers.saveWorker(activeWorkers.poll());
        }
        product.getWorkerSequence().forEach(workerClass ->{
            activeWorkers.add(nonActiveWorkers.getWorker(workerClass));
        });    
    }

    @Override
    public void handle(Event e) {
        if(e instanceof Tick){
            LOG.debug(this + " handled event " + e);            
            logEvent(e);            
            state.next(e);
            propagate((Tick) e);
        } else {
            LOG.warn("Unhandled event at " + this);
        }
    }           
    
    @Override
    public Set<Worker> getChildren() {
        return new HashSet(activeWorkers);
    }
    
    Collection<Worker> getAllWorkers(){
        return Stream.concat(getChildren().stream(), getNonActiveWorkers().stream())
                .collect(Collectors.toSet());
                
    }
    
    Collection<Worker> getNonActiveWorkers(){
        return nonActiveWorkers.getWorkers();
    }
    
    Queue<Series> getWorkPlan(){
        return new LinkedList(state.getWorkingPlan());
    }
    
    @Override
    public void propagate(PropagatableEvent e) {
        activeWorkers.forEach(aW -> aW.handle(e));
    }
        
    @Override
    public String toString() {
        return "Assembly['" + getName() + "']";
    }        
}
