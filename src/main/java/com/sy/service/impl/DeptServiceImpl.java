package com.sy.service.impl;

import com.sy.dao.IDeptDao;
import com.sy.pojo.Dept;
import com.sy.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/1/25.
 */
@Service("deptService")
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDao deptdao;

    @Override
    public Dept getUserById(int id) {
        return this.deptdao.selectByPrimaryKey(id);
    }

    @Override
    public List<Dept> GetDeptList(int deptid) {
        return this.deptdao.getListById(deptid);
    }

    @Override
    public List<Dept> GetQueryDeptList() {
        return this.deptdao.getDeptList();
    }

    @Override
    public List<Dept> getNameQueryList(String DeptName) {
        return this.deptdao.getNameQueryList(DeptName);
    }

    @Override
    public int insertDept(HashMap<String, Object> map) {
        return this.deptdao.insertDept(map);
    }

    @Override
    public int updateDeptByKey(HashMap<String, Object> map) {
        return this.deptdao.updateDeptByKey(map);
    }

    @Override
    public int deleteDept(int DeptId) {
        return this.deptdao.deleteDept(DeptId);
    }
}
