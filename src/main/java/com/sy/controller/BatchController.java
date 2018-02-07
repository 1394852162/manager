package com.sy.controller;

import com.sy.pojo.Batch;
import com.sy.service.IBatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private IBatchService iBatchService;

    /**
     * 查询批次列表
     * @return
     */
    @RequestMapping("/GetBatList.do")
    @ResponseBody
    public Map<String,Object> GetBatList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Batch> list = this.iBatchService.getBatList();
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("code",0);
            resultmap.put("data","无批次信息");
        }
        return resultmap;
    }

    /**
     * 根据批次名查询批次
     * @param BatName
     * @return
     */
    @RequestMapping("/queryNameBatList.do")
    @ResponseBody
    public Map<String,Object>  queryNameBatList(@RequestParam("BatName") String BatName){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        System.out.println("开始进入");
        List<Batch> list = this.iBatchService.queryNameBatList(BatName);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }else{
            resultmap.put("code",0);
            resultmap.put("data","查不到该部门");
        }
        return resultmap;
    }


    @RequestMapping("/insertBatch.do")
    @ResponseBody
    public Map<String,Object>  insertBatch(@RequestParam("BatNo") String BatNo, @RequestParam("BatName") String BatName,
                                          @RequestParam("BatTicketNum") int BatTicketNum, @RequestParam("Status") int Status,
                                          @RequestParam("BatBeginTime") String BeginTime, @RequestParam("BatEndTime") String EndTime
                                          ) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd");
        Date BatBeginTime =simp.parse(BeginTime);
        Date BatEndTime =simp.parse(EndTime);

        map.put("BatNo",BatNo);
        map.put("BatName",BatName);
        map.put("BatTicketNum",BatTicketNum);
        map.put("Status",Status);
        map.put("BatBeginTime",BatBeginTime);
        map.put("BatEndTime",BatEndTime);
        int result = this.iBatchService.insertBatch(map);
        if(result==1){
            resultmap.put("data", "添加部门成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "添加部门失败!");

        }
        return resultmap;
    }

    /**
     * 修改批次信息
     * @param BatNo
     * @param BatName
     * @param BatTicketNum
     * @param Status
     * @param BeginTime
     * @param EndTime
     * @return
     */
    @RequestMapping("/updateDeptByKey")
    @ResponseBody
    public Map<String,Object>  updateDeptByKey(@RequestParam("BatNo") String BatNo, @RequestParam("BatName") String BatName,
                                               @RequestParam("BatTicketNum") int BatTicketNum, @RequestParam("Status") int Status,
                                               @RequestParam("BatBeginTime") String BeginTime, @RequestParam("BatEndTime") String EndTime,
                                               @RequestParam("BatId") int BatId ) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd");
        Date BatBeginTime =simp.parse(BeginTime);
        Date BatEndTime =simp.parse(EndTime);

        map.put("BatNo",BatNo);
        map.put("BatName",BatName);
        map.put("BatTicketNum",BatTicketNum);
        map.put("Status",Status);
        map.put("BatBeginTime",BatBeginTime);
        map.put("BatEndTime",BatEndTime);
        map.put("BatId",BatId);
        int result = this.iBatchService.updateBatchByKey(map);
        if(result==1){
            resultmap.put("data", "修改部门成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "修改部门失败!");

        }
        return resultmap;
    }

    /**
     * 删除批次信息
     * @param BatId
     * @return
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Map<String,Object>  deleteBatch(@RequestParam("BatId") int BatId){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        int result = this.iBatchService.deleteBatch(BatId);
        if(result==1){
            resultmap.put("data", "部门删除成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "部门删除失败!");

        }
        return resultmap;

    }
}