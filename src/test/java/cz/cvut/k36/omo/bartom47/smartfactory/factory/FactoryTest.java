package cz.cvut.k36.omo.bartom47.smartfactory.factory;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.PropagatableEvent;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
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

@RunWith(MockitoJUnitRunner.class)
public class FactoryTest {       
    
    @Mock
    private Worker w;
    
    @Test
    public void testTickEventIsPropagatedAmongMultipleChildren() {
        Set<Building> bs = IntStream.range(0, 5).mapToObj(i -> {
            return mock(Building.class);
        }).collect(Collectors.toSet());
        Factory f = Factory.create("test factory");
        f.addBuilding(bs);
        f.tick();
        bs.forEach(b -> {
            verify(b).handle(any(Tick.class));
        });
    }
    
    @Test
    public void testTickEventPropagationIsHandledByWorkerWhenTriggeredFromFactory() {
        Factory f = Factory.create("test factory");
        Building b = Building.create(f, "test building");
        Assembly a = Assembly.create(b, "test assembly", 1);
        a.addWorker(w);
        f.tick();
        verify(w).handle(any(PropagatableEvent.class));
    }
       
}
