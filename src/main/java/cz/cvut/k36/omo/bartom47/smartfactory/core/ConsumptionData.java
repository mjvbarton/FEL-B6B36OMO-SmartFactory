package cz.cvut.k36.omo.bartom47.smartfactory.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

/**
 * Represents consumption data model node.
 * @author Matej
 * @param <E> type of {@link ConsumptionDataContainer}
 */
public abstract class ConsumptionData<E extends ConsumptionDataContainer> implements DataModelNode<E>{
    protected E parentNode;
    private boolean isParentNodeSet= false;

    /**
     * Creates configuration data with parent node
     * 
     * @param parentNode
     */
    public ConsumptionData() {
    }
    
    @JsonIgnore
    @Override
    public E getParent() {
        return parentNode;
    }

    public void setParent(E parentNode) {
        Objects.requireNonNull(parentNode);
        if(!isParentNodeSet) {           
            this.parentNode = parentNode;
            isParentNodeSet = true;
        }
    }   
                                  
}
