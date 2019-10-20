package cz.cvut.k36.omo.hw.hw02;

/**
 * This interface defines access to integer set.
 * 
 */
public interface OMOSetView {
   
   /**
    * Checks if specified element exists within the set
    * @param element - integer element that we are checking of
    * @return true if element exitsts within the set, false if not
    */
   boolean contains(int element);
 
   /**
    * Returns copy (shallow copy) of the set elements. The order does not matter.
    * @return integer array of set elements
    */
   int[] toArray();
 
   /**
    * Returns copy (deep copy) of the set itself. The set genertated by this set would not be modified if original set was modified.
    * @return completely new object of itself
    */
   OMOSetView copy();
}
