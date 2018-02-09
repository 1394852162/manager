package com.sy.service.impl;

import com.sy.dao.ICollarDao;
import com.sy.pojo.Collar;
import com.sy.service.ICollarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/8.
 */
@Service("collarService")
public class CollarServiceImpl implements ICollarService {

    @Resource
    private ICollarDao iCollarDao;

    @Override
    public List<Collar> getCollList() {
        return this.iCollarDao.getCollList();
    }

    @Override
    public List<Collar> querySelectList(HashMap<String,Object> map) {
        return this.iCollarDao.querySelectList(map);
    }

    @Override
    public int insertCollar(HashMap<String, Object> map) {
        return this.iCollarDao.insertCollar(map);
    }

    @Override
    public int updateCollar(HashMap<String, Object> map) {
        return this.iCollarDao.updateCollar(map);
    }

    @Override
    public int deleteCollar(int CollId) {
        return this.iCollarDao.deleteCollar(CollId);
    }
}
