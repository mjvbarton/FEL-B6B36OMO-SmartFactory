package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConfigurationReport;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConsumptionReport;
import cz.cvut.k36.omo.bartom47.smartfactory.reports.ConsumptionReportTest;
import cz.cvut.k36.omo.bartom47.smartfactory.sampledata.meac.Meac;
import cz.cvut.k36.omo.bartom47.smartfactory.sampledata.skodaauto.SkodaAuto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.codehaus.plexus.util.FileUtils;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration test of Smart Factory.
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class SmartFactoryIntegrationTest {
    private static Logger LOG = LoggerFactory.getLogger(ConsumptionReportTest.class);
    
    /*
     * Set this to false to see generated reports.
     */
    private static Boolean CLEAN_DIR = true;
    
    @BeforeClass
    @AfterClass
    public static void cleanDirectory() throws IOException{
        if(CLEAN_DIR){
            FileUtils.cleanDirectory("src/main/resources/reports/consumption/");
            FileUtils.cleanDirectory("src/main/resources/reports/configuration/");
            File f = new File("src/main/resources/reports/consumption/.gitkeep");            
            f.createNewFile();
            f = new File("src/main/resources/reports/configuration/.gitkeep");
            f.createNewFile();            
        }
    }    
                        
    @Before
    public void setUp() {
        Tick.resetCounter();
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
            String path = cr.generate(f.getConsumption());            
            assertTrue(Files.exists(Paths.get(path)));
        }                     
    }
    
    @Test
    public void testGenerateConfigurationReport(){
        Factory f = Environment.generateFactoryA();
        ConfigurationReport cr = new ConfigurationReport(1);
        String path = cr.generate(f.getConfiguration());            
        assertTrue(Files.exists(Paths.get(path)));
    }
            
    private void integrationTestOfFactoryCreatesReports(Factory f){        
        ConfigurationReport cr = new ConfigurationReport(0);
        String path = cr.generate(f.getConfiguration());
        assertTrue(Files.exists(Paths.get(path)));
        assertFalse(f.getChildren().isEmpty());
        f.getChildren().forEach(building ->{
            assertFalse(building.getChildren().isEmpty());
        });
        for(int i = 0; i < 50; i++){
            Tick t = f.tick();
            if(true){
               ConsumptionReport csr = new ConsumptionReport(t.getTickId());
               String csrPath = csr.generate(f.getConsumption());
               assertTrue(Files.exists(Paths.get(csrPath)));
               ConfigurationReport cfg = new ConfigurationReport(t.getTickId());
               String cfgPath = cfg.generate(f.getConfiguration());
               assertTrue(Files.exists(Paths.get(cfgPath)));
            }            
        }
    }
    
    @Test(expected = Test.None.class)
    public void integrationTestOfSkodaAutoCreatesReports() {
        integrationTestOfFactoryCreatesReports(SkodaAuto.createSkodaAuto());
    }
    
    @Test(expected = Test.None.class)
    public void integrationTestOfMeacCreatesReports() {
        integrationTestOfFactoryCreatesReports(Meac.create());
    }
    
}
