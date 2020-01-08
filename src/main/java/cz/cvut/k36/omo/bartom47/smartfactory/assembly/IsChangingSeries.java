package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

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
       
    // TODO: Implement logic here.   
    @Override
    AssemblyState next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


