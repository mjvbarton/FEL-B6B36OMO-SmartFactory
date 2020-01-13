package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.ConsumableConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConfigurationData;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represents configuration data for {@link Worker}. This object is serialized
 * to {@code *.yaml} file.
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerConfiguration extends ConfigurationData<Worker>{
    
    public WorkerConfiguration() {
        super();
    }      
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getAssembly(){
        return getParent().getParent().getName();
    }
    
    public Collection<ConsumableConfiguration> getConsumables(){
        return getParent().getConsumables().stream()
                .map(consumable -> consumable.getConfiguration())
                .collect(Collectors.toSet());
    }
}
