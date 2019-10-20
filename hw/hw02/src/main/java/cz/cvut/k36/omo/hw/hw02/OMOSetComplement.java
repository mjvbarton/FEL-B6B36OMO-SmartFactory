package cz.cvut.k36.omo.hw.hw02;

/**
 *
 * @author Matej
 */
// třída reprezentující A\B: doplněk množiny B vzhledem k množině A:  A\B = { x | x?A ? x?B }
public class OMOSetComplement implements OMOSetView{
    /**
     * Creates new complement set to setA and set B given.
     * It performs this set operation {@code setA - setB}
     * @param setA a <b>REFERENCE</b> to set A
     * @param setB a <b>REFERENCE</b> to set B
     */
    OMOSetComplement(OMOSetView setA, OMOSetView setB){
        
    }

    // metody rozhrani OMOSetView

    @Override
    public boolean contains(int element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OMOSetView copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
