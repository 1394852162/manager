package com.sy.dao;

import com.sy.pojo.VipTicket;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/7.
 */
public interface IVipTicketDao {
    /**
     * 查询vip票补入列表
     * @return
     */
    List<VipTicket> getVipList();

    /**
     * 根据名称模糊查询
     * @param Vipname
     * @return
     */
    List<VipTicket> QueryNameByList(String Vipname);

    /**
     * 插入一条记录
     * @param map
     * @return
     */
    int insertVipTicket(HashMap<String,Object> map);

    /**
     * 修改VIP票领取的信息
     * @param map
     * @return
     */
    int updateVipTicket(HashMap<String,Object> map);

    /**
     * 删除Vip信息
     * @param VipId
     * @return
     */
    int deleteVipTicket(int VipId);


}
