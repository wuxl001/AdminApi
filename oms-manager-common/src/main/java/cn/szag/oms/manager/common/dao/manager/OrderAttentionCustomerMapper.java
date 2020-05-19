package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.OrderAttentionCustomer;

public interface OrderAttentionCustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderAttentionCustomer record);

    int insertSelective(OrderAttentionCustomer record);

    OrderAttentionCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAttentionCustomer record);

    int updateByPrimaryKey(OrderAttentionCustomer record);
}