/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Product;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssemblyTest {       
    
    @Mock
    private Building b;
    
    @Mock
    private Worker worker;
    
    @Mock
    private Product product;
    
    @Mock
    private AssemblyState assemblyState;
    
    @Mock
    private Queue<Series> workPlan;
    
    @Spy
    private LinkedList<Worker> activeWorkers;
    
    @Mock
    private NotWorkingWorkers nonActiveWorkers;
        
    private Assembly assembly;
    
    
    @Mock
    private Tick tick;
    
    @Before
    public void setUp(){
        assembly = new Assembly(b, "test assembly", 1, assemblyState, 
                workPlan, activeWorkers, nonActiveWorkers);
    }
    
    @After
    public void clear(){
        
    }

    @Test
    public void testTickEventIsPropagatedAmongMultipleChildren() {
        Set<Worker> ws = IntStream.range(0, 5).mapToObj(i -> {
            return mock(Worker.class);
        }).collect(Collectors.toSet());
        Assembly a = Assembly.create(b, "test assembly", 1);
        a.addWorker(ws);
        a.handle(tick);
        ws.forEach(w -> {
            verify(w).handle(any(Tick.class));
        });
    }
    
    @Test
    public void testReorganizeWorkersPutsWorkersFromProductSequenceToEmptyActiveWorkers(){
        Queue<Class<? extends Worker>> testSequence = new LinkedList(
              IntStream.range(0, 5).mapToObj(i -> Worker.class)
                .collect(Collectors.toList())
        );                
        doReturn(Boolean.TRUE).when(activeWorkers).isEmpty();
        doReturn(testSequence).when(product).getWorkerSequence();
        doReturn(worker).when(nonActiveWorkers).getWorker(any(Class.class));
        assembly.reorganizeWorkers(product);
        verify(nonActiveWorkers, times(5)).getWorker(any(Class.class));
        ArgumentCaptor<Worker> captor = ArgumentCaptor.forClass(Worker.class);       
        verify(activeWorkers, times(5)).add(captor.capture());
        assertEquals(testSequence.size(), captor.getAllValues().size());
    }        
}
