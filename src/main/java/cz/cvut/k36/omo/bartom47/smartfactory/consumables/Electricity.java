package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;

/**
 * Represents consumable electricity.
 * @author Matej
 */
public final class Electricity extends Consumable{
    private static final double DEFAULT_UNIT_COST = 1.01;
    private static final String DEFAULT_UNIT_NAME = "kilowatt per hour";
    private static final String DEFAULT_UNIT_SHORTCUT = "kW/h";
    private static final String DEFAULT_COST_CURRENCY = "CZK";
    
    /**
     * Creates new electricity consumable with class contstants.
     * @param consument
     * @param unitsConsumedPerTick
     * @see Consumable#Consumable(Consument, String, double, String, String, String, Integer) 
     */
    public Electricity(Consument consument, Integer unitsConsumedPerTick) {        
        super(
                consument,                
                Electricity.class.getSimpleName(),
                DEFAULT_UNIT_COST,
                DEFAULT_UNIT_NAME,
                DEFAULT_UNIT_SHORTCUT,
                DEFAULT_COST_CURRENCY,
                unitsConsumedPerTick
        );
    }    
}
