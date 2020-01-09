package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.FactoryConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.FactoryConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents factory institution with buildings.
 * @author Matej
 */
// TODO: Implement toString()
// TODO: Move factory to a package cz.cvut.k36.omo.bartom47.smartfactory.factory
public class Factory extends HierarchyNode<FactoryConfiguration, FactoryConsumption> {
    private static Logger LOG = LoggerFactory.getLogger(Factory.class);
    private final Set<FactoryBuilding> factoryBuildings;
    
    private final FactoryConfiguration configuration;
    private final FactoryConsumption consumption;
        
    public Factory(Set<FactoryBuilding> factoryBuildings, FactoryConfiguration configuration, FactoryConsumption consumption) {
        this.factoryBuildings = factoryBuildings;
        this.configuration = configuration;
        this.consumption = consumption;
    }   
        
    @Override
    protected List<HierarchyNode> getPropagatorChildren() {
        return factoryBuildings.stream()
                .map(fb -> (HierarchyNode) fb)
                .collect(Collectors.toList());                
    }

    @Override
    public void handle(Event e) {
        logEvent(e);
    }

    @Override
    public FactoryConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public FactoryConsumption getConsumption() {
        return consumption;
    }

    @Override
    public void logEvent(Event e) {
        LOG.debug(e.toString());
        eventHistory.add(e);
    }
    
    /**
     * Generates {@link Tick} events at the whole factory.
     */
    public void tick(){
        Tick.dispatch(this, this);
    }
    
}
