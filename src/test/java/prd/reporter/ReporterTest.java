package prd.reporter;

import org.junit.Test;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.HtmlReportUtil;
import prd.reporter.prdpojo.Reporter;

import java.io.File;
import java.io.IOException;

public class ReporterTest {
    @Test
    public void test1() throws IOException, ReportProcessingException {
        // Create an output filename
        final File outputFilename = new File(Reporter.class.getSimpleName() + ".pdf");

        // Generate the report
        new Reporter().generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);

        // Output the location of the file
        System.err.println("Generated the report [" + outputFilename.getAbsolutePath() + "]");
    }

    @Test
    public void test2() throws IOException, ReportProcessingException {
        final File outputFilename = new File(Reporter.class.getSimpleName() + ".pdf");
        final MasterReport report = new Reporter().getReportDefinition();
        report.getParameterValues().put("Title","1111");
//        HtmlReportUtil.createStreamHTML(report, outputFilename.getAbsolutePath());
        PdfReportUtil.createPDF(report,outputFilename.getAbsolutePath());
    }
}
