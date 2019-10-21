package cz.cvut.k36.omo.hw.hw02;

import java.util.ArrayList;

/**
 *
 * @author Matej
 */
// třída reprezentující množinu sudých čísel
public class OMOSetEven implements OMOSetView{
    private final OMOSetView setA;
    
    /**
     * Creates new OMOSetEven from given set
     * @param setA a <b>REFERENCE</b> to some set
     */
    OMOSetEven(OMOSetView setA){
        this.setA = setA;
    }

    @Override
    public boolean contains(int element) {
        return setA.contains(element) && element % 2 == 0;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> rawResult = new ArrayList();
        for (Integer element : setA.toArray()) {
            if (element % 2 == 0) {
                rawResult.add(element);
            }
        }
        int[] result = new int[rawResult.size()];
        for (int i = 0; i < rawResult.size(); i++) {
            result[i] = rawResult.get(i);
        }
        return result;
    }

    @Override
    public OMOSetView copy() {
        return new OMOSetEven(setA.copy());
    }
}
