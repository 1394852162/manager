package com.sy.service;

import com.sy.pojo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haswell on 2018/1/29.
 */
public interface IEmployeeService {

    public boolean EmployeeLogin(Employee employee);

    public List<Employee> getEmpInfo(Employee employee);

    public int updatepwd(Employee employee);

    public List<Employee> getEmpList();

    public int insertEmp(HashMap<String,Object> map);

    public int updateByPrimaryKey(HashMap<String,Object> map);

    public int deleteEmp(int EmpId);

    public List<Employee> getNameQueryList(String EmpName);

    public List<Employee> getBatEmpInfo(int BatId,int EmpId);

    /**
     * 部门-职工树查询
     * @return
     */
    List<Map<String, Object>> getDeptEmpTree();

    /**
     * 跟新部门-职工的状态
     * @param param
     * @return
     */
    int updateDeptEmpTree(Map<String, Object> param);


    public List<Employee> QueryDeptEmp(int DeptId);



    public Employee getQueryEmpInfo(Employee employee);


    /**
     * 查询该人员所在的部门列表（admin权限查询所有）
     * @param map
     * @return
     */
    public List<Employee> selectDeptListBySession(HashMap<String,Object> map);

    /**
     * 查询该部门下的人员（admin权限查询所有）
     * @param map
     * @return
     */
    public List<Employee> selectEmpListbyDeptId(HashMap<String,Object> map);
}
