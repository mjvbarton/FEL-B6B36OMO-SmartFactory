package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.FactoryBuilding;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Matej
 */
public class FactoryBuildingConsumption extends ConsumptionData<FactoryBuilding>{
    
    public FactoryBuildingConsumption(FactoryBuilding parentNode) {
        super();
    }
    
    public String getName(){
        return parentNode.getName();
    }
    
    public String getFactory(){
        return Factory.class.getSimpleName();
    }
    
    public List<AssemblyConsumption> getAssemblies(){
        return parentNode.getAssemblies().stream()
                .map(assembly -> assembly.getConsumption())
                .collect(Collectors.toList());
    }
    
    public Double getSumCost(){
        return getAssemblies().stream()
                .mapToDouble(a -> a.getSumCost())
                .sum();
    }
    
}
