package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.core.TimeWorkingEntity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.WorkerConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.HierarchyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents consument entity.
 * @author Matej  
 */
// TODO: Add javadoc
public abstract class Worker extends
        Consument<WorkerConfiguration, WorkerConsumption> implements TimeWorkingEntity{
    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);
    
    protected final Assembly assembly;
    protected final String name;    
    private final Queue<Event> eventLog = new LinkedList();

    protected Worker(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick, 
            WorkerConfiguration configuration, WorkerConsumption consumption) {
        super(unitConsumptionPerTick, configuration, consumption);
        Objects.requireNonNull(assembly);
        this.assembly = assembly;
        this.name = name;
    }        

    @Override
    protected List<HierarchyNode> getPropagatorChildren() {
        // Worker does not have any children.
        return new ArrayList();
    }        

    @Override
    public void doWork() {
        LOG.debug("Worker '" + name + "' has worked.");
        updateConsumption();
    }

    @Override
    public void logEvent(Event e) {
        LOG.debug("Captured event: " + e);
        super.logEvent(e);
    }        
        
    public Assembly getAssembly() {
        return assembly;
    }

    public String getName() {
        return name;        
    }                      

    // TODO: Add logic
    @Override
    public void handle(Event e) {     
        logEvent(e);
        if(e instanceof Tick){
            updateConsumption();
        }
    }        
}
