package cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration;

import cz.cvut.k36.omo.bartom47.smartfactory.DataModelNode;
import java.util.Objects;

/**
 * Represents node in configuration data model.
 * @author Matej
 */
public abstract class ConfigurationData implements DataModelNode<ConfigurationDataContainer> {
    private final ConfigurationDataContainer parentNode;

    /**
     * Creates configuration data with parent node
     * @param parentNode 
     */
    protected ConfigurationData(ConfigurationDataContainer parentNode) {
        Objects.requireNonNull(parentNode);
        this.parentNode = parentNode;
    }        
    
    @Override
    public ConfigurationDataContainer getParent() {
        return parentNode;
    }
    
}
