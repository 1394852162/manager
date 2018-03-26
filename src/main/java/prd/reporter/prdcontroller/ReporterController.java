package prd.reporter.prdcontroller;

import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.springframework.stereotype.Controller;
import prd.reporter.prdpojo.Reporter;

@SuppressWarnings("serial")
@Controller
public class ReporterController extends BaseController {


    @Override
    void doReporter() {
        final MasterReport report = new Reporter().getReportDefinition();
        report.getParameterValues();
    }
}
