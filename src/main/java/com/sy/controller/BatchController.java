package com.sy.controller;

import com.sy.pojo.Batch;
import com.sy.service.IBatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haswell on 2018/2/6.
 */
@Controller
@RequestMapping("/batch")
public class BatchController {

    @Resource
    private IBatchService IBatchService;


    public Map<String,Object> GetDeptList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Batch> list = this.IBatchService.getBatList();
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("code",0);
            resultmap.put("data","查询失败");
        }
        return resultmap;
    }

}
