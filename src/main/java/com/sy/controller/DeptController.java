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

    /**
     * ��ѯ�����б�
     * @return
     */
    @RequestMapping("/getDeptList.do")
    @ResponseBody
    public Map<String,Object>  GetDeptList(){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        System.out.println("??ʼ��??");
        List<Dept> list = this.DeptService.GetQueryDeptList();
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }
        else{
            resultmap.put("data","��ѯʧ��");
            resultmap.put("code",0);
        }
        return resultmap;
    }

    /**
     * ���ݲ������Ʋ�ѯ����
     * @param DeptName
     * @return
     */
    @RequestMapping("/getNameQueryList.do")
    @ResponseBody
    public Map<String,Object>  getNameQueryList(@RequestParam("DeptName") String DeptName){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        System.out.println("ǰ̨�����name========"+DeptName);
        List<Dept> list = this.DeptService.getNameQueryList(DeptName);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }else{
            resultmap.put("data","�鲻���ò���");
            resultmap.put("code",0);
        }
        return resultmap;
    }

    /**
     * ��Ӳ�����Ϣ
     * @param DeptNo
     * @param DeptName
     * @return
     */
    @RequestMapping("/insertDept.do")
    @ResponseBody
    public Map<String,Object>  insertDept(@RequestParam("DeptNo") String DeptNo,@RequestParam("DeptName") String DeptName){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(DeptNo.equals("")||DeptName.equals("")){
            resultmap.put("data", "�����������Ĳ�����Ϣ!");
            resultmap.put("code", 0);
            return resultmap;
        }
        map.put("DeptNo",DeptNo);
        map.put("DeptName",DeptName);
        int result = this.DeptService.insertDept(map);
        if(result==1){
            resultmap.put("data", "��Ӳ��ųɹ�!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "��Ӳ���ʧ��!");
            resultmap.put("code", 0);
        }
        return resultmap;
    }

    /**
     * �޸Ĳ�����Ϣ����DeptId
     * @param DeptNo
     * @param DeptName
     * @param DeptId
     * @return
     */
    @RequestMapping("/updateDeptByKey")
    @ResponseBody
    public Map<String,Object>  updateDeptByKey(@RequestParam("DeptNo") String DeptNo,@RequestParam("DeptName") String DeptName,
                                               @RequestParam("DeptId") int DeptId){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(DeptNo.equals("")||DeptName.equals("")){
            resultmap.put("data", "�����������Ĳ�����Ϣ!");
            resultmap.put("code", 0);
            return resultmap;
        }
        map.put("DeptNo",DeptNo);
        map.put("DeptName",DeptName);
        map.put("DeptId",DeptId);
        int result = this.DeptService.updateDeptByKey(map);
        if(result==1){
            resultmap.put("data", "�޸Ĳ��ųɹ�!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "�޸Ĳ���ʧ��!");
            resultmap.put("code", 0);
        }
        return resultmap;
    }

    /**
     * ����DeptIdɾ������
     * @param DeptId
     * @return
     */
    @RequestMapping("/deleteDept")
    @ResponseBody
    public Map<String,Object>  deleteDept(@RequestParam("DeptId") int DeptId){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        int result = this.DeptService.deleteDept(DeptId);
        if(result==1){
            resultmap.put("data", "����ɾ���ɹ�!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "����ɾ��ʧ��!");
            resultmap.put("code", 0);
        }
        return resultmap;

    }


}
