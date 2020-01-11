package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents factory institution with buildings.
 * @author Matej
 */
// TODO: Fix javadoc
public class Factory extends HierarchyNode<Factory, Building, FactoryConfiguration, FactoryConsumption> {
    private static Logger LOG = LoggerFactory.getLogger(Factory.class);    
    private final Set<Building> buildings;        
        
    private Factory(String name) {
        super(null, name, new FactoryConfiguration(), new FactoryConsumption());
        this.buildings = new HashSet();        
    }
    
    /**
     * Creates new factory with name given.
     * @param name the name of the factory
     * @return new factory
     */
    public static Factory create(String name){
        return new Factory(name);
    }
                          
    /**
     * Generates {@link Tick} events at the whole factory.
     */
    public void tick(){       
        Tick.dispatch(this, this);
    }
    
    public void addBuilding(Collection<Building> buildings){
        this.buildings.addAll(buildings);
    }
    
    public void addBuilding(Building building){
       buildings.add(building);
    }

    @Override
    public Set<Building> getChildren() {
        return buildings;
    }

    @Override
    public void handle(Event e) {        
        if(e instanceof Tick){
            LOG.debug(this + " handled event " + e);
            logEvent(e);            
            propagate((Tick) e);
        } else {
            LOG.warn("Unhandled event at " + this);
        }
    }        

    @Override
    public String toString() {
        return "Factory['" + getName() + "']";
    }        
}
