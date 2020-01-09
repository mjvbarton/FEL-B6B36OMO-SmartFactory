package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConsumptionReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matej
 */
public class SmartFactory implements Runnable{
    private Factory factory;
    private static final int TICK_INTERVAL_SECONDS = 3600;
    private static Logger LOG = LoggerFactory.getLogger(SmartFactory.class);
    
    public SmartFactory(Factory factory){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread f = new Thread(
              new SmartFactory(Factory.create()));
        f.start();        
    }

    @Override
    public void run() {
        try {
            factory.tick();            
            Thread.sleep(TICK_INTERVAL_SECONDS * 1000);
        } catch (InterruptedException ex) {
            LOG.warn("Caught interrupted exception.", ex);
        }
    }
    
    public void generateConsumptionReport(HierarchyNode node, Integer tickId){
        ConsumptionReport cr = new ConsumptionReport(tickId);
    }
    
}
