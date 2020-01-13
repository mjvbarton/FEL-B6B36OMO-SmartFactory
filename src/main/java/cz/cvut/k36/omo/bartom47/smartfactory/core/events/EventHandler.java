package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

/**
 * Can handle {@link Event} events
 * @author Matej
 */
public interface EventHandler {
    
    /**
     * Handles the event
     * @param e handled event
     */
    void handle(Event e);
    
    /**
     * Saves the event to the local log.
     * @param e handled event
     */
    void logEvent(Event e);
}
