package cz.cvut.k36.omo.bartom47.smartfactory.consumables.materials;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;

/**
 * Represents material.
 * @since 1.0-BETA is used just as an empty shell for further development and
 * the constructor throws {@link UnsupportedOperationException}
 * @author Matej
 */
public abstract class Material extends Consumable{
    
    public Material(double unitCost, String name, String unitName, String unitShortcutName, String costCurrencyShortcut) {
        super(unitCost, name, unitName, unitShortcutName, costCurrencyShortcut);
        throw new UnsupportedOperationException("Not supported yet.");
    }           
}
