package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

/**
 * Represents oil consumed by consument.
 * @author Matej
 */
public final class Oil extends Consumable{
    private static final double DEFAULT_UNIT_COST = 106.00;
    private static final String DEFAULT_UNIT_NAME = "liter";
    private static final String DEFAULT_UNIT_SHORTCUT = "l";
    private static final String DEFAULT_COST_CURRENCY = "CZK";
    
    /**
     * Creates new electricity consumable with class contstants.    
     * @param consument
     * @param unitsConsumedPerTick
     * @see Consumable#Consumable(Consument, String, double, String, String,
     * String, Integer)
     */
    public Oil(Consument consument, Integer unitsConsumedPerTick) {
        super(
                consument,                
                Oil.class.getSimpleName(),
                DEFAULT_UNIT_COST,
                DEFAULT_UNIT_NAME,
                DEFAULT_UNIT_SHORTCUT,
                DEFAULT_COST_CURRENCY,
                unitsConsumedPerTick
        );
    }
}
