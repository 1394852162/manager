package com.sy.service;

import com.sy.pojo.Dept;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/1/25.
 */
public interface IDeptService {
    public Dept getUserById(int id);
    public List<Dept> GetDeptList(int deptid);

    public List<Dept> GetQueryDeptList();

    public List<Dept> getNameQueryList(String DeptName);

    public  int insertDept(HashMap<String,Object> map);

    public int updateDeptByKey(HashMap<String,Object> map);

    public int deleteDept(int DeptId);
}
