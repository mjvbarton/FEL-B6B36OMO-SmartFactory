package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;
import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
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
    
    public String getFactoryBuildingName(){
        return getParent().getFactoryBuilding().getName();
    }
    
    public List<WorkerConsumption> getWorkers(){
        return getParent().getWorkers().stream()
                .map(worker -> worker.getConsumption())                
                .collect(Collectors.toList());
    }
    
    public Double getSumCost(){
        return getWorkers().stream()
                .mapToDouble(wC -> wC.getSumCost())
                .sum();
    }
    
}
