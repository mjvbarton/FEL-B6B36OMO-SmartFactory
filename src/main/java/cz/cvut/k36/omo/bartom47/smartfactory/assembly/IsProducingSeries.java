package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the state when assembly is producing single {@link Serie}
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
    AssemblyState next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
