package com.sy.controller;

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

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"})
public class EmpControllerTest {

    public static final Logger LOGGER = Logger.getLogger(EmpControllerTest.class);

    @Resource
    private EmpController empController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
    }

    /**
     * 部门-职工树查询测试
     * @throws Exception
     */
    @Test
    public void testGetDeptEmpTree() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/employee/getDeptEmpTree.do"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        LOGGER.info("=====客户端获得反馈数据:" + result);

    }

    /**
     * 跟新部门-职工的状态
     * @throws Exception
     */
    @Test
    public void testUpdateDeptEmpTree() throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("EmpId", 2);
        jsonObject.put("DeptId", 1);
        jsonObject.put("EmpStatus3", 0);
        jsonObject.put("EmpStatus4", 0);

        String requestjson = jsonObject.toString();
        LOGGER.info(requestjson);

        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/employee/updateDeptEmpTree.do").contentType(MediaType.APPLICATION_JSON).content(requestjson))
                .andReturn().getResponse().getContentAsString();
        LOGGER.info(responseString);
    }
}
