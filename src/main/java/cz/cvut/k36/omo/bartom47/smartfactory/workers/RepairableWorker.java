package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import java.util.HashMap;

/**
 * Class represeenting worker, that can break down and that can be repaired.
 * @author Matej
 * @since 1.0-BETA acts as a flag class
 */
public abstract class RepairableWorker extends Worker{

    /**
     * Default constructor for repairable worker.
     * @param assembly
     * @param name
     * @param unitConsumptionPerTick 
     * @see Worker#Worker(Assembly, String, HashMap)
     */
    public RepairableWorker(Assembly assembly, String name, 
            HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, unitConsumptionPerTick);
    }        

    @Override
    public void handle(Event e) {
        super.handle(e);
    }
        
}
