package cz.cvut.k36.omo.bartom47.smartfactory.events;

/**
 * Represents time tick in the simulation.
 * @author Matej
 */
public class Tick extends PropagatableEvent{
    
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
     */
    public static void dispatch(EventHandler sender, EventHandler firstReciever){
        try{
            tickCount++;
            final Tick dispatched = new Tick(sender, tickCount);
            firstReciever.handle(dispatched);
        } catch(RuntimeException e){
            tickCount--;
        }        
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
    
}
