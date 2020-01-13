package cz.cvut.k36.omo.bartom47.smartfactory.consumables;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Event;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsumableTest {
    
    @Mock
    private Worker worker;
    
    @Mock(answer=Answers.CALLS_REAL_METHODS)
    private Consumable consumable;
    
    @Mock
    private Tick tick;
    
    public ConsumableTest() {
    }

    /**
     * Test of handle method, of class PersonWorker.
     */
    @Test
    public void testHandleTickUpdatesConsumablesConsumedAndCost() {
        final Integer expectedConsumed = 1;
        final Double expectedCost = 1.0;
        ConsumableConsumption consumption = spy(new ConsumableConsumption());
        doReturn(worker).when(consumable).getParent();
        doReturn(consumption).when(consumable).getConsumption();
        doReturn(consumable).when(consumption).getParent();
        doReturn(
                Arrays.asList(consumable)
                        .stream().collect(Collectors.toSet())
        ).when(worker).getChildren();
        doReturn(expectedConsumed).when(worker).getConsumableIncrement(any(Consumable.class));
        doReturn(expectedCost).when(consumable).getUnitCost();
        doCallRealMethod().when(consumable).handle(any(Event.class));
        doNothing().when(consumable).logEvent(any(Event.class));
        assertNotNull(consumable.getParent());
        IntStream.range(1, 6).forEach(i -> {
            doReturn("Tick['" + i + "']").when(tick).toString();
            Integer consumed = i * expectedConsumed;
            Double cost = i * expectedCost;
            consumable.handle(tick);            
            verify(consumable, times(i)).handle(tick);
            verify(consumption, times(i)).increment(any(Integer.class));
            doCallRealMethod().when(consumption).getCost();
            assertNotEquals(0, consumption.getParent().getUnitCost());
            assertEquals(tick.toString(), consumed, consumption.getConsumed());
            assertEquals(tick.toString(), cost, consumption.getCost());
        });
    }
    
}
