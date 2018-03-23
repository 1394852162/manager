package com.sy.dao;

import com.sy.pojo.Batch;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/6.
 */
public interface IBatchDao {
    /**
     * 查询批次列表
     * @return
     */
    List<Batch> getBatList();

    /**
     * 根据批次名称查询批次
     * @return BatName
     */
    List<Batch> queryNameBatList(String BatName);

    /**
     *添加一个批次
     * @param map
     * @return
     */
    int insertBatch(HashMap<String,Object> map);



    /**
     * 修改批次信息
     * @param map
     * @return
     */
    int updateBatchByKey(HashMap<String,Object> map);


    /**
     * 根据BatId删除批次
     * @param BatId
     * @return
     */
    int deleteBatch(int BatId);

    /**
     * 查询批次信息根据部门Id
     * @param map
     * @return
     */
    List<Batch> getBatListbyDept(HashMap<String,Object> map);

}
