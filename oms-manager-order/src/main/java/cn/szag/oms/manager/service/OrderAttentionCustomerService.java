package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderAttentionCustomer;

/** 
* @ClassName: OrderAttentionCustomerService 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月29日 下午4:48:18  
*/

public interface OrderAttentionCustomerService {
	int deleteByPrimaryKey(String id);

    int insert(OrderAttentionCustomer record);

    int insertSelective(OrderAttentionCustomer record);

    OrderAttentionCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAttentionCustomer record);

    int updateByPrimaryKey(OrderAttentionCustomer record);
	
}
