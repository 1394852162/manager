package com.sy.controller;

import com.sy.pojo.Dept;
import com.sy.pojo.Employee;
import com.sy.service.IEmployeeService;
import com.sy.service.IVipService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Resource
    private IVipService iVipService;
    Employee emp = new Employee();

    /**
     * 查询人员剩余的票数
     * @param BatId
     * @return
     */
    @RequestMapping("/getBatEmpInfo.do")
    @ResponseBody
    public Map<String,Object> getBatEmpInfo(@RequestParam("BatId") int BatId,@RequestParam("EmpId") int EmpId) {
        System.out.println(BatId+"+++++++++++"+EmpId);
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<Employee> list = iEmpService.getBatEmpInfo(BatId,EmpId);
        if(list != null & list.size()>0) {
            resultmap.put("data", list);
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "未查到!");
        }
        return resultmap;
    }

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
        if(EmpExit==true){
            emp = iEmpService.getQueryEmpInfo(emp);
        }
        HashMap<String,Object> result = new HashMap<String,Object>();
        System.out.println("存入到对象的数据"+emp);
        result.put("IfExit",EmpExit+"");
        result.put("emp",emp);
        session.setAttribute("User", emp);
        return result;
    }

    @RequestMapping("/layout.do")
    public String layout(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/login.html";
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
                         @RequestParam("DeptId") int DeptId,
                         @RequestParam("EmpStatus1") int EmpStatus1,@RequestParam("EmpStatus2") int EmpStatus2,
                         @RequestParam("EmpNo") String EmpNo) {
        Map<String,Object> resultmap = new HashMap<String,Object>();
        if(EmpName==""||EmpPassword==""||EmpNo==""){
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
                                  @RequestParam("DeptId") int DeptId,
                                  @RequestParam("EmpStatus1") int EmpStatus1,@RequestParam("EmpStatus2") int EmpStatus2,
                                  @RequestParam("EmpNo") String EmpNo,@RequestParam("EmpId") int EmpId ){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("EmpId",EmpId);
        map.put("EmpNo",EmpNo);
        map.put("EmpName",EmpName);
        map.put("EmpPassword",EmpPassword);
        map.put("DeptId",DeptId);

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
        if(EmpId==emp.getEmpId()){
            resultmap.put("code", 0);
            resultmap.put("data", "请不要删除自己!");
            return resultmap;
        }
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



    /**
     * 添加一条记录
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insertVipTicket.do")
    @ResponseBody
    public Map<String,Object> insertVipTicket(@RequestBody HashMap<String,Object> param) throws ParseException {


        Map<String,Object> resultmap = new HashMap<String,Object>();
/*用时改为param.put("EmpId",emp.getEmpId());*/
        param.put("EmpId",1);

        int result =this.iVipService.insertVipTicket(param);
        if(result==1){
            resultmap.put("data", "添加成功!");
            resultmap.put("code", 1);
        }else{
            resultmap.put("code", 0);
            resultmap.put("data", "添加失败了!");
        }
        return resultmap;
    }

    /**
     * 部门-职工树查询
     * @return
     */
    @RequestMapping("/getDeptEmpTree.do")
    @ResponseBody
    public Map<String, Object> getDeptEmpTree(){
        Map<String,Object> result = new HashMap<>();

        List<Map<String,Object>> data = iEmpService.getDeptEmpTree();

        if ( data != null ){
            result.put("code",1);
            result.put("data",data);
        } else {
            result.put("code", 0);
            result.put("msg", "无数据！");
        }
        return result;
    }

    /**
     * 跟新部门-职工的状态
     * @param param
     * @return
     */
    @RequestMapping("/updateDeptEmpTree.do")
    @ResponseBody
    public Map<String,Object> updateDeptEmpTree(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        int rowCount = iEmpService.updateDeptEmpTree(param);
        try {
            if ( rowCount > 0 ) {
                result.put("code", 1);
                result.put("msg", "操作成功");
            } else {
                result.put("code", 0);
                result.put("msg", "操作失败");
            }
        } catch (Exception e) {
            result.put("code", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }


    /**
     * 查询部门下的员工
     * @param DeptId
     * @return
     */
    @RequestMapping("/QueryDeptEmp.do")
    @ResponseBody
    public Map<String,Object> QueryDeptEmp(@RequestParam("DeptId") int DeptId   ){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        System.out.println("开始进入");
        List<Employee> list = iEmpService.QueryDeptEmp(DeptId);
        System.out.println("数据为"+list);
        if(list != null & list.size()>0) {
            resultmap.put("data",list);
            resultmap.put("code",1);
        }else{
            resultmap.put("data","查不到该部门下的人员");
            resultmap.put("code",0);
        }
        return resultmap;
    }


}