/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

/**
 *
 * @author Matej
 */
public class ReportConfiguration {
        
    private static ObjectMapper objectMapper;
    
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
