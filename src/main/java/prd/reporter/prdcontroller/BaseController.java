package prd.reporter.prdcontroller;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BaseController {
    void doReporter(String outputType, String filePath, Map<String, String> paramMap, HttpServletResponse response);
}
