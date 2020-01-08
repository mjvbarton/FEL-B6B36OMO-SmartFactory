package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

/**
 * Container of consumptopm data.
 * @author Matej
 * @param <P> type of data model node
 */
public interface ConsumptionDataContainer<P extends ConsumptionData> {
    
    /**
     * Returns consumption data model node
     * @return node of consumption data model
     */
    public P getConsumption();
    
}
