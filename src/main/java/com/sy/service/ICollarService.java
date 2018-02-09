package com.sy.service;

import com.sy.pojo.Collar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/8.
 */
public interface ICollarService {
    /**
     *查询领取列表
     * @return
     */
   public List<Collar> getCollList();

    /**
     * 根据条件模糊查询
     * @return
     */
   public List<Collar> querySelectList(String EmpName, String EmpNo, Date BeginDate, Date EndDate);

    /**
     * 添加一条领用记录
     * @param map
     * @return
     */
    public int insertCollar(HashMap<String,Object> map);

    /**
     * 修改一条领用记录
     * @param map
     * @return
     */
   public int updateCollar(HashMap<String,Object> map);

    /**
     * 删除一条领用记录
     * @param CollId
     * @return
     */
   public int deleteCollar(int CollId);
}
