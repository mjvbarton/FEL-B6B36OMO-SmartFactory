package cz.cvut.k36.omo.bartom47.smartfactory.production;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the series of the {@link Product}
 * @author Matej
 * @param <P> the type of product in series
 */
public class Series<P extends Product> {
    private static Logger LOG = LoggerFactory.getLogger(Series.class);
    
    private final P product;
    private final int countProducts;
    private int toProduce;

    /**
     * Creates new serie.
     * @param product the product
     * @param countProducts 
     */
    // TODO: Add validation
    public Series(P product, int countProducts) {
        this.product = product;
        this.countProducts = countProducts;
        toProduce = countProducts;
    }
    
    
    
    /**
     * Checks if the serie has more products to produce.
     * @return {@code true} if the serie has no products to produces, 
     * {@code false} otherwise
     */
    public boolean hasNoProductsToProduce(){                
        return toProduce == 0;
    }
    
    /**
     * Updates the number of products.     
     */
    public void updateToProduce(){        
        toProduce -= product.getProductsPerTick();
        LOG.debug("Number of 'toProduce' updated to " + toProduce);
    }
}
