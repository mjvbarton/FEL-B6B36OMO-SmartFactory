package cz.cvut.k36.omo.bartom47.smartfactory.workers;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.core.exceptions.WorkerFactoryException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents worker factory. Singleton.
 * @author Matej 
 */
public class WorkerFactory {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerFactory.class);
    private static WorkerFactory factory;
    private final Collection<Class<? extends Worker>> supportedClasses;
    
    private WorkerFactory() {
        this.supportedClasses = new HashSet();
        supportedClasses.add(PersonWorker.class);
        supportedClasses.add(CollaborativeRobot.class);
        supportedClasses.add(Machine.class);
        LOG.debug("Default supported worker classes: " 
                + PersonWorker.class.getSimpleName() + " ,"
                + CollaborativeRobot.class.getSimpleName() + " ,"
                + Machine.class.getSimpleName() + " ,"
        );
    }        
    
    /**
     * Gets the singleton instance
     * @return singleton instance
     */
    public static synchronized WorkerFactory getFactory(){        
        if(factory == null){            
            factory = new WorkerFactory();
            LOG.debug("Created new instance of " + WorkerFactory.class.getSimpleName());
        }
        return factory;
    }
    
    /**
     * Creates new worker as an instance of worker class given. This worker will
     * be a child of assembly given.
     * @param clazz child class of {@link Worker}
     * @param assembly parent assembly node for new worker
     * @return new worker instance of worker class given
     * @throws WorkerFactoryException when problem occurs during the process.
     * See details of {@link WorkerFactoryException#getMessage()} and 
     * {@link WorkerFactoryException#getCause()} for more details.
     */
    public Worker createOfClass(Class<? extends Worker> clazz, Assembly assembly) throws WorkerFactoryException{
        if(supportedClasses.contains(clazz)){
            try {
                final Method m = clazz.getMethod("create", Assembly.class);
                return (Worker) m.invoke(null, assembly);                
                
            } catch (NoSuchMethodException | SecurityException ex) {                                
                throw new WorkerFactoryException("Method of create of worker class" 
                        + clazz.getName() + "is unreachable.", ex);
                
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new WorkerFactoryException("Factory method create "
                        + "cof worker class " + clazz.getName() + " cannot be invoked.", ex);                
            }
        } else
            throw new WorkerFactoryException("Class " + clazz + " not found in supported classes.");
    }
    
    /**
     * Adds class given to supported classes of the factory.
     * @param clazz class to be recognized as supported
     */
    public void addSupportedClass(Class<? extends Worker> clazz){
        supportedClasses.add(clazz);
    }
}
