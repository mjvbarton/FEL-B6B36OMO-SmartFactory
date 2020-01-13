package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents time tick in the simulation.
 * @author Matej
 */
public class Tick extends PropagatableEvent{
    private static Logger LOG = LoggerFactory.getLogger(Tick.class);
    
    /**
     * Represents the count of created ticks. Used for generating {@code Tick.tickId}
     */
    private static int tickCount = 0;        
        
    private final Integer tickId;
    
    public static void resetCounter(){
        tickCount = 0;
    }
    
    /**
     * Creates new Tick event with id given.
     * @param sender
     * @param tickId 
     */
    protected Tick(EventHandler sender, int tickId) {
        super(Tick.class.getSimpleName(), sender);
        this.tickId = tickId;
    }
    
    /**
     * Dispatches the Tick event at first reciever who handles it.
     * @param sender sender of the event
     * @param firstReciever first reciever to handle event
     * @return the tick object for reference
     */
    public static Tick dispatch(EventHandler sender, EventHandler firstReciever){      
        tickCount++;
        final Tick dispatched = new Tick(sender, tickCount);
        LOG.info(dispatched.toString());
        firstReciever.handle(dispatched);
        return dispatched;
    }

    /**
     * Get the number of all ticks created in the simulation.
     * @return the number of created ticks
     */
    public static int getTickCount() {
        return tickCount;
    }

    /**
     * Get the number of actual tick
     * @return the tick id
     */
    public Integer getTickId() {
        return tickId;
    }        

    @Override
    public String toString() {
        return "Tick['" + tickId + "']";
    }
    
    
    
}
