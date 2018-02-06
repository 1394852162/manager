package com.sy.service.impl;

import com.sy.dao.IBatchDao;
import com.sy.pojo.Batch;
import com.sy.service.IBatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/6.
 */
@Service("batchService")
public class BatchServiceImpl implements IBatchService{

    @Resource
    private IBatchDao iBatchDao;


    @Override
    public List<Batch> getBatList() {
        return this.iBatchDao.getBatList();
    }

    @Override
    public List<Batch> queryNameBatList(String BatName) {
        return this.iBatchDao.queryNameBatList(BatName);
    }

    @Override
    public int insertBatch(HashMap<String, Object> map) {
        return this.iBatchDao.insertBatch(map);
    }

    @Override
    public int updateBatchByKey(HashMap<String, Object> map) {
        return this.iBatchDao.updateBatchByKey(map);
    }

    @Override
    public int deleteBatch(int BatId) {
        return this.iBatchDao.deleteBatch(BatId);
    }
}
