package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.BuildingConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConfigurationData;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Representing configuration data for {@link Factory}
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactoryConfiguration extends ConfigurationData<Factory>{
    
    public FactoryConfiguration() {
        super();
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    //TODO: Implement ignoring chain elements
    public Collection<BuildingConfiguration> getBuildings(){
        return getParent().getChildren().stream()
                .map(building -> building.getConfiguration())
                .collect(Collectors.toSet());
    }
}
