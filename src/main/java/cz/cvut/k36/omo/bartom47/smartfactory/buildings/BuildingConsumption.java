package cz.cvut.k36.omo.bartom47.smartfactory.buildings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.k36.omo.bartom47.smartfactory.assembly.AssemblyConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConsumptionData;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Represents a consumption data node for {@link Building}
 * @author Matej
 */
public class BuildingConsumption extends ConsumptionData<Building>{
    
    public BuildingConsumption() {
        super();
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getFactory(){
        return getParent().getParent().getName();
    }
    
    public List<AssemblyConsumption> getAssemblies(){
        return getParent().getChildren().stream()
                .map(assembly -> assembly.getConsumption())
                .collect(Collectors.toList());
    }
    
    public Double getSumCost(){
        return getAssemblies().stream()
                .mapToDouble(a -> a.getSumCost())
                .sum();
    }
    
}
