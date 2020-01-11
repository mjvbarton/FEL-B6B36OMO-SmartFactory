package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.core.DataModelNode;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matej
 * @param <E>
 */
public abstract class FactoryReport<E extends DataModelNode> {
    private static String RESOURCES_LOCATION_PATH = "src/main/resources/reports/";
    private static Logger LOG = LoggerFactory.getLogger(FactoryReport.class);
    private final String reportType;
    private final String resourcePath;
    
    public FactoryReport(String resourcePath, String reportType){
        this.resourcePath = resourcePath;
        this.reportType = reportType;
    }
    
    protected File getFile(String path){
        return new File(path);        
    }
    
    public String generate(E dataNode){
        try {
            String path = new StringBuilder().append(RESOURCES_LOCATION_PATH).append(resourcePath).toString();
            ReportConfiguration.getObjectMapper().writeValue(getFile(path), dataNode);
            return path;
        } catch (IOException ex) {
            LOG.error("An error occured during writing to the file", ex);
            throw new RuntimeException("An error occured during writing to the file", ex);
        }
    }
}
