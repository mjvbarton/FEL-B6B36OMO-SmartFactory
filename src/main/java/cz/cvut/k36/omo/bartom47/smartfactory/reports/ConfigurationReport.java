package cz.cvut.k36.omo.bartom47.smartfactory.reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Matej
 */
public class ConfigurationReport extends FactoryReport{
    
    public static String getResourcePath(Integer tickId) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmss");

        StringBuilder sb = new StringBuilder();
        return sb
                .append("configuration/")
                .append(ConfigurationReport.class.getSimpleName())
                .append("-")
                .append(df.format(new Date()))
                .append("-")
                .append(tickId).append(".yaml")
                .toString();
    }

    public ConfigurationReport(Integer tickId) {
        super(getResourcePath(tickId), ConfigurationReport.class.getSimpleName());
    }
    
}
