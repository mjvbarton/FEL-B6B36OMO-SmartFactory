package cz.cvut.k36.omo.hw.hw02;

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
        int[] arrA = setA.toArray();
        int[] arrB = setB.toArray();
        int[] result = new int[arrA.length + arrB.length];
        for(int i = 0; i < arrA.length; i++){
            result[i] = arrA[i];
        }
        for(int i = 0; i < arrB.length; i++){
            result[arrA.length + i] = arrB.length;
        }
        return result;
    }

    @Override
    public OMOSetView copy() {
        return new OMOSetUnion(setA.copy(), setB.copy());
    }
}
