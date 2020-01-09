package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import java.util.HashMap;

/**
 * Represents Collaborative robot
 * @author Matej
 */
public class CollaborativeRobot extends RepairableWorker {

    public CollaborativeRobot(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, unitConsumptionPerTick);
    }
    
    

    @Override
    public void handle(Event e) {
        super.handle(e);
    }
    
}
