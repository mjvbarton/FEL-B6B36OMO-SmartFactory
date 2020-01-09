package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representing consumption data for {@link Factory}
 * @author Matej
 */
public class FactoryConsumption extends ConsumptionData<Factory>{
    
    public FactoryConsumption() {
        super();
    }
    
    public String getName(){
        return Factory.class.getSimpleName();
    }
    
    public List<FactoryBuildingConsumption> getBuildings(){
        return parentNode.getBuildings().stream()
                .map(building -> building.getConsumption())
                .collect(Collectors.toList());
    }
    
    public Double sumCost(){
        return getBuildings().stream()
                .mapToDouble(building -> building.getSumCost())
                .sum();
    }    
}
