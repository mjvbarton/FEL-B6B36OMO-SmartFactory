package cz.cvut.k36.omo.bartom47.smartfactory.consumables.configuration;

/**
 * Container of configuration data.
 * @author Matej
 * @param <P> type of data model node
 */
public interface ConfigurationDataContainer<P extends ConfigurationData> {
    /**
     * Returns configuration data model node
     * @return node of configuration data model
     */
    public P getConfiguration();
}
