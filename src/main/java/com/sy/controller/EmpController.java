package com.sy.controller;

import com.sy.pojo.Employee;
import com.sy.service.IEmployeeService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haswell on 2018/1/29.
 */
@Controller
@RequestMapping("/employee")
public class EmpController {
    @Resource
    private IEmployeeService iEmpService;
    Employee emp = new Employee();


    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public HashMap<String,Object> UserLogin(@RequestParam("name") String username, @RequestParam("password") String password,HttpSession session) {

        emp.setEmpName(username);
        emp.setEmpPassword(password);
        boolean EmpExit = iEmpService.EmployeeLogin(emp);
        List<Employee> list = iEmpService.getEmpInfo(emp);
        if(EmpExit==true){
            emp.setEmpId(list.get(0).getEmpId());
            emp.setDeptId(list.get(0).getDeptId());
            emp.setEmpNo(list.get(0).getEmpNo());
        }
        HashMap<String,Object> result = new HashMap<String,Object>();
        System.out.println("存入到对象的数据"+emp);
        result.put("IfExit",EmpExit+"");
        session.setAttribute("User", emp);
        return result;
    }

    /**
     * 将用户的信息放入到employee
     * @return
     */
    @RequestMapping("/getSessionUsername.do")
    @ResponseBody
    public Map<String, Object> getSessionUsername(){
        Map<String, Object> result = new HashMap<String, Object>();
        if(emp == null){
            result.put("code",0);
        }else{
            result.put("code", emp);
        }
        System.out.println(emp);
        return result;
    }

    @RequestMapping("/getEmpInfo.do")
    @ResponseBody
    public List<Employee> getEmpInfo(@RequestParam("name") String username, @RequestParam("password") String password) {
        emp.setEmpName(username);
        emp.setEmpPassword(password);
        List<Employee> list = iEmpService.getEmpInfo(emp);
    return list;
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    @RequestMapping("/updatepwd.do")
    @ResponseBody
    public HashMap<String,Object> updatepwd(@RequestParam("password") String password){
        emp.setEmpPassword(password);
        int result = iEmpService.updatepwd(emp);
        HashMap<String,Object> map  = new HashMap<String,Object>();
        map.put("code",result);
        return map;
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping("/getEmpList.do")
    @ResponseBody
    public Map<String,Object> getEmpList() {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Employee> list = iEmpService.getEmpList();
        if(list != null & list.size()>0) {
            resultmap.put("data", list);
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "无数据!");
        }
        return resultmap;
    }

    /**
     * 根据用户名查询用户
     * @param EmpName
     * @return
     */

    @RequestMapping("/getNameQueryList.do")
    @ResponseBody
    public Map<String,Object> getNameQueryList(@RequestParam("EmpName") String EmpName) {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Employee> list =iEmpService.getNameQueryList(EmpName);
        if(list != null & list.size()>0){
            resultmap.put("data", list);
            resultmap.put("code", 1);
        }else{
            resultmap.put("code",0);
            resultmap.put("data","无数据");
        }
        return resultmap;
    }

    /**
     * 添加用户 参数待定
     * @return
     */
    @RequestMapping("/insertEmp.do")
    @ResponseBody
    public  Map<String,Object> insertEmp(@RequestParam("EmpName") String EmpName,@RequestParam("EmpPassword") String EmpPassword,
                         @RequestParam("DeptId") int DeptId,@RequestParam("EmpBirth") String EmpBirth,
                         @RequestParam("EmpStatus1") int EmpStatus1,@RequestParam("EmpStatus2") int EmpStatus2,
                         @RequestParam("EmpNo") String EmpNo) {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        if(EmpName==""||EmpPassword==""||EmpBirth==""||EmpNo==""){
            resultmap.put("data", "添加用户失败!用户信息不完整,请仔细核对");
            resultmap.put("code", 0);
            return resultmap;
        }else if(DeptId==-1||EmpStatus1==-1||EmpStatus2==-1){
            resultmap.put("data", "添加用户失败!用户信息不完整,请仔细核对");
            resultmap.put("code", 0);
            return resultmap;
        }
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("EmpNo",EmpNo);
        map.put("EmpName",EmpName);
        map.put("EmpPassword",EmpPassword);
        map.put("DeptId",DeptId);
        map.put("EmpBirth",EmpBirth);
        map.put("EmpStatus1",EmpStatus1);
        map.put("EmpStatus2",EmpStatus2);
        int result = iEmpService.insertEmp(map);

        if(result==1){
            resultmap.put("data", "添加用户成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "添加用户失败!");
            resultmap.put("code", 0);
        }
        return resultmap;
    }

    /**
     * 用户修改
     * @return
     */
    @RequestMapping("/updateEmp.do")
    @ResponseBody
    public  Map<String,Object> updateByPrimaryKey(@RequestParam("EmpName") String EmpName,@RequestParam("EmpPassword") String EmpPassword,
                                  @RequestParam("DeptId") int DeptId,@RequestParam("EmpBirth") String EmpBirth,
                                  @RequestParam("EmpStatus1") int EmpStatus1,@RequestParam("EmpStatus2") int EmpStatus2,
                                  @RequestParam("EmpNo") String EmpNo,@RequestParam("EmpId") int EmpId ){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("EmpId",EmpId);
        map.put("EmpNo",EmpNo);
        map.put("EmpName",EmpName);
        map.put("EmpPassword",EmpPassword);
        map.put("DeptId",DeptId);
        map.put("EmpBirth",EmpBirth);
        map.put("EmpStatus1",EmpStatus1);
        map.put("EmpStatus2",EmpStatus2);
        int result  = iEmpService.updateByPrimaryKey(map);
        if(result==1){
            resultmap.put("data", "修改用户成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("data", "修改用户失败!");
            resultmap.put("code", 0);
        }
        return resultmap;
    }

    /**
     * 根据EmpId删除用户
     * @param EmpId
     * @return
     */
    @RequestMapping("/deleteEmp.do")
    @ResponseBody
    public Map<String,Object> deleteEmp(@RequestParam("EmpId") int EmpId){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        int result = iEmpService.deleteEmp(EmpId);
        if(result==1){
            resultmap.put("data", "删除成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "删除失败!");
        }
        return resultmap;
    }


}
