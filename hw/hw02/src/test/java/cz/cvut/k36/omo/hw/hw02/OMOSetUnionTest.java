/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.hw.hw02;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Matej
 */
public class OMOSetUnionTest {
    class TestSet extends OMOSetUnion{

        public TestSet(OMOSetView setA, OMOSetView setB) {
            super(setA, setB);
        }
                
    }
    
    private OMOSetBase setA;
    private OMOSetBase setB;
    private TestSet instance;
    
    private static SetGenerator setGen;
    
    public OMOSetUnionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        SetGenerator setGen = new SetGenerator();
        setA = setGen.getOMOSetNatural();
        setB = setGen.getOMOSetEven();
        
        instance = new TestSet(setA, setB);
    }
    
    @After
    public void tearDown() {
        setA = null;
        setB = null;
        instance = null;
    }

    /**
     * Test of contains method, of class OMOSetUnion.
     */
    @Test
    public void testContains_elementInSetA_elementInSetB_noAfterAction_trueReturned() {
        System.out.println("testContains_elementInSetA_elementInSetB_noAfterAction_trueReturned");       
        int element = 2;
        Assert.assertTrue("Element " + element + " in union:", instance.contains(element));
    }
    
    /**
     * Test of contains method, of class OMOSetUnion.
     */
    @Test
    public void testContains_elementInSetA_elementNotInSetB_noAfterAction_trueReturned() {
        System.out.println("testContains_elementInSetA_elementNotInSetB_noAfterAction_trueReturned");       
        int element = 3;
        Assert.assertTrue("Element " + element + " in union:", instance.contains(element));
    }
    
    /**
     * Test of contains method, of class OMOSetUnion.
     */
    @Test
    public void testContains_elementInSetA_elementNotInSetB_elementRemovedFromSetA_falseReturned() {
        System.out.println("testContains_elementInSetA_elementNotInSetB_elementRemovedFromSetA_falseReturned");       
        int element = 3;
        setA.remove(element);
        Assert.assertFalse("Element " + element + " not in union:", instance.contains(element));
    }
    
    /**
     * Test of contains method, of class OMOSetUnion.
     */
    @Test
    public void testContains_elementInSetA_elementInSetB_elementRemovedFromSetA_trueReturned() {
        System.out.println("testContains_elementInSetA_elementInSetB_elementRemovedFromSetA_trueReturned");       
        int element = 4;
        setA.remove(element);
        Assert.assertTrue("Element " + element + " in union:", instance.contains(element));
    }

    /**
     * Test of toArray method, of class OMOSetUnion.
     */
    @Ignore
    @Test
    public void testToArray() {
        System.out.println("toArray");
        OMOSetUnion instance = null;
        int[] expResult = null;
        int[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class OMOSetUnion.
     */
    @Ignore
    @Test
    public void testCopy() {
        System.out.println("copy");
        OMOSetUnion instance = null;
        OMOSetView expResult = null;
        OMOSetView result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
