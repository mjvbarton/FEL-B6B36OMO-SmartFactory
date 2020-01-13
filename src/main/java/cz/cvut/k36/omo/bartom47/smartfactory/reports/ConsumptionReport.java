package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import cz.cvut.k36.omo.bartom47.smartfactory.core.events.Tick;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents generated <b>consumption report</b> into .yaml file. <br>
 * The reports are stored at {@code src/main/resources/reports/consumption}.
 *
 * @author Matej
 */
public class ConsumptionReport extends FactoryReport{
    /**
     * Gets resource path for the id of the {@link Tick} given.
     * @param tickId
     * @return resource path as string
     */
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
        
    /**
     * Creates new configuration report for tickId given.
     * @param tickId id of reported {@link Tick}
     */
    public ConsumptionReport(Integer tickId) {        
        super(getResourcePath(tickId), ConsumptionReport.class.getSimpleName());
    }       
}
