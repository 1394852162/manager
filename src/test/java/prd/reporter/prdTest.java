package prd.reporter;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import prd.reporter.prdcontroller.ReporterController;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml"})
public class prdTest {

    public static final Logger LOGGER = Logger.getLogger(ReporterController.class);

    @Resource
    private ReporterController reporterController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reporterController).build();
    }

    @Test
    public void testGetReporter() throws  Exception  {
        JSONObject jo = new JSONObject();
        jo.put("Title", "html");
        jo.put("w", "w");
        jo.put("w1", "w1");
        jo.put("outputType", "pdf");

        String requestjson = jo.toString();
//        System.out.println(requestjson);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/getReporter").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

}
