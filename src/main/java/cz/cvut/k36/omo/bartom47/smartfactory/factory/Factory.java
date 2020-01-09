package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.FactoryConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.FactoryConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents factory institution with buildings.
 * @author Matej
 */
// TODO: Fix javadoc
// TODO: Implement toString()
// TODO: Move factory to a package cz.cvut.k36.omo.bartom47.smartfactory.factory
public class Factory extends HierarchyNode<FactoryConfiguration, FactoryConsumption> {
    private static Logger LOG = LoggerFactory.getLogger(Factory.class);    
    private final Set<FactoryBuilding> factoryBuildings;        
        
    public Factory(Set<FactoryBuilding> factoryBuildings, FactoryConfiguration configuration, FactoryConsumption consumption) {
        super(configuration, consumption);
        this.factoryBuildings = factoryBuildings;        
    }   
    
    public static Factory create(){
        return new Factory(new HashSet(), new FactoryConfiguration(), new FactoryConsumption());
    }
        
    @Override
    protected List<HierarchyNode> getPropagatorChildren() {
        return factoryBuildings.stream()
                .map(fb -> (HierarchyNode) fb)
                .collect(Collectors.toList());                
    }
    
    public Set<FactoryBuilding> getBuildings(){
        return new HashSet(factoryBuildings);
    }

    @Override
    public void handle(Event e) {
        logEvent(e);
    }
    
    @Override
    public void logEvent(Event e) {
        super.logEvent(e);
        LOG.debug(e.toString());        
    }
    
    /**
     * Generates {@link Tick} events at the whole factory.
     */
    public void tick(){
        Tick.dispatch(this, this);
    }
    
    public void addBuildings(List<FactoryBuilding> buildings){
        this.factoryBuildings.addAll(buildings);
    }
    
}
