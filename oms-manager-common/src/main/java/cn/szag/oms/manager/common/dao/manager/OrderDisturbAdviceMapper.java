package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.OrderDisturbAdvice;
/**
 * 设置通知免打扰
* @ClassName: OrderDisturbAdviceMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月26日 下午7:21:43
 */
public interface OrderDisturbAdviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderDisturbAdvice record);

    int insertSelective(OrderDisturbAdvice record);

    OrderDisturbAdvice selectByPrimaryKey(OrderDisturbAdvice record);

    int updateByPrimaryKeySelective(OrderDisturbAdvice record);

    int updateByPrimaryKey(OrderDisturbAdvice record);
}