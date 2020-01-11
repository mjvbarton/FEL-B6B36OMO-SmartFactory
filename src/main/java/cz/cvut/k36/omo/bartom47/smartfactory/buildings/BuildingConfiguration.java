package cz.cvut.k36.omo.bartom47.smartfactory.buildings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.assembly.AssemblyConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConfigurationData;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represents configuration for {@link Building}.
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingConfiguration extends ConfigurationData<Building>{
    
    public BuildingConfiguration() {
        super();
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getFactoryName(){
        //return getParent().getParent().getName();
        return null;
    }
    
    public Collection<AssemblyConfiguration> getAssemblies(){
        return getParent().getChildren().stream()
                .map(assembly -> assembly.getConfiguration())
                .collect(Collectors.toSet());
    }
    
}
