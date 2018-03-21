package com.sy.controller;

import javax.annotation.Resource;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"})
public class CollarControllerTest {

    public static final Logger LOGGER = Logger.getLogger(CollarControllerTest.class);

    @Resource
    private CollarController collarController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(collarController).build();
    }

    @Test
    public void testAddCollar() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();
        jo.put("CollNo", "TPF");
        jo.put("BatId", 1);
        jo.put("BatEndTime", "2018-02-11");
        jo.put("CollTime", "2018-02-14");
        jo.put("EmpId", 3);
        jo.put("CollNum", 4);
        jo.put("CollNote", "职工劵领取");
        jo.put("Status", 1);
//   这个就是前段需要传的json的array
        /*

        {"CollNo":"20180209","BatId":13,"BatEndTime":"2018-03-02","CollTime":"2018-02-09","EmpId":2,"CollNum":8,"CollNote":"春节第一次领取职工劵"}

         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/collar/insertCollarMap.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }



    @Test
    public void testselectCollar() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();
        jo.put("EmpName", "TPF");
        jo.put("EmpNo", "1002");
        jo.put("BeginDate", "2018-01-01");
        jo.put("EndDate", "2018-04-01");
       jo.put("CollNo", "20180209");
       jo.put("Status", 0);

//   这个就是前段需要传的json的array
        /*
        {"EmpName":"x","EmpNo":1003,"BeginDate":"BeginDate","EndDate":"2018-02-10","CollNo":"3","Status":0}
         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/collar/querySelectList.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }



    @Test
    public void testupdateCollar() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();
        jo.put("CollNum", 10);
        jo.put("CollTime", "2018-03-10");
        jo.put("CollNote", "测试领用修改");
        jo.put("CollId", 1);
//   这个就是前段需要传的json的array
        /*
        {"CollNum":10,"CollTime":"2018-03-10","CollNote":"测试领用修改","CollId":1}
         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/collar/updateCollar.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }





    /**
     * 查询领用信息加上领取的票数合计 调用的借口不变
     * @throws Exception
     */
    @Test
    public void testLYHJ() throws Exception{

        //参数为DeptId
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/collar/getCollList.do"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("数据是:" + result);
    }

    /**
     * 职工劵明细
     * @throws Exception
     */
    @Test
    public void testGetCollarTicketList() throws Exception{
        JSONObject jo = new JSONObject();
        jo.put("DeptId", 11);
        jo.put("EmpId", 446);
        jo.put("BatId", 26);

        String requestjson = jo.toString();
        System.out.println(requestjson);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/collar/getCollarTicketList.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);

        /*ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/collar/getCollarTicketList.do"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        LOGGER.info("=====客户端获得反馈数据:" + result);*/
    }
}
