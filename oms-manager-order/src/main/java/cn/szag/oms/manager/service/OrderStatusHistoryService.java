package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderStatusHistory;

public interface OrderStatusHistoryService {
	
	int deleteByPrimaryKey(String id);

    int insert(OrderStatusHistory record);

    int insertSelective(OrderStatusHistory record);

    OrderStatusHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderStatusHistory record);

    int updateByPrimaryKeyWithBLOBs(OrderStatusHistory record);

    int updateByPrimaryKey(OrderStatusHistory record);
    
    List<OrderStatusHistory> selectList(OrderStatusHistory record);

}
