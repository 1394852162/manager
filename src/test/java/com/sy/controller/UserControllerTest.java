package com.sy.controller;



import javax.annotation.Resource;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class UserControllerTest {

    @Resource
    private EmpController emoController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(emoController).build();
    }

    @Test
    public void testlogin() throws Exception{

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/login.do?name=lijp&password=654321"));
            MvcResult mvcResult = resultActions.andReturn();
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("=====?????????:" + result);



    }
}
