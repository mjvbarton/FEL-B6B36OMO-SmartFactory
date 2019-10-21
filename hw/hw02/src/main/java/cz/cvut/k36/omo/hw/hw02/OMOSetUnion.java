package cz.cvut.k36.omo.hw.hw02;

import java.util.ArrayList;

/**
 *
 * @author Matej
 */
public class OMOSetUnion implements OMOSetView{
    protected OMOSetView setA;
    protected OMOSetView setB;
    
    /**
     * Creates set union from set A and set B given.
     * @param setA a <b>REFERENCE</b> to set A
     * @param setB a <b>REFERENCE</b> to set B
     */
    OMOSetUnion(OMOSetView setA, OMOSetView setB){
        this.setA = setA;
        this.setB = setB;
    }
    
    @Override
    public boolean contains(int element) {
        return setA.contains(element) || setB.contains(element);
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> rawResult = new ArrayList();
        for(Integer element : setA.toArray()){
            rawResult.add(element);
        }
        for(Integer element : setB.toArray()){
            if(setA.contains(element) == false){
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
        return new OMOSetUnion(setA.copy(), setB.copy());
    }
}
