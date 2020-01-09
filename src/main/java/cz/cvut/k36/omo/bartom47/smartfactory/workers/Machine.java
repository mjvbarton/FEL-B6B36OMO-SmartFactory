package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.WorkerConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.WorkerConsumption;
import java.util.HashMap;

/**
 *
 * @author Matej
 */
public class Machine extends RepairableWorker{
    
    public Machine(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, unitConsumptionPerTick);
    }
    
}
