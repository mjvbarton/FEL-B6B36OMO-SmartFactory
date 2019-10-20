/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.hw.hw02;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matej
 */
public class OMOSetTest {
    private TestSet instance;
    private final Random randGen = new Random();
    
    class TestSet extends OMOSet implements OMOSetView{
        
    }
    
    public OMOSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TestSet();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of add method, of class OMOSet.
     */
    @Test
    public void testAdd_someElement_elementAddedToSet() {        
        System.out.println("testAdd_someElement_elementAddedToSet");
        
        // ARRANGE       
        int element = randGen.nextInt();
        
        // ACT 
        instance.add(element);
        
        // ASSERT
        Assert.assertTrue("Element found in the set:", instance.set.contains(element));
    }

    /**
     * Test of remove method, of class OMOSet.
     */
    @Test
    public void testRemove_specifiedElemnt_setDoesNotContainElement() {
        System.out.println("testRemove_specifiedElemnt_setDoesNotContainElement");
        
        // ARRANGE
        for(int i = 1; i <= 5; i++){
            instance.set.add(i);
        }
        
        // ACT
        int checkElement = 2; // This could be set 
        instance.remove(checkElement);
        
        // ASSERT
        Assert.assertFalse("Element not found in the set:", instance.set.contains(checkElement));
    }

    /**
     * Test of contains method, of class OMOSet.
     */
    @Test
    public void testContains_elementInTheSet_trueReturned() {
        System.out.println("contains");
        
        // ARRANGE
        int element = randGen.nextInt();
        instance.set.add(element);
        
        // ACT
        boolean expectedResult = instance.set.contains(element);
        boolean returnedResult = instance.contains(element);
        
        // ASSERT
        Assert.assertEquals("Function represents Set.contains(Object) function:", expectedResult, returnedResult);        
    }
    
    /**
     * Test of contains method, of class OMOSet.
     */
    @Test
    public void testContains_elementNotInTheSet_trueReturned() {
        System.out.println("testContains_elementNotInTheSet_trueReturned");
        
        // ARRANGE
        int element = randGen.nextInt();        
        
        // ACT
        boolean expectedResult = instance.set.contains(element);
        boolean returnedResult = instance.contains(element);
        
        // ASSERT
        Assert.assertEquals("Function represents Set.contains(Object) function:", expectedResult, returnedResult);        
    }

    /**
     * Test of toArray method, of class OMOSet.
     */
    @Test
    public void testToArray_arrayCreated_originalModified_childModifiedToo() {
        System.out.println("testToArray_arrayCreated_originalModified_childModifiedToo");        
        
        // ARRANGE
        for(int i = 1; i <= 20; i++){
            instance.add(i);
        }
        int removedElement = 13;
        
        // ACT
        int[] testArray = instance.toArray();
        instance.remove(removedElement);                
        boolean contains = false;
        for(int i = 0; i < testArray.length; i++){
            contains = testArray[i] == removedElement;
        }
        
        // ASSERT
        Assert.assertFalse("Element " + removedElement + " not included in final array:", contains);
        
    }

    /**
     * Test of copy method, of class OMOSet.
     */
    @Test
    public void testCopy_newInstanceCreated_parentModified_childNotModified() {
        System.out.println("testCopy_newInstanceCreated_parentModified_childNotModified");
        
        // ARRANGE
        for(int i = 1; i <= 20; i++){
            instance.add(i);
        }
        int addedElement = 21;
        
        // ACT
        OMOSetView newSet = instance.copy();
        instance.add(addedElement);
        
        // ASSERT
        boolean expected = instance.contains(addedElement);
        boolean returned =  newSet.contains(addedElement);
        Assert.assertNotEquals("Element found in parent set but not in returned set.", expected, returned);
        
    }
    
}
