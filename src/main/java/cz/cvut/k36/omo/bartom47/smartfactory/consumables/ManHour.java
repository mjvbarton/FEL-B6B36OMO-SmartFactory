package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.workers.PersonWorker;

/**
 * Represented worked hours of {@link PersonWorker}
 * @author Matej
 */
public class ManHour extends Consumable{
    private static final double DEFAULT_UNIT_COST = 1.01;
    private static final String DEFAULT_UNIT_NAME = "man-hour";
    private static final String DEFAULT_UNIT_SHORTCUT = "m-h";
    private static final String DEFAULT_COST_CURRENCY = "CZK";

    /**
     * Creates new man hours consumable with class contstants.
     * @param consument
     * @param unitsConsumedPerTick
     * @see Consumable#Consumable(Consument, String, double, String, String, String, Integer) 
     */
    public ManHour(Consument consument, Integer unitsConsumedPerTick) {
        super(
                consument,
                ManHour.class.getSimpleName(),
                DEFAULT_UNIT_COST,
                DEFAULT_UNIT_NAME,
                DEFAULT_UNIT_SHORTCUT,
                DEFAULT_COST_CURRENCY,
                unitsConsumedPerTick
        );
    }
}
