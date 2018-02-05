package com.sy.dao;

import com.sy.pojo.Dept;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/1/25.
 */
public interface IDeptDao {

    Dept selectByPrimaryKey(Integer id);
    List<Dept> getListById(Integer deptId);

    /**
     * 查询部门列表
     * @return
     */
    List<Dept> getDeptList();


    /**
     * 根据名称模糊查询部门信息
     * @param DeptName
     * @return
     */
    List<Dept> getNameQueryList(String DeptName);

    /**
     *插入部门信息
     * @param map
     * @return
     */
    int insertDept(HashMap<String,Object> map);


    /**
     * 修改用户信息
     * @param map
     * @return
     */
    int updateDeptByKey(HashMap<String,Object> map);


    /**
     * 根据DeptId删除人员
     * @param DeptId
     * @return
     */
    int deleteDept(int DeptId);


}
