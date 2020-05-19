package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderDisturbAdvice;

/** 设置消息免打扰
* @ClassName: OrderDisturbAdviceService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月26日 下午7:26:01  
*/
public interface OrderDisturbAdviceService {
	int deleteByPrimaryKey(String id);

    int insert(OrderDisturbAdvice record);

    int insertSelective(OrderDisturbAdvice record);

    OrderDisturbAdvice selectByPrimaryKey(OrderDisturbAdvice record);

    int updateByPrimaryKeySelective(OrderDisturbAdvice record);

    int updateByPrimaryKey(OrderDisturbAdvice record);

	void insertSelective2(OrderDisturbAdvice record);

}
