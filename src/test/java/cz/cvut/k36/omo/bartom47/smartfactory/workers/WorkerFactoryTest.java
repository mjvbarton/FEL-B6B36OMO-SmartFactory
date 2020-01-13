package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WorkerFactoryTest {
    
    private WorkerFactory wf;
    
    @Mock
    private Assembly assembly;
    
    @Before
    public void setUp(){
        wf = WorkerFactory.getFactory();
    }        

    @Test(expected = Test.None.class)
    public void testCreateOfClassCreatesWorkerForDefaultSupportedClasses() {
        Worker pw = wf.createOfClass(PersonWorker.class, assembly);
        assertNotNull("PersonWorker instance", pw);
        assertTrue("is instance of PersonWorker", pw instanceof PersonWorker);
        
        Worker mw = wf.createOfClass(Machine.class, assembly);        
        assertNotNull("Machine instance", mw);
        assertTrue("Returned is instance of Machine", mw instanceof Machine);
        
        Worker rw = wf.createOfClass(CollaborativeRobot.class, assembly);      
        assertNotNull("CollaborativeRobot instance", rw);
        assertTrue("is instance of CollaborativeRobot", rw instanceof CollaborativeRobot);        
    }
    
}
