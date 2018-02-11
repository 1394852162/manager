package com.sy.controller;

import javax.annotation.Resource;


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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"})
public class CollarControllerTest {

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
        jo.put("CollNo", "ceshino");
        jo.put("BatId", 1);
        jo.put("BatEndTime", "2018-04-02");
        jo.put("CollTime", "2018-01-09");
        jo.put("EmpId", 3);
        jo.put("CollNum", 4);
        jo.put("CollNote", "职工劵");
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
        jo.put("EmpName", "s");
//        jo.put("EmpNo", "1003");
//        jo.put("BeginDate", "2018-04-02");
//        jo.put("EndDate", "2018-01-09");
//        jo.put("EmpId", 3);
//        jo.put("CollNum", 5);
//        jo.put("CollNote", "职工劵");
//   这个就是前段需要传的json的array
        /*

        {"CollNo":"20180209","BatId":13,"BatEndTime":"2018-03-02","CollTime":"2018-02-09","EmpId":2,"CollNum":8,"CollNote":"春节第一次领取职工劵"}

         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/collar/querySelectList.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}