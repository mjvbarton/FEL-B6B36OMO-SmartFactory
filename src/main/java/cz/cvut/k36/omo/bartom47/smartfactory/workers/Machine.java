package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Electricity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Oil;
import java.util.HashMap;

/**
 * Represents Machine worker. <br>
 * This entity consumes {@link Oil} and {@link Electricity} by default.
 * @author Matej
 */
public class Machine extends RepairableWorker{
    
    public Machine(Assembly assembly, String name) {
        super(assembly, name, new HashMap());        
    }
    
    public static Machine create(Assembly assembly, String name, 
            int electricityConsumptionPerTick, int oilConsumptionPerTick){
        final Machine m = new Machine(assembly, name);        
        final Electricity e = new Electricity(m, electricityConsumptionPerTick);
        final Oil o = new Oil(m, oilConsumptionPerTick);
        return m;
    }
    
}
