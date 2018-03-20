package com.sy.controller;

import com.sy.pojo.Collar;
import com.sy.service.ICollarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * Created by haswell on 2018/2/8.
 */
@Controller
@RequestMapping("/collar")
public class CollarController {

    @Resource
    private ICollarService iCollarService;

    @RequestMapping("/getCollList.do")
    @ResponseBody
    public Map<String,Object> getCollList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Collar> list = this.iCollarService.getCollList();
        System.out.println(list);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("num",list.get(0).getSumCollNum());
            resultmap.put("code",1);
        }
        else{
            resultmap.put("code",0);
            resultmap.put("data","无领用信息");
        }
        return resultmap;

    }

    @RequestMapping("/querySelectCollList.do")
    @ResponseBody
    public Map<String,Object> querySelectCollList(@RequestParam("EmpName") String EmpName,@RequestParam("EmpNo") String EmpNo,
                                              @RequestParam("BeginDate") String BeginDate,@RequestParam("EndDate") String EndDate) throws ParseException {
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        Date beginCollTime =simp.parse(BeginDate);
        Date endCollTime =simp.parse(EndDate);
        Map<String,Object> resultmap = new HashMap<String,Object>();
//        List<Collar> list = this.iCollarService.querySelectList(EmpName,EmpNo,beginCollTime,endCollTime);
//        if(list != null & list.size()>0) {
//            resultmap.put("data",list);
//            resultmap.put("code",1);
//        }
//        else{
//            resultmap.put("code",0);
//            resultmap.put("data","查不到信息");
//        }
        return resultmap;
    }

    /**
     * 添加领用票
     * @param CollNo
     * @param BatId
     * @param EndTime
     * @param LyTime
     * @param EmpId
     * @param CollNum
     * @param CollNote
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insertCollar.do")
    @ResponseBody
    public Map<String,Object>  insertCollar(@RequestParam("CollNo") String CollNo, @RequestParam("BatId") int BatId,
                                           @RequestParam("BatEndTime") String EndTime, @RequestParam("CollTime") String LyTime,
                                           @RequestParam("EmpId") int EmpId, @RequestParam("CollNum") int CollNum,
                                            @RequestParam("CollNote") String CollNote) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        Date BatEndTime =simp.parse(EndTime);
        Date CollTime =simp.parse(LyTime);

        map.put("CollNo",CollNo);
        map.put("BatId",BatId);
        map.put("BatEndTime",BatEndTime);
        map.put("CollTime",CollTime);
        map.put("EmpId",EmpId);
        map.put("CollNum",CollNum);
        map.put("CollNote",CollNote);
        int result = this.iCollarService.insertCollar(map);
        if(result==1){
            resultmap.put("data", "领取成功了!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "领取失败!");

        }
        return resultmap;
    }

    /**
     * 根据条件模糊查询
     * @param param
     * @return
     * @throws ParseException
     */
    @RequestMapping("/querySelectList.do")
    @ResponseBody
    public Map<String,Object> querySelectList(@RequestBody HashMap<String, Object> param) throws ParseException {

        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Collar> list = this.iCollarService.querySelectList(param);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("num",list.get(0).getSumCollNum());
            System.out.println("总数为:"+list.get(0).getSumCollNum());
            resultmap.put("code",1);
        }
        else{
            resultmap.put("code",0);
            resultmap.put("data","查询失败");
        }
        return resultmap;
    }



    @RequestMapping("/insertCollarMap.do")
    @ResponseBody
    public Map<String,Object>  insertCollarMap(@RequestBody HashMap<String, Object> param) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
       // HashMap<String,Object> map = new HashMap<String,Object>();
        int result = this.iCollarService.insertCollar(param);
        if(result==1){
            resultmap.put("data", "领取成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "领取失败了!");

        }
        return resultmap;
    }


    @RequestMapping("/updateCollar.do")
    @ResponseBody
    public Map<String,Object>  updateCollar(@RequestBody HashMap<String, Object> param) throws ParseException {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        // HashMap<String,Object> map = new HashMap<String,Object>();
        int result = this.iCollarService.updateCollar(param);
        if(result==1){
            resultmap.put("data", "修改成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "修改失败了!");
        }
        return resultmap;
    }

    @RequestMapping("/deleteCollar.do")
    @ResponseBody
    public Map<String,Object> deleteCollar(@RequestParam("CollId") int CollId) {

        Map<String,Object> resultmap = new HashMap<String,Object>();
       int result = this.iCollarService.deleteCollar(CollId);
        if(result==1){
            resultmap.put("data", "删除成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "删除失败了!");
        }
        return resultmap;
    }

    /**
     * 职工劵明细
     * @return
     */
    @RequestMapping("/getCollarTicketList.do")
    @ResponseBody
    public Map<String, Object> getCollarTicketList(){
        Map<String,Object> results = new HashMap<>();
        List<Map<String,Object>> datas = iCollarService.getCollarTicketList();
        if ( datas != null ){
            results.put("code",1);
            results.put("data",datas);
        } else {
            results.put("code", 0);
            results.put("msg", "无数据！");
        }
        return results;
    }

}
