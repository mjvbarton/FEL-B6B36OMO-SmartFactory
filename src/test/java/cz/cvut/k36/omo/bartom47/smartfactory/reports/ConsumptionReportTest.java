package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Electricity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.ConsumableConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.PersonWorker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matej
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ConsumptionReportTest {
    private static Logger LOG = LoggerFactory.getLogger(ConsumptionReportTest.class);        
    private static Boolean CLEAN_DIR = true;
    @Mock
    private Tick tick;
    
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Consument worker;
            
    @Mock
    private Assembly mockAssembly;            
    
    @BeforeClass
    @AfterClass
    public static void cleanDirectory() throws IOException{
        if(CLEAN_DIR)
            FileUtils.cleanDirectory("src/main/resources/reports/consumption/");
    }
        
    @Before
    public void setUp() {
        when(tick.getTickId()).thenReturn(1);
        
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of getResourcePath method, of class ConsumptionReport.
     */
    @Test
    public void testGenerateGeneratesYAMLFileForImplementedConsumableConsumption() {        
        ConsumableConsumption cc = new Electricity(worker, 2).getConsumption();
        doReturn("test consumable").when(cc).getName();
        doReturn("test consument").when(cc).getConsument();
        doReturn("testers").when(cc).getUnitName();        
        doReturn(6).when(cc).getConsumed();
        doReturn("TSC").when(cc).getCurrency();        
        doReturn(123.90).when(cc).getCost();
        ConsumptionReport cr = spy(new ConsumptionReport(Tick.getTickCount()));                       
        String path = cr.generate(cc);                
        assertTrue(Files.exists(Paths.get(path)));
        LOG.info("File created.");        
        
    }
    
    @Test
    public void testGenerateGeneratesYAMLFileForWorkerConsumption() {
        when(mockAssembly.getName()).thenReturn("Mock assembly");
        Worker w = PersonWorker.create(mockAssembly, "Test");
        WorkerConsumption wC = w.getConsumption();
        ConsumptionReport cr = new ConsumptionReport(Tick.getTickCount());
        cr.generate(wC);
    }
           
}
