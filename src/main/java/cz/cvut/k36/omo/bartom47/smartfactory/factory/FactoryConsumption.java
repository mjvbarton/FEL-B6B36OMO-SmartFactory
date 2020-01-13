package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.core.ConsumptionData;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.BuildingConsumption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representing consumption data for {@link Factory}. This object
 * is serialized to *.yaml file.
 * @author Matej
 */
public class FactoryConsumption extends ConsumptionData<Factory>{
    
    public FactoryConsumption() {
        super();
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    public List<BuildingConsumption> getBuildings(){
        return getParent().getChildren().stream()
                .map(building -> building.getConsumption())
                .collect(Collectors.toList());
    }
    
    public Double getSumCost(){
        return getBuildings().stream()
                .mapToDouble(building -> building.getSumCost())
                .sum();
    }    
}
