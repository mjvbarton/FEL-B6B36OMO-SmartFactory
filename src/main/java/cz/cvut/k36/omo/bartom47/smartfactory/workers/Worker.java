package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.core.TimeWorkingEntity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents consument entity.
 * @author Matej  
 */
public abstract class Worker extends
        Consument<Assembly, Consumable, WorkerConfiguration, WorkerConsumption> implements TimeWorkingEntity{
    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);
    
    protected final Assembly assembly;    
    private final Queue<Event> eventLog = new LinkedList();
        
    /**
     * Creates new worker with default configuration and consumption.
     * @param assembly parent assembly
     * @param name name of the worker
     * @param unitConsumptionPerTick table of consumptions per tick
     */ 
    protected Worker(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, new WorkerConfiguration(), new WorkerConsumption(), unitConsumptionPerTick);
        Objects.requireNonNull(assembly);
        this.assembly = assembly;
        assembly.addWorker(this);        
    }
        
    @Override
    public void doWork() {
        LOG.debug(this + " has worked");        
    }            
            
    @Override
    public void handle(Event e) {                             
        if(e instanceof Tick){
            logEvent(e);
            doWork();
            propagate((Tick) e);
        } else {
            LOG.warn("Unhandled event at " + this);
        }
    }   

    @Override
    public String toString() {
        return "Worker['" + getName() + "']";
    }
    
}
