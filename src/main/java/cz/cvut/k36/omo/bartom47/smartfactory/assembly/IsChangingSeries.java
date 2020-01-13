package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.WorkplanRequest;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the state when assembly finishes producing of {@link Serie} and
 * accepts new {@link Serie}
 * @author Matej
 */
class IsChangingSeries extends AssemblyState{
    private final static String LOGGER_NAME = IsChangingSeries.class.getName();
    private final static Logger LOG = LoggerFactory.getLogger(LOGGER_NAME);
    
    IsChangingSeries(Assembly assembly, Queue<Series> workingPlan, Series activeSerie) {
        super(assembly, workingPlan, activeSerie);
    }    
          
    @Override
    AssemblyState next(Event e) {
        if(e instanceof Tick){
            if(workingPlan.isEmpty()){
                WorkplanRequest.dispatch(assembly, assembly.getParent());
                return new IsChangingSeries(assembly, workingPlan, activeSerie);
            } else
                if(productsInSeriesAreEqual())
                    return new IsProducingSeries(assembly, workingPlan, workingPlan.poll()).next(e);
                else
                    return new IsChangingWorkers(assembly, workingPlan, workingPlan.poll()).next(e);
        }   else{
            LOG.warn("Unhandled event " + e);
            return this;
        }        
    }        
    
    protected boolean productsInSeriesAreEqual(){
        Series nextSerie = (Series) workingPlan.peek();
        return nextSerie.getProduct().equals(activeSerie.getProduct());
    }                        
}


