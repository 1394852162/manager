package prd.reporter.prdpojo;

import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import prd.reporter.AbstractReportGenerator;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Reporter extends AbstractReportGenerator {

    public Reporter() {
    }


    /**
     * 定义报表数据源
     * @return
     */
    @Override
    public DataFactory getDataFactory() {
        return null;
    }

    /**
     * 定义报表参数
     * @return
     */
    @Override
    public Map<String, Object> getReportParameters() {
//        final Map parameters = new HashMap<String, Object>();
//        parameters.put("Title", "1212");
        /*parameters.put("Col Headers BG Color", "yellow");
        parameters.put("Customer Names",
                new String [] {
                        "American Souvenirs Inc",
                        "Toys4GrownUps.com",
                        "giftsbymail.co.uk",
                        "BG&E Collectables",
                        "Classic Gift Ideas, Inc",
                });*/
//        return parameters;
        return null;
    }

    /**
     * 定义生存报表
     * @return
     */
    @Override
    public MasterReport getReportDefinition() {
        try {
            final ClassLoader classloader = this.getClass().getClassLoader();
            final URL reportDefinitionURL = classloader.getResource("reports/Reporter.prpt");

            final ResourceManager resourceManager = new ResourceManager();
            final Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
            return (MasterReport) directly.getResource();
        } catch (ResourceException e){
            e.printStackTrace();
        }
        return null;
    }

}
