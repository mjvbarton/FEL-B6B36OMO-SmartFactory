package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Matej
 */
public class ConsumptionReport extends FactoryReport{
    
    public static String getResourcePath(Integer tickId){        
        DateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmss");        
                
        StringBuilder sb = new StringBuilder();
        return sb
                .append("consumption/")
                .append(ConsumptionReport.class.getSimpleName())
                .append("-")
                .append(df.format(new Date()))
                .append("-")
                .append(tickId).append(".yaml")
                .toString();
    }
        
    public ConsumptionReport(Integer tickId) {        
        super(getResourcePath(tickId), ConsumptionReport.class.getSimpleName());
    }       
}
