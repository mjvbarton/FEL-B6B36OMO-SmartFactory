package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.core.HierarchyNode;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.PropagatableEvent;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a consumable entity that can be consumed by the consumer.
 * @author Matej
 */
// TODO: Add javadoc for getters
public abstract class Consumable extends HierarchyNode<Consument, HierarchyNode, ConsumableConfiguration, ConsumableConsumption>{
    private static final Logger LOG = LoggerFactory.getLogger(Consumable.class);
    private final double unitCost;    
    private final String unitName;
    private final String unitShortcutName;
    private final String costCurrencyShortcut;    
        
    /**
     * Creates new consumable.
     * @param parent
     * @param unitCost price for one unit
     * @param name name of the consumable
     * @param unitName name of the unit used by the consumable
     * @param unitShortcutName shortcut of the unit
     * @param costCurrencyShortcut shortcut of the currency
     * @param unitsConsumedPerTick number of units consumed by {@link Consument} per
     * each {@link Tick}
     */
    protected Consumable(Consument parent, String name, double unitCost, String unitName, 
            String unitShortcutName, String costCurrencyShortcut, Integer unitsConsumedPerTick) {
        super(parent, name, new ConsumableConfiguration(), new ConsumableConsumption());        
        Objects.requireNonNull(unitName);
        Objects.requireNonNull(unitShortcutName);
        Objects.requireNonNull(costCurrencyShortcut);        
        this.unitCost = unitCost;        
        this.unitName = unitName;
        this.unitShortcutName = unitShortcutName;
        this.costCurrencyShortcut = costCurrencyShortcut;        
        parent.addConsumable(this, unitsConsumedPerTick);
    }
   
    public double getUnitCost() {
        return unitCost;
    }
   
    public String getUnitName() {
        return unitName;
    }

    public String getUnitShortcut() {
        return unitShortcutName;
    }

    public String getCostCurrencyShortcut() {
        return costCurrencyShortcut;
    }        

    @Override
    public Set<HierarchyNode> getChildren() {
       throw new UnsupportedOperationException("Consumable does not have any children nodes.");
    }

    @Override
    public void propagate(PropagatableEvent e) {
        LOG.trace("Responsibility chain of PropagagatableEvent ends here.");
        // do nothing
    }    
    
    @Override
    public void handle(Event e) {
        if(e instanceof Tick){
            logEvent(e);
            LOG.debug(this + " handled event " + e);
            getConsumption().increment(
                getParent().getConsumableIncrement(this)
            );
        } else {
            LOG.warn("Unhandled event at " + this);
        }
    }

    @Override
    public String toString() {
        return "Consumable['"+ getName() + "']";
    }        
}
