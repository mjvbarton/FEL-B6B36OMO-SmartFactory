package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConsumptionReport;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Integration test of smart factory.
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class SmartFactoryIntegrationTest {
    
    public SmartFactoryIntegrationTest() {
    }
                
    @Before
    public void setUp() {
        Tick.resetCounter();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class SmartFactory.
     */
    @Test
    public void testMain() {
    }

    /**
     * Test of run method, of class SmartFactory.
     */
    @Test
    public void testRun() {
    }

    /**
     * Test of generateConsumptionReport method, of class SmartFactory.
     */
    @Test
    public void testGenerateConsumptionReport() {        
        Factory f = Environment.generateFactoryA();
        
        for(int i = 0; i < 20; i++){
            f.tick();
            ConsumptionReport cr = new ConsumptionReport(i);
            cr.generate(f.getConsumption());
        }                     
    }
    
}
