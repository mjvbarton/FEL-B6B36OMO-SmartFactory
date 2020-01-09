package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.FactoryBuildingConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.FactoryBuildingConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a building of the factory.
 * @author Matej
 */
// TODO: Move factory to a package cz.cvut.k36.omo.bartom47.smartfactory.factory.building
public abstract class FactoryBuilding 
        extends HierarchyNode<FactoryBuildingConfiguration, FactoryBuildingConsumption>{
    private static Logger LOG = LoggerFactory.getLogger(FactoryBuilding.class);
    
    private final Set<Assembly> assemblies;    
    private final String name;
    private final Factory factory;
    private final FactoryBuildingConfiguration configuration;
    private final FactoryBuildingConsumption consumption;

    /**
     * Creates new factory building
     * @param assemblies set of assemblies in the factory building
     * @param name the name of the building
     * @param factory the parent factory the building belongs to
     * @param configuration node of configuration data view model
     * @param consumption node of consumption data view model
     */
    protected FactoryBuilding(Set<Assembly> assemblies, String name, Factory factory, 
            FactoryBuildingConfiguration configuration, FactoryBuildingConsumption consumption) {
        Objects.requireNonNull(assemblies);
        Objects.requireNonNull(name);
        Objects.requireNonNull(factory);
        Objects.requireNonNull(configuration);
        Objects.requireNonNull(consumption);        
        this.assemblies = assemblies;
        this.name = name;
        this.factory = factory;
        this.configuration = configuration;
        this.consumption = consumption;
    }    
          
    @Override
    protected List getPropagatorChildren() {
        return assemblies.stream()
                .map(a -> (HierarchyNode) a)
                .collect(Collectors.toList());                
    }

    @Override
    public void handle(Event e) {
        logEvent(e);
    }

    @Override
    public void logEvent(Event e) {
        LOG.debug(this + " captured event " + e);
        eventHistory.add(e);
    }

    @Override
    public FactoryBuildingConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public FactoryBuildingConsumption getConsumption() {
        return consumption;
    }

    /**
     * Returns the name of the factory building.
     * @return the name of the factory building
     */
    public String getName() {
        return name;
    }
               
}
