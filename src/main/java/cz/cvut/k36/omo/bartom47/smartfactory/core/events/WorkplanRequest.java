package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

/**
 *
 * @author Matej
 */
public class WorkplanRequest extends Event{
    
    public WorkplanRequest(EventHandler sender) {
        super(WorkplanRequest.class.getSimpleName(), sender);
    }
    
    public static void dispatch(EventHandler sender, EventHandler firstReciever){
        firstReciever.handle(new WorkplanRequest(sender));
    }
    
}
