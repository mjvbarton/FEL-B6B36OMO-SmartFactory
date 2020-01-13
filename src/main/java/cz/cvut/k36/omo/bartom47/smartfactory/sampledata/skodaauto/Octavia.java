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
public class Octavia extends Product{
    private static Octavia instance;
    
    public Octavia() {
        super("Skoda Octavia", 200, loadSequence());
    }
    
    /**
     * Singleton method to retrieve instance.
     * @return stored instance
     */
    public synchronized static Octavia get(){
        if(instance == null){
            instance = new Octavia();
        }
        return instance;
    }

    private static Queue<Class<? extends Worker>> loadSequence() {
        LinkedList<Class<? extends Worker>> seq = new LinkedList();
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        seq.add(CollaborativeRobot.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        return seq;
    }
}
