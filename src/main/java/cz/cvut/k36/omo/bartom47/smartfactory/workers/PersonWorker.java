package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.ManHour;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.WorkerConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents person worker
 * @author Matej
 */
public class PersonWorker extends Worker{
    private static Logger LOG = LoggerFactory.getLogger(PersonWorker.class);

    private PersonWorker(Assembly assembly, String name, HashMap<Consumable, Integer> unitConsumptionPerTick) {
        super(assembly, name, unitConsumptionPerTick, new WorkerConfiguration(), new WorkerConsumption());
    }
    
    public static PersonWorker create(Assembly assembly, String name){
        HashMap<Consumable, Integer> upt = new HashMap();
        upt.put(new ManHour(), 1);
        return new PersonWorker(assembly, name, upt);
    }

    @Override
    public void handle(Event e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
