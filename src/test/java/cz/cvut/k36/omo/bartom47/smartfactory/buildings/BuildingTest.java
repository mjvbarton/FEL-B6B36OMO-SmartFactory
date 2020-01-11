/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.buildings;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class BuildingTest {
       
    @Mock
    private Factory f;
    
    @Mock
    private Tick tick;

    @Test
    public void testTickEventIsPropagatedAmongMultipleChildren() {
        Set<Assembly> as = IntStream.range(0, 5).mapToObj(i -> {
            return mock(Assembly.class);
        }).collect(Collectors.toSet());
        Building b = Building.create(f, "test factory");
        b.addAssembly(as);
        b.handle(tick);
        as.forEach(a -> {
            verify(a).handle(any(Tick.class));
        });
    }
    
}
