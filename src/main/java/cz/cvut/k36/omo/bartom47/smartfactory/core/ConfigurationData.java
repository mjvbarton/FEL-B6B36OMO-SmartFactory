package cz.cvut.k36.omo.bartom47.smartfactory.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.k36.omo.bartom47.smartfactory.core.DataModelNode;
import java.util.Objects;

/**
 * Represents node in configuration data model.
 * @author Matej
 * @param <E> type of configuration data container
 */
public abstract class ConfigurationData<E extends ConfigurationDataContainer> implements DataModelNode<E> {
    private E parentNode;
    private boolean isParentSet = false;

    /**
     * Creates configuration data with parent node 
     */
    public ConfigurationData() {
        
    }        
    
    @JsonIgnore
    @Override
    public E getParent() {
        return parentNode;
    }
    
    public void setParent(E parentNode){
        if(!isParentSet){
            this.parentNode = parentNode;
            isParentSet = true;
        }
            
    }
    
}
