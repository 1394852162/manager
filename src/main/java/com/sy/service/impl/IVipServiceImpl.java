package com.sy.service.impl;

import com.sy.dao.IVipTicketDao;
import com.sy.pojo.VipTicket;
import com.sy.service.IVipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by haswell on 2018/2/7.
 */
@Service("iVipService")
public class IVipServiceImpl implements IVipService{

    @Resource
    private IVipTicketDao iVipTicketDao;

    @Override
    public List<VipTicket> getVipList() {
        return this.iVipTicketDao.getVipList();
    }

    @Override
    public List<VipTicket> QueryNameByList(String Vipname) {
        return this.iVipTicketDao.QueryNameByList(Vipname);
    }

    @Override
    public int insertVipTicket(HashMap<String, Object> map) {
        return this.iVipTicketDao.insertVipTicket(map);
    }

    @Override
    public int updateVipTicket(HashMap<String, Object> map) {
        return this.iVipTicketDao.updateVipTicket(map);
    }

    @Override
    public int deleteVipTicket(int VipId) {
        return this.iVipTicketDao.deleteVipTicket(VipId);
    }
}
