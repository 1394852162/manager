package com.sy.controller;

import com.sy.pojo.VipTicket;

import com.sy.service.IVipService;
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
 * Created by haswell on 2018/2/7.
 */
@Controller
@RequestMapping("/vip")
public class VipController {

    @Resource
    private IVipService iVipService;

    VipTicket viptic = new VipTicket();
    /**
     * 查询vip票添加的列表
     * @return
     */
    @RequestMapping("/getVipList.do")
    @ResponseBody
    public Map<String,Object> getVipList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<VipTicket> list = this.iVipService.getVipList();
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("data","无数据");
            resultmap.put("code",0);

        }
        return resultmap;
    }


    @RequestMapping("/QueryNameByList.do")
    @ResponseBody
    public Map<String,Object> QueryNameByList(@RequestParam("EmpName") String  EmpName){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<VipTicket> list = this.iVipService.QueryNameByList(EmpName);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("code",0);
            resultmap.put("data","未查到该人员");


        }
        return resultmap;
    }



    /**
     * 添加一条记录
     * @param EmpId
     * @param VipEMpID
     * @param VipAddNum
     * @param VipAddNote
     * @param AddTime
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insertVipTicket.do")
    @ResponseBody
    public Map<String,Object> insertVipTicket(@RequestParam("EmpId") int EmpId,@RequestParam("VipEMpID") int VipEMpID,
                                              @RequestParam("VipAddNum") int VipAddNum,@RequestParam("VipAddNote") String VipAddNote,
                                              @RequestParam("VipAddTime") String AddTime) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        Date VipAddTime =simp.parse(AddTime);
        map.put("EmpId",EmpId);
        map.put("VipEMpID",VipEMpID);
        map.put("VipAddNum",VipAddNum);
        map.put("VipAddNote",VipAddNote);
        map.put("VipAddTime",VipAddTime);
        int result =this.iVipService.insertVipTicket(map);
        if(result==1){
            resultmap.put("data", "添加成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "添加失败!");
        }
        return resultmap;
    }

    /**
     * 删除记录
     * @param VipId
     * @return
     */
    @RequestMapping("/deleteVipTicket.do")
    @ResponseBody
    public Map<String,Object> deleteVipTicket(@RequestParam("VipId") int VipId){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        int result = this.iVipService.deleteVipTicket(VipId);
        if(result==1){
            resultmap.put("data", "删除成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "删除失败!");
            resultmap.put("code", 0);

        }
        return resultmap;
    }

}
