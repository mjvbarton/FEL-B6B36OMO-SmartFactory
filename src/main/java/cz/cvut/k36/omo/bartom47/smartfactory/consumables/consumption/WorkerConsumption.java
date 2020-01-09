package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Matej
 * @param <E> parameter of data node
 */
public class WorkerConsumption<E extends Worker> extends ConsumptionData<E> {        
    public WorkerConsumption() {
        super();
    }
            
    public String getName(){
        return ((Worker) getParent()).getName();
    }
    
    public String getAssembly(){
        return ((Worker) getParent()).getAssembly().getName();
    }
    
    public List<ConsumableConsumption> getConsumables(){
        return (List<ConsumableConsumption>) getParent().getConsumables().stream()
                .map(consumable -> {
                    Consumable c = (Consumable) consumable;
                    return c.getConsumption();
                }).collect(Collectors.toList());                                
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
