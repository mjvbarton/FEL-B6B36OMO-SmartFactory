package cz.cvut.k36.omo.bartom47.smartfactory.assembly;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.k36.omo.bartom47.smartfactory.core.ConfigurationData;
import cz.cvut.k36.omo.bartom47.smartfactory.production.SeriesConfiguration;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.WorkerConfiguration;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represents configuration data for {@link Assembly}.
 * @author Matej
 */
public class AssemblyConfiguration extends ConfigurationData<Assembly>{
    
    public AssemblyConfiguration() {
        super();
    }
    
    public String getName(){
        return getParent().getName();        
    }
    
    public Integer getPriority(){
        return getParent().getPriority();
    }
    
    public Collection<WorkerConfiguration> getActiveWorkers(){
        return getParent().getChildren().stream()
                .map(worker -> worker.getConfiguration())
                .collect(Collectors.toList());
    }
    
    @JsonIgnore //TODO: Remove this in version 1.0
    public Collection<WorkerConfiguration> getNonActiveWorkers(){
        throw new UnsupportedOperationException("Not implemented in version 1.0-BETA.");
    }
    
    @JsonIgnore //TODO: Remove this in version 1.0
    public Collection<SeriesConfiguration> getWorkPlan(){
        throw new UnsupportedOperationException("Not implemented in version 1.0-BETA.");
    }
    
}
