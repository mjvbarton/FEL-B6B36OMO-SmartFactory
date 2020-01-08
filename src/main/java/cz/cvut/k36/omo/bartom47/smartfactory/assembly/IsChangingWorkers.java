package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the state when assembly changes sequence of workers which
 * is caused by {@link Serie} series with different products.
 * @author Matej
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
    AssemblyState next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
