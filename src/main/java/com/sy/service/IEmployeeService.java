package com.sy.service;

import com.sy.pojo.Employee;

import java.util.HashMap;
import java.util.List;

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
}
