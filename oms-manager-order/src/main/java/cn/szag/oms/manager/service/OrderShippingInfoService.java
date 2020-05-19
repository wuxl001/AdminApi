package cn.szag.oms.manager.service;


import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.ShippinInfoDate;

/** 
 * 海运
* @ClassName: OrderShippingInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月17日 下午3:15:13  
*/
public interface OrderShippingInfoService {
	int deleteByPrimaryKey(String id);

    int insert(OrderShippingInfo record);

    int insertSelective(OrderShippingInfo record);

    OrderShippingInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderShippingInfo record);

    int updateByPrimaryKey(OrderShippingInfo record);
    /**
     * 根据订单Id和集装箱Id查找海运信息
    * @Title: selectByOrderNo 
    * @Description: TODO 
    * @param @param orderId
    * @param @param containerId
    * @param @return
    * @author dengyanghao
    * @return OrderShippingInfo
    * @throws
     */
    OrderShippingInfo selectByOrderId(String orderId,String containerId);
    /**
     * 根据订单Id和柜号获取进度时间
    * @Title: selectByOrderNo 
    * @Description: TODO 
    * @param @param orderId
    * @param @param boxNo
    * @param @return
    * @author dengyanghao
    * @return OrderShippingInfo
    * @throws
     */
    ShippinInfoDate select(String orderId,String boxNo,String id ,Integer type);
}
