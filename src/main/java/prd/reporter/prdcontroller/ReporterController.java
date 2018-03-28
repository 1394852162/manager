package prd.reporter.prdcontroller;

import org.apache.commons.io.FileUtils;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.base.StreamReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.*;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.libraries.repository.ContentIOException;
import org.pentaho.reporting.libraries.repository.ContentLocation;
import org.pentaho.reporting.libraries.repository.DefaultNameGenerator;
import org.pentaho.reporting.libraries.repository.file.FileRepository;
import org.pentaho.reporting.libraries.resourceloader.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prd.reporter.AbstractReportGenerator;
import prd.reporter.prdpojo.Reporter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//@SuppressWarnings("serial")
@Controller
//@RequestMapping("/reporter")
public class ReporterController implements BaseController {


    private static final Map<String, String> contentTypeMap = new HashMap<String, String>();
    static {
        contentTypeMap.put("pdf", "application/pdf");
        contentTypeMap.put("excel2003", "application/vnd.ms-excel");
        contentTypeMap.put("excel2007", "application/vnd.ms-excel");
        contentTypeMap.put("html", "text/html");

    }

    /*@Override
    void doReporter() {
        *//*final MasterReport report = new Reporter().getReportDefinition();
        report.getParameterValues();*//*
    }*/


    @ResponseBody
    @RequestMapping("/getReporter")
    public Map<String, String> getReporter(@RequestBody Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response)
            throws ReportProcessingException, IOException, ResourceLoadingException, ResourceCreationException, ResourceKeyCreationException, ContentIOException, ServletException {


        final MasterReport report = new Reporter().getReportDefinition();

        Map<String,String> outputTypeMap = new HashMap<>();
        outputTypeMap.put("1","1");
        String outputType = null;

        if ( null != paramMap ){
            Set<String> paramSet = paramMap.keySet();
            Iterator<String> iterator = paramSet.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                Object value = paramMap.get(key);
                report.getParameterValues().put(key, value);
                if ("outputType" == key){
                    outputType = (String) value;
                }
//                System.out.println(key + ":" + value);
            }
        }
        request.setCharacterEncoding("utf-8");
        response.setContentType(contentTypeMap.get(outputType.toLowerCase()));
        if ( outputType.equalsIgnoreCase("pdf") ) {
            OutputStream out = response.getOutputStream();
            System.out.println("out == " + out);
            try {
                PdfReportUtil.createPDF(report, out);
            } catch (ReportProcessingException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } else if ( outputType.equalsIgnoreCase("html") ) {
            String fileName = Reporter.class.getSimpleName() + "." + outputType;
            File folderOut = new File(request.getSession().getServletContext().getRealPath("/out"));
            FileUtils.deleteDirectory(folderOut);
            folderOut.mkdirs();
            final FileRepository targetRepository = new FileRepository(folderOut);
            final ContentLocation targetRoot = targetRepository.getRoot();
            final HtmlPrinter printer = new AllItemsHtmlPrinter(report.getResourceManager());
            printer.setContentWriter(targetRoot, new DefaultNameGenerator(targetRoot, fileName));
            printer.setDataWriter(targetRoot, new DefaultNameGenerator(targetRoot, "content"));
            printer.setUrlRewriter(new FileSystemURLRewriter());
            final StreamHtmlOutputProcessor outputProcessor = new StreamHtmlOutputProcessor(report.getConfiguration());
            outputProcessor.setPrinter(printer);
            final StreamReportProcessor reportProcessor = new StreamReportProcessor(report, outputProcessor);
            reportProcessor.processReport();
            reportProcessor.close();
            String route =  folderOut.getName() + "/" + fileName;
//            response.sendRedirect(route);
//            request.getRequestDispatcher(route).forward(request, response);

            System.out.println(route);
            System.out.println(request.getSession().getServletContext().getRealPath("/"));
//            System.out.println("=======================================================");
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+route;
//            System.out.println(path);
            System.out.println(basePath);
//            System.out.println("=======================================================");
            outputTypeMap.put("PRD_URL","1");



//            return

            OutputStream out = response.getOutputStream();
            try {
                HtmlReportUtil.createStreamHTML(report, out);
            } catch (ReportProcessingException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } else if ( outputType.equalsIgnoreCase("excel2003")
                    || outputType.equalsIgnoreCase("excel2007") ) {
//            response.setContentType("application/x-msdownload");
            if(outputType.equalsIgnoreCase("excel2003")) {
                response.setHeader("Content-disposition", "attachment;filename="+ Reporter.class.getSimpleName() +".xls");
                OutputStream out = response.getOutputStream();
                try {
                    ExcelReportUtil.createXLS(report, out);
                } catch (ReportProcessingException e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                }
            } else if(outputType.equalsIgnoreCase("excel2007")){
                response.setHeader("Content-disposition", "attachment;filename="+ Reporter.class.getSimpleName() +".xlsx");
                OutputStream out = response.getOutputStream();
                try {
                    ExcelReportUtil.createXLSX(report, out);
                } catch (ReportProcessingException e) {
                    e.printStackTrace();
                }  finally {
                    out.close();
                }
            }
        }



//        System.out.println("outputType==" + outputType);
//        System.out.println(response.getContentType());
//        System.out.println(outputFilename.getAbsolutePath());
//            new Reporter().generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
//        PdfReportUtil.createPDF(report,outputFilename);
//            HtmlReportUtil.createStreamHTML(report,response.getOutputStream());
            /*response.setCharacterEncoding("utf-8");
            response.setContentType(contentTypeMap.get("pdf"));
            response.setHeader("Content-disposition", "attachment;filename="+ Reporter.class.getSimpleName() +".pdf");
            PdfReportUtil.createPDF(report,response.getOutputStream());*/

        return outputTypeMap;
    }

    @Override
    public void doReporter(String outputType, String filePath, Map<String, String> paramMap, HttpServletResponse response) {

    }
}
