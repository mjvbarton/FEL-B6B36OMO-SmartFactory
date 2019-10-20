package cz.cvut.k36.omo.hw.hw02;

/**
 * Defines an option to add and remove elements of the set.
 */
abstract class OMOSetBase implements OMOSetView {
    /**
     * Adds an element to the set
     * @param element integer object to be added
     */
    public abstract void add(int element);
    
    /**
     * Removes element from the set
     * @param element element object to be removed
     */
    public abstract void remove(int element); //odebere prvek "element" z množiny
}