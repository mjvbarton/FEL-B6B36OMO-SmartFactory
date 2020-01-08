/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.events;

import cz.cvut.k36.omo.bartom47.smartfactory.HierarchyNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for {@link EventPropagator} event mechanism implemented in {@link HierarchyNode}
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class EventPropagatorTest {
    
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    public HierarchyNode node;
    
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    public PropagatableEvent propagatableEvent;
        
    public EventPropagatorTest() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
        Mockito.reset();
    }

    /**
     * Test of propagate method, of class HierarchyNode.
     */
    @Test
    public void testPropagatableEventIsHandledAndThenPropagated() {
        doNothing().when(node).handle(any(Event.class));
        doNothing().when(node).propagate(any(PropagatableEvent.class));
        node.handle(propagatableEvent);
        verify(node, times(1)).handle(any(PropagatableEvent.class));
        verify(node, times(1)).propagate(any(PropagatableEvent.class));
        verify(node, times(1)).handle(any(Event.class));
    }         
}
