package cz.cvut.k36.omo.hw.hw02;

import java.util.HashSet;

/**
 *
 * @author Matej
 */
public class OMOSet extends OMOSetBase implements OMOSetView{
    protected final HashSet<Integer> set = new HashSet();
    
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
        int[] array = new int[set.size()];
        int cursor = 0;
        for(Integer element : set){
            array[cursor] = element;
            cursor++;
        }
        return array;
    }

    @Override
    public OMOSetView copy() {
        OMOSetBase instance = new OMOSet();
        for(Integer element : set){
            int value = element;
            instance.add(value);
        }
        return instance;
    }
    
    
}
