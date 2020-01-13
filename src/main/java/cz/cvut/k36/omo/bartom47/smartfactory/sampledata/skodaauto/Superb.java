package cz.cvut.k36.omo.bartom47.smartfactory.sampledata.skodaauto;

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
public class Superb extends Product{
    private static Superb instance;
    
    public Superb() {
        super("Skoda Superb", 50, loadSequence());
    }
    
    /**
     * Singleton method to retrieve instance.
     * @return stored instance
     */
    public synchronized static Superb get() {
        if (instance == null) {
            instance = new Superb();
        }
        return instance;
    }

    private static Queue<Class<? extends Worker>> loadSequence() {
        LinkedList<Class<? extends Worker>> seq = new LinkedList();
        seq.add(Machine.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        return seq;
    }
}
