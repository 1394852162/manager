package com.sy.dao;

import com.sy.pojo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haswell on 2018/1/29.
 */
public interface IEmployeeDao {
    /**
     * 登录判断用户名密码是否存在
     * @param employee
     * @return
     */
    boolean ifExist(Employee employee);

    /**
     * 获取每个人剩余的票数
     * @param BatId
     * @return
     */
    List<Employee> getBatEmpInfo(int BatId,int EmpId);

    /**
     * 根据用户名密码查询登录人员的信息
     * @param employee
     * @return
     */
    List<Employee> getEmpInfo(Employee employee);

    /**
     * 更改用户密码
     * @param employee
     * @return
     */
    int updatepwd(Employee employee);


    /**
     * 获取查询的所有可用用户列表信息
     * @return
     */
    List<Employee> getEmpList();

    /**
     * 插入一条用户记录
     * @param map
     * @return
     */
    int insertEmp(HashMap<String,Object> map);

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    int updateByPrimaryKey(HashMap<String,Object> map);

    /**
     * 根据EmpId删除人员
     * @param EmpId
     * @return
     */
    int deleteEmp(int EmpId);

    /**
     * 根据名称模糊查询
     * @param EmpName
     * @return
     */
    List<Employee> getNameQueryList(String EmpName);

    /**
     * 部门-职工树查询
     * @return
     */
    List<Map<String, Object>> getDeptEmpTree();

    //跟新地区
    int updateDeptEmpTree(Map<String, Object> param);

    /**
     * 根据DeptID查询部门下的人员
     * @param DeptId
     * @return
     */
    List<Employee> QueryDeptEmp(int DeptId);

}
