package com.sy.controller;

import com.sy.pojo.Dept;
import com.sy.pojo.User;
import com.sy.service.IDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haswell on 2018/1/25.
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private IDeptService DeptService;
    Dept dept = new Dept();


    @RequestMapping("/getDeptList.do")
    @ResponseBody
    public Map<String,Object>  GetDeptList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        System.out.println("开始进入");
        List<Dept> list = this.DeptService.GetQueryDeptList();
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("data","查询失败");
            resultmap.put("code",0);
        }
        return resultmap;
    }



}
