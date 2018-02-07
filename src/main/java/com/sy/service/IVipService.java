package com.sy.service;

import com.sy.pojo.VipTicket;

import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/7.
 */
public interface IVipService {

   public List<VipTicket> getVipList();


    public List<VipTicket> QueryNameByList(String Vipname);


    public int insertVipTicket(HashMap<String, Object> map);


    public int updateVipTicket(HashMap<String, Object> map);


    public int deleteVipTicket(int VipId);

}
