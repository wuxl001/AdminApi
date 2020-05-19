package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.OrderEvaluationScore;



/** 
* @ClassName: OrderEvaluationScoreService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:35:59  
*/
public interface OrderEvaluationScoreService {
	
	int deleteByPrimaryKey(String id);

    int insert(OrderEvaluationScore record);

    int insertSelective(OrderEvaluationScore record);

    OrderEvaluationScore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderEvaluationScore record);

    int updateByPrimaryKey(OrderEvaluationScore record);
    /**
     * 根据订单Id和柜号获取评分列表
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @param boxNo
    * @param @return
    * @author dengyanghao
    * @return List<OrderEvaluationScore>
    * @throws
     */
    List<OrderEvaluationScore> select(String orderId,String containerId);
}
