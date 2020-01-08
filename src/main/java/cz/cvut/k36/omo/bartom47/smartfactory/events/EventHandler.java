package cz.cvut.k36.omo.bartom47.smartfactory.events;

/**
 * Can handle {@link Event} events
 * @author Matej
 */
public interface EventHandler {
    
    /**
     * Handles the event
     * @param e 
     */
    void handle(Event e);
    
    /**
     * Saves the event to the local log.
     * @param e 
     */
    void logEvent(Event e);
}
