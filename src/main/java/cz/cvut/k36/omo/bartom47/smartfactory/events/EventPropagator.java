package cz.cvut.k36.omo.bartom47.smartfactory.events;

/**
 * Can propagate {@link PropagatableEvent} events (for example {@link Tick}).
 * @author Matej
 */
public interface EventPropagator extends EventHandler{
    /**
     * Propagates given propagated event.
     * @param e event to propagate
     */
    void propagate(PropagatableEvent e);
    
    /**
     * Handles propagatable event. A super method shall be called here.
     * @param e event to handle
     */
    void handle(PropagatableEvent e);
}
