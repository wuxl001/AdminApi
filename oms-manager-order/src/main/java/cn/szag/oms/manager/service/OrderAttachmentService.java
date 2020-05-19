package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.OrderAttachment;


/**
 * 单证信息
* @ClassName: OrderAttachmentService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月17日 上午9:12:56
 */
public interface OrderAttachmentService {
	int deleteByPrimaryKey(String id);

    int insert(OrderAttachment record);

    int insertSelective(OrderAttachment record);

    OrderAttachment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAttachment record);

    int updateByPrimaryKey(OrderAttachment record);
    /**
     * 根据订单Id查询单证
    * @Title: selectByOrder 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return List<OrderAttachment>
    * @throws
     */
    List<OrderAttachment> selectByOrderId(String orderId);
    
    int findByOrderIdCount(String orderId);
}
