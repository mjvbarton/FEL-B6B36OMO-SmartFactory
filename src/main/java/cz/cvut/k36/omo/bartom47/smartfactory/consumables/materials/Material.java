package cz.cvut.k36.omo.bartom47.smartfactory.consumables.materials;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;

/**
 * Represents material.
 * @since 1.0-BETA is used just as an empty shell for further development and
 * the constructor throws {@link UnsupportedOperationException}
 * @author Matej
 */
public abstract class Material extends Consumable{
    
    public Material(Consument consument, double unitCost, String name, 
            String unitName, String unitShortcutName, 
            String costCurrencyShortcut, Integer unitsConsumedPerTick) {
        super(consument, name, unitCost, unitName, unitShortcutName, costCurrencyShortcut, unitsConsumedPerTick);
        throw new UnsupportedOperationException("Not supported yet.");
    }           
}
