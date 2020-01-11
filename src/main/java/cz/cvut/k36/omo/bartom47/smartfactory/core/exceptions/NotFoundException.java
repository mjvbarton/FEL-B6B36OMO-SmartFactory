package cz.cvut.k36.omo.bartom47.smartfactory.core.exceptions;

/**
 * Used when a resource is not found.
 * @author Matej
 */
public class NotFoundException extends RuntimeException{

    private NotFoundException(String message) {
        super(message);
    }

    private NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static NotFoundException create(Object resource, Throwable cause){
        return new NotFoundException(getMessage(resource), cause);
    }
    
    public static NotFoundException create(Object resource){
        return new NotFoundException(getMessage(resource));
    }
    
    private static String getMessage(Object resource){
        String message = new StringBuilder()
                .append("Resource ")
                .append(resource)
                .append(" was not found")
                .toString();
        return message;
    }
    
    
    
}
