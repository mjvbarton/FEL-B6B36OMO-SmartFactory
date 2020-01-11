package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConfigurationData;

/**
 * Represents configuration data API for {@link Consumable}
 * @author Matej
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumableConfiguration extends ConfigurationData<Consumable>{

    public ConsumableConfiguration() {
        super();
    }     
    
    public String getName(){
        return getParent().getName();
    }
    
    @JsonIgnore //TODO: Implement ignoring this when used by parent.
    public String getConsument(){
        return getParent().getParent().getName();
    }
    
    public Integer getConsumptionPerUnit(){
        return getParent().getParent().getConsumableIncrement(getParent());
    }
    
    public String getUnitName(){
        return getParent().getUnitName();
    }
    
    public String getUnitShortcut(){
        return getParent().getUnitShortcut();                
    }
    
    public Double getUnitCost(){
        return getParent().getUnitCost();
    }
    
    public String getCostCurrencyShortcut(){
        return getParent().getCostCurrencyShortcut();
    }        
        
    
}
