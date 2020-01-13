package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConsumptionData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents consumption for {@link Consumable}
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumableConsumption extends ConsumptionData<Consumable>{
    private static Logger LOG = LoggerFactory.getLogger(ConsumableConsumption.class);    
    
    private Integer consumed;
    
    public ConsumableConsumption() {
        super();        
        consumed = 0;        
    }               

    public void setConsumed(Integer consumed) {
        Objects.requireNonNull(consumed);
        this.consumed = consumed;
    }        

    @JsonIgnore
    public void increment(Integer consumedUnitsPerTick){
        Objects.requireNonNull(consumedUnitsPerTick);
        if(consumedUnitsPerTick < 0)
            throw new IllegalArgumentException("Consumed per unit must be greater than zero.");
        this.consumed += consumedUnitsPerTick;
        LOG.debug("Consumed units per tick: " + consumedUnitsPerTick);
        LOG.debug("Consumed value: " + this.consumed);
    }
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getConsument(){
        return getParent().getName();
    }
    
    public String getUnitName() {
        return getParent().getUnitName();
    }
    
    public Integer getConsumed() {
        return consumed;
    }
                
    public String getCurrency(){
        return getParent().getUnitShortcut();
    }  
    
    public Double getCost() {
        LOG.debug("Cost per consumed unit: " + getParent().getUnitCost());
        return getParent().getUnitCost() * new Double(consumed);
    }
}
