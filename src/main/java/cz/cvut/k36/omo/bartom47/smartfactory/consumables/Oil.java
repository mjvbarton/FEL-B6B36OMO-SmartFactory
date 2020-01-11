/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

/**
 *
 * @author Matej
 */
//TODO: Singletonize
public final class Oil extends Consumable{
    private static final double DEFAULT_UNIT_COST = 106.00;
    private static final String DEFAULT_UNIT_NAME = "liter";
    private static final String DEFAULT_UNIT_SHORTCUT = "l";
    private static final String DEFAULT_COST_CURRENCY = "CZK";
    
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
