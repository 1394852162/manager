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

/**
 * Created by haswell on 2018/2/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"})
public class VipControllerTest {

    @Resource
    private VipController vipController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(vipController).build();
    }


    /**
     * VIp票领取修改测试
     * @throws Exception
     */
    @Test
    public void testupdateVipTicket() throws  Exception  {
//		delete 需要传的参数和insert不一样，是 BrandSeq:1 pBrandId:[2,3] 这种json格式
        JSONObject jo = new JSONObject();
        jo.put("VipEmpID", 4);//VIp领取人
        jo.put("VipAddNum",10);//领取数量
        jo.put("VipAddTime", "2018-03-10");//领取时间
        jo.put("VipAddNote", "能成功吗");//备注
        jo.put("VipAddId", 4);//备注
//   这个就是前段需要传的json的array
        /*
        {"CollNum":10,"CollTime":"2018-03-10","CollNote":"测试领用修改","CollId":1}
         */
        String requestjson = jo.toString();
        System.out.println(requestjson);
//json格式的传输不能使用get方法中的paran这样的方法来处理了，必须是下面的方式
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/vip/updateVipTicket.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }


}
