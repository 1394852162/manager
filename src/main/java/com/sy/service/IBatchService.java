package com.sy.service;

import com.sy.pojo.Batch;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/6.
 */
public interface IBatchService {
    public List<Batch> getBatList();

    public List<Batch> queryNameBatList(String BatName);

    public int insertBatch(HashMap<String,Object> map);

    public int updateBatchByKey(HashMap<String,Object> map);

    public int deleteBatch(int BatId);

    public List<Batch> getBatListbyDept(HashMap<String,Object> map);

}
