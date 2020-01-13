package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import java.util.Queue;

/**
 * Adds support for event logging.
 * @author Matej
 */
public interface EventLog {
    
    /**
     * Returns a shallow copy of stored event log
     * @return queue of events contained in the log.
     */
    public Queue<Event> getHistory();
}
