package cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption;

import cz.cvut.k36.omo.bartom47.smartfactory.DataModelNode;
import java.util.Objects;

/**
 * Represents consumption data model node.
 * @author Matej
 */
public abstract class ConsumptionData implements DataModelNode<ConsumptionDataContainer>{
    private final ConsumptionDataContainer parentNode;

    /**
     * Creates configuration data with parent node
     *
     * @param parentNode
     */
    protected ConsumptionData(ConsumptionDataContainer parentNode) {
        Objects.requireNonNull(parentNode);
        this.parentNode = parentNode;
    }

    @Override
    public ConsumptionDataContainer getParent() {
        return parentNode;
    }
    
}
