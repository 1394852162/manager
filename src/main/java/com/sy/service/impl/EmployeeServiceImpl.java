package com.sy.service.impl;

import com.sy.dao.IEmployeeDao;
import com.sy.pojo.Employee;
import com.sy.service.IEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/1/29.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{


    @Resource
    private IEmployeeDao iEmployeeDao;

    @Override
    public boolean EmployeeLogin(Employee employee) {
       // System.out.println("*********"+this.iEmployeeDao.ifExist(employee));
        return this.iEmployeeDao.ifExist(employee);
    }

    @Override
    public int updatepwd(Employee employee) {
        return this.iEmployeeDao.updatepwd(employee);
    }

    @Override
    public List<Employee> getEmpInfo(Employee employee) {
        return this.iEmployeeDao.getEmpInfo(employee);
    }

    @Override
    public List<Employee> getEmpList() {
        return this.iEmployeeDao.getEmpList();
    }

    @Override
    public int insertEmp(HashMap<String,Object> map) {
        return this.iEmployeeDao.insertEmp(map);
    }

    @Override
    public int updateByPrimaryKey(HashMap<String, Object> map) {
        return this.iEmployeeDao.updateByPrimaryKey(map);
    }

    @Override
    public int deleteEmp(int EmpId) {
        return this.iEmployeeDao.deleteEmp(EmpId);
    }

    @Override
    public List<Employee> getNameQueryList(String EmpName) {
        return this.iEmployeeDao.getNameQueryList(EmpName);
    }

    @Override
    public List<Employee> getBatEmpInfo(int BatId) {
        return this.iEmployeeDao.getBatEmpInfo(BatId);
    }
}
