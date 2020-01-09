package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration.FactoryBuildingConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.FactoryBuildingConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Event;
import java.util.HashSet;
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
public class FactoryBuilding 
        extends HierarchyNode<FactoryBuildingConfiguration, FactoryBuildingConsumption>{
    private static Logger LOG = LoggerFactory.getLogger(FactoryBuilding.class);

    public static FactoryBuilding create(Factory factory, String name) {
        return new FactoryBuilding(new HashSet(), name, factory, new FactoryBuildingConfiguration(),
        new FactoryBuildingConsumption());
    }
    
    private final Set<Assembly> assemblies;    
    private final String name;
    private final Factory factory;   
    //private final Maintenance maintenance;

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
        super(configuration, consumption);
        Objects.requireNonNull(assemblies);
        Objects.requireNonNull(name);
        Objects.requireNonNull(factory);                
        this.assemblies = assemblies;
        this.name = name;
        this.factory = factory;        
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
        super.logEvent(e);
        LOG.debug(this + " captured event " + e);        
    }

    /**
     * Returns the name of the factory building.
     * @return the name of the factory building
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the parent factory of the building
     * @return the parent factory
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * Returns the assemblies in the building.
     * @return assemblies in the building
     */
    public Set<Assembly> getAssemblies() {
        return new HashSet(assemblies);
    }    
    
    public void addAssemblies(List<Assembly> assemblies){
        this.assemblies.addAll(assemblies);
    }
}
