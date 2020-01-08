package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.EventPropagator;
import cz.cvut.k36.omo.bartom47.smartfactory.events.PropagatableEvent;

/**
 *
 * @author Matej
 */
public abstract class HierarchyNode implements EventPropagator{

    @Override
    public void propagate(PropagatableEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handle(PropagatableEvent e) {
        handle((Event) e);
        propagate(e);
    }    
}
