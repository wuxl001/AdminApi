package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderPaymentInfo;

/** 
 * 付汇
* @ClassName: OrderPaymentInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月17日 上午10:18:29  
*/
public interface OrderPaymentInfoService {
	int deleteByPrimaryKey(String id);

    int insert(OrderPaymentInfo record);

    int insertSelective(OrderPaymentInfo record);

    OrderPaymentInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderPaymentInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderPaymentInfo record);

    int updateByPrimaryKey(OrderPaymentInfo record);
    /**
     * 根据orderId查询付汇
    * @Title: selectByOrderId 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return OrderPaymentInfo
    * @throws
     */
    OrderPaymentInfo selectByOrderId(String orderId);

}
