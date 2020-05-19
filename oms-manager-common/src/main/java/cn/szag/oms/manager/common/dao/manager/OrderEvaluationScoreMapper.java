package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderEvaluationScore;

/**
 * 评价
* @ClassName: OrderEvaluationScoreMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:28:49
 */
public interface OrderEvaluationScoreMapper {
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
    List<OrderEvaluationScore> select(@Param("orderId")String orderId,@Param("containerId")String containerId);
}