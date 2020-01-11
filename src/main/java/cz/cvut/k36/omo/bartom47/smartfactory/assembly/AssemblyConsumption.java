package cz.cvut.k36.omo.bartom47.smartfactory.assembly;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConsumptionData;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerConsumption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents consumption data for {@link Assembly}.
 * @author Matej
 */
public class AssemblyConsumption extends ConsumptionData<Assembly>{
    
    public AssemblyConsumption() {
        super();
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getFactoryBuildingName(){
        return getParent().getParent().getName();
    }
    
    public List<WorkerConsumption> getWorkers(){
        return getParent().getChildren().stream()
                .map(worker -> worker.getConsumption())                
                .collect(Collectors.toList());
    }
    
    public Double getSumCost(){
        return getWorkers().stream()
                .mapToDouble(wC -> wC.getSumCost())
                .sum();
    }
    
}
