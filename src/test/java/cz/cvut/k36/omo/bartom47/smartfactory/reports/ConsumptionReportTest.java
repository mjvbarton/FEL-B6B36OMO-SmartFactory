/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Electricity;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.ConsumableConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.consumption.WorkerConsumption;
import cz.cvut.k36.omo.bartom47.smartfactory.events.Tick;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.PersonWorker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Matej
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsumptionReportTest {
        
    
    @Mock
    Tick tick;
    
    @Mock
    Assembly mockAssembly;            
    
    public ConsumptionReportTest() {
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
    public void testGenerateGeneratesYAMLFileForConsumableConsumption() {
        Consumable c = new Electricity();
        ConsumableConsumption cC = c.getConsumption();
        ConsumptionReport cr = new ConsumptionReport(Tick.getTickCount());
        cr.generate(cC);
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
