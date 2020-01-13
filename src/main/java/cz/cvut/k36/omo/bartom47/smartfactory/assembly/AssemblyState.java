package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Queue;

/**
 * Represents the state of {@link Assembly}
 * @author Matej
 */
abstract class AssemblyState{   
    protected final Assembly assembly;
    protected final Queue<Series> workingPlan;
    protected final Series activeSerie;

    protected AssemblyState(Assembly assembly, Queue<Series> workingPlan, Series activeSerie) {
        this.assembly = assembly;
        this.workingPlan = workingPlan;
        this.activeSerie = activeSerie;
    }        
    
    /**
     * Returns the next state of assembly state machine
     * @param e event passed from the assembly
     * @return the next state
     */
    abstract AssemblyState next(Event e);

    /**
     * Returns the working plan of the state.
     * @return working plan of the state
     */
    Queue<Series> getWorkingPlan() {
        return workingPlan;
    }
}
