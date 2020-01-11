package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.ManHour;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents person worker.
 * This entity consumes {@link ManHour} by default.
 * @author Matej
 */
public class PersonWorker extends Worker{
    private static Logger LOG = LoggerFactory.getLogger(PersonWorker.class);

    /**
     * Default constructor for person worker. Person worker consumes {@link ManHour}.
     * @param assembly parent assembly
     * @param name the name of person worker
     */
    protected PersonWorker(Assembly assembly, String name) {
        super(assembly, name, new HashMap());
        final Consumable c = new ManHour(this, 1);
    }
    
    //TODO: Add name management
    /**
     * Creates new person worker with name given.
     * @param assembly parent assembly
     * @param name the name of person worker
     * @return new person worker created by default constructor
     */
    public static PersonWorker create(Assembly assembly, String name){        
        return new PersonWorker(assembly, name);
    }

    @Override
    public void handle(Event e) {
        super.handle(e);
    }    
        
}
