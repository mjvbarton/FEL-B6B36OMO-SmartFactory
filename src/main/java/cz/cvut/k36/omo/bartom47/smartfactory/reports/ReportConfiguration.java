package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

/**
 * Configuration of the YAML file serializer. This class is {@code static}.
 * @author Matej
 */
public class ReportConfiguration {
        
    private static ObjectMapper objectMapper;
    
    /**
     * Returns object mapper.
     * @return configured object mapper
     */
    public static synchronized ObjectMapper getObjectMapper(){
        if(objectMapper == null){
            objectMapper = new ObjectMapper(new YAMLFactory()
                   .disable(Feature.WRITE_DOC_START_MARKER)                                                         
            );
            objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);            
        }
        return objectMapper;
    }
}
