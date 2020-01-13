package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.core.DataModelNode;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents generated report of the factory. The report is generated
 * to {@code *.yaml} file.
 * @author Matej
 * @param <E> type of accepted {@link DataModelNode}
 */
public abstract class FactoryReport<E extends DataModelNode> {
    private static String RESOURCES_LOCATION_PATH = "src/main/resources/reports/";
    private static Logger LOG = LoggerFactory.getLogger(FactoryReport.class);
    private final String reportType;
    private final String resourcePath;
    
    /**
     * Creates new factory report
     * @param resourcePath path to the resource
     * @param reportType type of the report
     */
    protected FactoryReport(String resourcePath, String reportType){
        this.resourcePath = resourcePath;
        this.reportType = reportType;
    }
    
    protected File getFile(String path){
        return new File(path);        
    }
    
    /**
     * Generates report in to file for data model node given.
     * @param dataNode node of the data model. See: {@link DataModelNode}
     * @return path of newly generated file as string
     */
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
