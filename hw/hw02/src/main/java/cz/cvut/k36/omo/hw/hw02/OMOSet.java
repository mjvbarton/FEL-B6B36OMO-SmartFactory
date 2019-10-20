package cz.cvut.k36.omo.hw.hw02;

import java.util.HashSet;

/**
 *
 * @author Matej
 */
public class OMOSet extends OMOSetBase implements OMOSetView{
    private final HashSet<Integer> set = new HashSet();
    
    @Override
    public void add(int element) {
        set.add(element);
    }

    @Override
    public void remove(int element) {
        set.remove(element);
    }

    @Override
    public boolean contains(int element) {
        return set.contains(element);
    }

    @Override
    public int[] toArray() {
        int[] array = new int[]{};
        for(Integer element : set){
            array.
        }
    }

    @Override
    public OMOSetView copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // metody rozhraní OMOSetView a OMOSetBase
    
}
