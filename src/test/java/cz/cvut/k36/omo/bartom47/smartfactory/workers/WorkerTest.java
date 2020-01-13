package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WorkerTest {                
    
    @Mock
    private Assembly a;
    
    @Mock
    private Tick tick;        
    

    @Test
    public void testTickEventIsPropagatedAmongMultipleChildren() {
        Worker w = spy(PersonWorker.create(a, "test worker"));        
        Set<Consumable> cs = IntStream.range(0, 5).mapToObj(i -> {
            Consumable c = mock(Consumable.class);
            when(c.getParent()).thenReturn(w);            
            return c;        
        }).collect(Collectors.toSet());      
        doReturn(cs).when(w).getChildren();
        w.handle(tick);
        cs.forEach(c -> {
            verify(c).handle(any(Tick.class));
        });
    }            
}
