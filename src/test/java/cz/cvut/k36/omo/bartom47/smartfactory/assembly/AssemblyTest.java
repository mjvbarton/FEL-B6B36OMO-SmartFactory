/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssemblyTest {       
    
    @Mock
    private Building b;
    
    @Mock
    private Tick tick;

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
    
}
