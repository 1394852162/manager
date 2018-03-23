package com.sy.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

/**
 * Created by haswell on 2018/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"})
public class BatchControllerTest {

    @Resource
    private BatchController  catchController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(catchController).build();
    }



    /**@RequestParam("BatNo") String BatNo, @RequestParam("BatName") String BatName,
     @RequestParam("BatTicketNum") int BatTicketNum,
     @RequestParam("BatBeginTime") String BeginTime, @RequestParam("BatEndTime") String EndTime,
     @RequestParam("Status2") int Status2, @RequestParam("BatNote") String BatNote
     * 部门插入测试
     * @throws Exception
     */
    @Test
    public void testinsertBatch() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();


        jo.put("BatNo", "11005");
        jo.put("BatName", "11005");//备注
        jo.put("BatTicketNum", 8);//备注
        jo.put("BatBeginTime", "2018-2-15");//备注
        jo.put("BatEndTime", "2018-3-25");//备注
        jo.put("Status2", 1);//备注
        jo.put("BatNote", "测试批次4有备注");//备注



//   这个就是前段需要传的json的array
        /*
        {"CollNum":10,"CollTime":"2018-03-10","CollNote":"测试领用修改","CollId":1}
         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/batch/insertBatch.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }


    /**
     * 根据人员状态查询批次
     * @throws Exception
     */
    @Test
    public void testgetBatListbyDept() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();


        jo.put("EmpStatus1", 1);
//   这个就是前段需要传的json的array
        /*
        {"CollNum":10,"CollTime":"2018-03-10","CollNote":"测试领用修改","CollId":1}
         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/batch/getBatListbyDept.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }


}
