package cz.cvut.k36.omo.bartom47.smartfactory.buildings;

import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a building of the factory.
 * @author Matej
 */
public class Building extends HierarchyNode<Factory, Assembly, BuildingConfiguration, BuildingConsumption>{
    private static Logger LOG = LoggerFactory.getLogger(Building.class);    
    private final Set<Assembly> assemblies;    
    //private final Maintenance maintenance;
    
    // TODO: Create name management for the simulation
    public static Building create(Factory factory, String name){            
        return new Building(factory, name);        
    }              

    /**
     * Creates new factory building     
     * @param name the name of the building
     * @param factory the parent factory the building belongs to     
     */
    private Building(Factory factory, String name) {
        super(factory, name, new BuildingConfiguration(), new BuildingConsumption());                 
        assemblies = new HashSet();
        factory.addBuilding(this);
    }    
    
    /**
     * Adds a collection of assemblies to the building.
     * @param assemblies assemblies to be added
     */
    public void addAssembly(Collection<Assembly> assemblies) {
        this.assemblies.addAll(assemblies);
    }

    /**
     * Adds an assembly to the building
     * @param assembly assembly to be added
     */
    public void addAssembly(Assembly assembly) {
        assemblies.add(assembly);
    }

    @Override
    public Set<Assembly> getChildren() {
        return new HashSet(assemblies);
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
}
