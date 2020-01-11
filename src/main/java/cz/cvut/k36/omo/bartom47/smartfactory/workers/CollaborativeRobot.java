package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Electricity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Oil;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import java.util.HashMap;

/**
 * Represents Collaborative Robot.<br>
 * This entity consumes {@link Oil} and {@link Electricity} by default.
 * @author Matej
 */
public class CollaborativeRobot extends RepairableWorker {

    private CollaborativeRobot(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, unitConsumptionPerTick);
    }
    
    public static CollaborativeRobot create(Assembly assembly, String name, 
            int electricityConsumptionPerTick, int oilConsumptionPerTick){
        final CollaborativeRobot m = new CollaborativeRobot(assembly, name, new HashMap());        
        final Electricity e = new Electricity(m, electricityConsumptionPerTick);
        final Oil o = new Oil(m, oilConsumptionPerTick);
        return m;
    }    
    

    @Override
    public void handle(Event e) {
        super.handle(e);
    }
    
}
