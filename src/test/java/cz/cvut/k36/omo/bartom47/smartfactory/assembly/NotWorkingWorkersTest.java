package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerFactory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotWorkingWorkersTest {
    
    @Mock
    private WorkerFactory wf;
    
    @Mock
    private Assembly assembly;
    
    @Mock
    private HashMap<Class<? extends Worker>, Queue<Worker>> innerPool;
    
    @Mock
    private Queue<Worker> workerQueue;
    
    @Mock
    private Worker worker;
    
    private NotWorkingWorkers instance;
        
    
    @Before
    public void setUp(){
        instance = spy(new NotWorkingWorkers(assembly, innerPool, wf));
    }
    
    @After
    public void clean(){
        Mockito.reset(instance);
    }
    

    @Test(expected = Test.None.class)
    public void testGetWorkerReturnsWorkerForContainedClassAndNotEmtpyQueue() {
        doReturn(Boolean.TRUE).when(innerPool).containsKey(any(Class.class));
        doReturn(workerQueue).when(innerPool).get(any(Class.class));
        doReturn(Boolean.FALSE).when(workerQueue).isEmpty();
        doReturn(worker).when(workerQueue).poll();
        instance.getWorker(Worker.class);              
    }
    
    @Test(expected = Test.None.class)
    public void testGetWorkerReturnsCreatesNewWorkerForContainedClassAndEmtpyQueue() {
        doReturn(Boolean.TRUE).when(innerPool).containsKey(any(Class.class));        
        doReturn(workerQueue).when(innerPool).get(any(Class.class));
        doReturn(Boolean.TRUE).when(workerQueue).isEmpty();
        doReturn(worker).when(wf).createOfClass(any(Class.class), any(Assembly.class));
        instance.getWorker(Worker.class);        
        verify(wf, times(1)).createOfClass(any(Class.class), any(Assembly.class));
    }
    
    @Test(expected = Test.None.class)
    public void testGetWorkerCreatesNewWorkerAndPutsNotContainedClassWithEmptyQueueIntoPool(){
        doReturn(Boolean.FALSE).when(innerPool).containsKey(any(Class.class));
        instance.getWorker(Worker.class);
        verify(innerPool, times(1)).put(Worker.class, new LinkedList());
        verify(wf, times(1)).createOfClass(any(Class.class), any(Assembly.class));
    }
    
    @Test(expected = Test.None.class)
    public void testSaveWorkerSavesWorkerForContainedClass(){
        Queue<Worker> queue = new LinkedList();
        doReturn(Boolean.TRUE).when(innerPool).containsKey(any(Class.class));
        doReturn(queue).when(innerPool).get(any(Class.class));
        instance.saveWorker(worker);
        assertTrue("Worker in the queue", queue.contains(worker));
    }
    
    @Test(expected = Test.None.class)
    public void testSaveWorkerSavesWorkerForNotContainedClass(){        
        doReturn(Boolean.FALSE).when(innerPool).containsKey(any(Class.class));        
        instance.saveWorker(worker);
        verify(innerPool).put(any(Class.class), any(LinkedList.class));
    }
    
}
