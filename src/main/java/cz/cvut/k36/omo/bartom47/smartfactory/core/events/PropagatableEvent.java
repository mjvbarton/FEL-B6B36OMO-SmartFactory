package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

/**
 * Represents event that can be propagated over children.
 * @author Matej
 */
public abstract class PropagatableEvent extends Event{
    
    protected PropagatableEvent(String name, EventHandler sender) {
        super(name, sender);
    }        
}
