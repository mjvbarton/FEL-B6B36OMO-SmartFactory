package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.ConsumableConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConsumptionData;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represents consumption data node for {@link Worker}. This object is serialized
 * to {@code *.yaml} file.
 * @author Matej 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerConsumption extends ConsumptionData<Worker> {        
    public WorkerConsumption() {
        super();
    }
            
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getAssembly(){
        return getParent().getParent().getName();
    }
    
    public Collection<ConsumableConsumption> getConsumables(){        
        return getParent().getConsumables().stream()
                .map(consumable -> consumable.getConsumption())
                .collect(Collectors.toSet());
    }
    
    public String getCurrency(){
        // TODO: Add global currency configuration from the controller
        return "CZK";
    }
    
    public Double getSumCost(){               
        return getConsumables().stream()
                .mapToDouble(c -> c.getCost())
                .sum();                
    }    
}
