package cz.cvut.k36.omo.hw.hw02;

import java.util.ArrayList;

/**
 *
 * @author Matej
 */
// třída reprezentující A\B: doplněk množiny B vzhledem k množině A:  A\B = { x | x?A ? x?B }
public class OMOSetComplement implements OMOSetView{
    protected OMOSetView setA;
    protected OMOSetView setB;
    
    /**
     * Creates new complement set to setA and set B given.
     * It performs this set operation {@code setA - setB}
     * @param setA a <b>REFERENCE</b> to set A
     * @param setB a <b>REFERENCE</b> to set B
     */
    OMOSetComplement(OMOSetView setA, OMOSetView setB){
        this.setA = setA;
        this.setB = setB;
    }

    @Override
    public boolean contains(int element) {
        return setA.contains(element) && !setB.contains(element);
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> rawResult = new ArrayList();
        for(Integer element : setA.toArray()){
            if(setB.contains(element) == false){
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
        return new OMOSetComplement(setA.copy(), setB.copy());
    }
}
