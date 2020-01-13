package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the state when assembly is producing single {@link Series}
 * @author Matej
 */
class IsProducingSeries extends AssemblyState{
    private static final String LOGGER_NAME = IsProducingSeries.class.getName();
    private static final Logger LOG = LoggerFactory.getLogger(LOGGER_NAME);
                
    IsProducingSeries(Assembly assembly, Queue<Series> workingPlan, Series activeSerie) {
        super(assembly, workingPlan, activeSerie);
    }
                
    // TODO: Implement logic here.
    @Override
    AssemblyState next(Event e) {
        if(e instanceof Tick){
            if(activeSerie.hasNoProductsToProduce())
                return new IsChangingSeries(assembly, workingPlan, activeSerie).next(e);
            else{
                activeSerie.updateToProduce();
                return new IsProducingSeries(assembly, workingPlan, activeSerie);
            }                
        } else
            LOG.warn("Unhandled event " + e);
            return this;
    }
    
}
