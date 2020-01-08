package cz.cvut.k36.omo.bartom47.smartfactory;


/**
 * Represents a node of data model
 * @author Matej
 * @param <E> type of parent object
 */
public interface DataModelNode<E> {    
    
    /**
     * Returns the parent node of data model.
     * @return parent node, <b>Notice:</b> Must not contain {@code null}
     */
    E getParent();
    
}
