package cz.cvut.k36.omo.bartom47.smartfactory.assembly;

import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Object pool of non working workers
 * @author Matej
 */
class NotWorkingWorkers {
    private static Logger LOG = LoggerFactory.getLogger(NotWorkingWorkers.class);
    private final Assembly assembly;
    private final HashMap<Class<? extends Worker>, Queue<Worker>> workerPool;
    private final WorkerFactory wfactory;
    
    
    NotWorkingWorkers(Assembly assembly) {
        this.assembly = assembly;
        this.workerPool = new HashMap();
        this.wfactory = WorkerFactory.getFactory();
    } 
    
    NotWorkingWorkers(Assembly assembly, HashMap<Class<? extends Worker>, Queue<Worker>> workerPool, 
            WorkerFactory wfactory){
        this.assembly = assembly;
        this.workerPool = workerPool;
        this.wfactory = wfactory;
    }
    
    Worker getWorker(Class<? extends Worker> workerClass){
        if(workerPool.containsKey(workerClass) && !workerPool.get(workerClass).isEmpty()){
            return workerPool.get(workerClass).poll();
            
        } else if(workerPool.containsKey(workerClass) && workerPool.get(workerClass).isEmpty()){
            return wfactory.createOfClass(workerClass, assembly);
            
        } else if(!workerPool.containsKey(workerClass)){
            workerPool.put(workerClass, new LinkedList());
            return wfactory.createOfClass(workerClass, assembly);
        } else
            throw new IllegalStateException("Invalid state of 'getWorker(Class<? extends Worker>)'");
        
    }       
    
    void saveWorker(Worker worker){
        if(workerPool.containsKey(worker.getClass())){
            workerPool.get(worker.getClass()).add(worker);
        } else{            
            workerPool.put(worker.getClass(), new LinkedList(Arrays.asList(worker)));
        }
    }
    
    Collection<Worker> getWorkers(){
        return workerPool.values().stream()
                .flatMap(workers -> workers.stream())
                .collect(Collectors.toList());
    }
}
