package cz.cvut.k36.omo.hw.hw02;

import java.util.ArrayList;

/**
 *
 * @author Matej
 */
public class OMOSetIntersection implements OMOSetView{
    protected final OMOSetView setA;
    protected final OMOSetView setB;
    
    /**
     * Creates intersection from set A and set B given.
     * @param setA a <b>REFERENCE</b> to set A
     * @param setB a <b>REFERENCE</b> to set B
     */
    OMOSetIntersection(OMOSetView setA, OMOSetView setB){
        this.setA = setA;
        this.setB = setB;
    }

    @Override
    public boolean contains(int element) {
        return setA.contains(element) && setB.contains(element);
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> rawResult = new ArrayList();
        for(Integer element : setA.toArray()){
            if(setB.contains(element)){
                rawResult.add(element);
            }
        }
        int[] result = new int[rawResult.size()];
        for(int i = 0; i < rawResult.size(); i++){
            result[i] = rawResult.get(i);
        }
        return result;                
    }

    @Override
    public OMOSetView copy() {
        return new OMOSetIntersection(setA.copy(), setB.copy());
    }
}
