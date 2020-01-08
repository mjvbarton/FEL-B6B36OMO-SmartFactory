package cz.cvut.k36.omo.bartom47.smartfactory.production;

import cz.cvut.k36.omo.bartom47.smartfactory.consumables.materials.Material;
import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents product that can be produced by the factory.
 * @since 1.0-BETA {@link #workerSequence} and {@link #materials} are not used yet
 * @author Matej
 */
public abstract class Product {
    
    private static Logger LOG = LoggerFactory.getLogger(Product.class);
    
    private final int productsPerTick;    
    private final WorkerSequence workerSequence;
    private final List<Material> materials;

    /**
     * Creates new product with specified value of {@link #productsPerTick}
     * @param productsPerTick number of products that are produces between ticks
     */
    // TODO: Add more validation of 'productsPerTick'
    public Product(int productsPerTick) {
        Objects.requireNonNull(productsPerTick);
        this.productsPerTick = productsPerTick;
        this.workerSequence = null;
        this.materials = null;
    }

    /**
     * Number of products produced by ticks.
     * @return number of products that are produces between ticks
     */
    int getProductsPerTick() {
        return productsPerTick;
    }

    /**
     * Gets the sequence of worker classes needed to be on {@link Assembly}.
     * @since 1.0-BETA not used, template for development of version {@code 1.0},
     * throws {@link UnsupportedOperationException} by default
     * @throws UnsupportedOperationException always, because not implemented
     * @return {@link Queue} of worker classes needed to produce this product by
     * the {@link Assembly}
     */
    // TODO: Implement NonActiveWorkersPool after 2020-01-09
    public WorkerSequence getWorkerSequence() {        
        LOG.error("Unsupported method 'getWorkerSequence' called.", new UnsupportedOperationException("Not supported yet."));
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Gets the list of {@link Material} used by this product.
     * @since 1.0-BETA not used, template for further development
     * throws {@link UnsupportedOperationException} by default
     * @throws UnsupportedOperationException always, because not implemented
     * @return 
     */
    public Material getMaterials() {
        LOG.error("Unsupported method 'getMaterials' called.", new UnsupportedOperationException("Not supported yet."));
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
