package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the state when assembly changes sequence of workers which
 * is caused by {@link Series} series with different products.
 * @author Matej
 * @since 1.0-BETA not used at all
 */
class IsChangingWorkers extends AssemblyState{
    private static final String LOGGER_NAME = IsChangingWorkers.class.getName();
    private static final Logger LOG = LoggerFactory.getLogger(LOGGER_NAME);
    
    IsChangingWorkers(Assembly assembly, Queue<Series> workingPlan, Series activeSerie) {
        super(assembly, workingPlan, activeSerie);
    }
    
    // TODO: Implement logic here.
    // TODO: Implement NonActiveWorkersPool after 2020-01-09
    @Override
    AssemblyState next(Event e) {
        if(e instanceof Tick){
            assembly.reorganizeWorkers(activeSerie.getProduct());
            return new IsProducingSeries(assembly, workingPlan, activeSerie);
        } else {
            LOG.warn("Unhandled event " + e);
            return this;
        }
    }
    
}
