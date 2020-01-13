package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConfigurationReport;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConsumptionReport;
import cz.cvut.k36.omo.bartom47.smartfactory.sampledata.meac.Meac;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents controller of the simulation.
 * @author Matej
 */
public class SmartFactory implements Runnable {    
    private final Factory factory;
    private static final int TICK_INTERVAL_SECONDS = 3600;    
    private static Logger LOG = LoggerFactory.getLogger(SmartFactory.class);    
    
    private static enum ReportGeneration{
        NONE,
        START,
        TICK                
    }
    
    private static final ReportGeneration CONFIG_REPORT_GENMODE = ReportGeneration.NONE;
    private static final ReportGeneration CONSUMPTION_REPORT_GENMODE = ReportGeneration.NONE;
            
    
    private SmartFactory(Factory factory){
        LOG.info("Started simulation for " + factory);
        this.factory = factory;
        if(CONFIG_REPORT_GENMODE != ReportGeneration.NONE)
            generateConfigurationReport(factory, 0);
        if(CONSUMPTION_REPORT_GENMODE != ReportGeneration.NONE)
            generateConsumptionReport(factory, 0);
    }

    /**
     * Execution entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SmartFactory simulation = new SmartFactory(Meac.create());
        Thread t = new Thread(simulation);
        t.start();
    }

    @Override
    public void run() {  
        while(true){
            try {
                factory.tick();  
                if (CONFIG_REPORT_GENMODE == ReportGeneration.TICK) 
                    generateConfigurationReport(factory, Tick.getTickCount());
                if (CONSUMPTION_REPORT_GENMODE == ReportGeneration.TICK)
                    generateConsumptionReport(factory, Tick.getTickCount());
                Thread.sleep(TICK_INTERVAL_SECONDS * 1000);
            } catch (InterruptedException ex) {
                LOG.warn("Caught interrupted exception.", ex);
            }
        }
    }
    
    private void generateConsumptionReport(HierarchyNode node, Integer tickId){
        ConsumptionReport cr = new ConsumptionReport(tickId);
        cr.generate(node.getConsumption());
        LOG.info("Consumption report for " + node + " was generated.");
    }
    
    private void generateConfigurationReport(HierarchyNode node, Integer tickId){       
        ConfigurationReport cr = new ConfigurationReport(tickId);
        cr.generate(node.getConfiguration());
        LOG.info("Configuration report for " + node + " was generated.");
    }
    
}
