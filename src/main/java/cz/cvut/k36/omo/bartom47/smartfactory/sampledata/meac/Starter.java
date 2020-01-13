/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Starter extends Product{
    private static Starter instance;

    public Starter() {
        super("Starter", 2000, loadSequence());
    }

    /**
     * Singleton method to retrieve instance.
     * @return stored instance
     */
    public synchronized static Starter get() {
        if (instance == null) {
            instance = new Starter();
        }
        return instance;
    }

    private static Queue<Class<? extends Worker>> loadSequence() {
        LinkedList<Class<? extends Worker>> seq = new LinkedList();
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(Machine.class);
        seq.add(Machine.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(CollaborativeRobot.class);
        seq.add(CollaborativeRobot.class);
        seq.add(PersonWorker.class);
        seq.add(PersonWorker.class);
        seq.add(Machine.class);
        seq.add(Machine.class);
        seq.add(PersonWorker.class);
        return seq;
    }
}
