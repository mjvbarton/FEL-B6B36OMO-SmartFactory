package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumableConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumptionDataContainer;
import java.util.Objects;

/**
 * Represents a consumable entity that can be consumed by the consumer.
 * @author Matej
 */
// TODO: Add javadoc for getters
public abstract class Consumable implements ConsumptionDataContainer<ConsumableConsumption>{
    private final double unitCost;
    private final String name;
    private final String unitName;
    private final String unitShortcutName;
    private final String costCurrencyShortcut;

    /**
     * Creates new consumable.
     * @param unitCost price for one unit
     * @param name name of the consumable
     * @param unitName name of the unit used by the consumable
     * @param unitShortcutName 
     * @param costCurrencyShortcut 
     */
    protected Consumable(double unitCost, String name, String unitName, String unitShortcutName, String costCurrencyShortcut) {        
        Objects.requireNonNull(name);
        Objects.requireNonNull(unitName);
        Objects.requireNonNull(unitShortcutName);
        Objects.requireNonNull(costCurrencyShortcut);
        this.unitCost = unitCost;
        this.name = name;
        this.unitName = unitName;
        this.unitShortcutName = unitShortcutName;
        this.costCurrencyShortcut = costCurrencyShortcut;
    }
   
    public double getUnitCost() {
        return unitCost;
    }

    public String getName() {
        return name;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getUnitShortcutName() {
        return unitShortcutName;
    }

    public String getCostCurrencyShortcut() {
        return costCurrencyShortcut;
    }        

    @Override
    public ConsumableConsumption getConsumption() {
        return new ConsumableConsumption(this);
    }
}
