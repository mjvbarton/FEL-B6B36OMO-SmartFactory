package cz.cvut.k36.omo.bartom47.smartfactory.sampledata.meac;

import cz.cvut.k36.omo.bartom47.smartfactory.production.Product;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.CollaborativeRobot;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Machine;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.PersonWorker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Sample product. Singletonized.
 * @author Matej
 */
public class RidiciJednotka extends Product {
    private static RidiciJednotka instance;

    public RidiciJednotka() {
        super("RidiciJednotka", 300, loadSequence());
    }

    /**
     * Singleton method to retrieve instance.
     * @return stored instance
     */
    public synchronized static RidiciJednotka get() {
        if (instance == null) {
            instance = new RidiciJednotka();
        }
        return instance;
    }

    private static Queue<Class<? extends Worker>> loadSequence() {
        LinkedList<Class<? extends Worker>> seq = new LinkedList();
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(Machine.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(Machine.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        return seq;
    }
    
}
