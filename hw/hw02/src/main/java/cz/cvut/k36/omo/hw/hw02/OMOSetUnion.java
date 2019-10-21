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
        OMOSetIntersection intersect = new OMOSetIntersection(setA, setB);
        OMOSetComplement compl = new OMOSetComplement(setA, setB);
        int[] arrIntersect = intersect.toArray();
        int[] arrCompl = compl.toArray();
        int[] result = new int[arrIntersect.length + arrCompl.length];
        
        for(int i = 0; i < arrIntersect.length; i++){
            result[i] = arrIntersect[i];
        }
        
        for(int i = 0; i < arrCompl.length; i++){
            result[arrIntersect.length + i] = arrCompl[i];
        }
        
        return result;
    }

    @Override
    public OMOSetView copy() {
        return new OMOSetUnion(setA.copy(), setB.copy());
    }
}
