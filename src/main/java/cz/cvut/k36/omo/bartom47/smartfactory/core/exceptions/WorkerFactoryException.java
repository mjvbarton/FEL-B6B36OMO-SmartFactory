package cz.cvut.k36.omo.bartom47.smartfactory.core.exceptions;

/**
 *
 * @author Matej
 */
public class WorkerFactoryException extends RuntimeException{

    public WorkerFactoryException(String message) {
        super(message);
    }

    public WorkerFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
