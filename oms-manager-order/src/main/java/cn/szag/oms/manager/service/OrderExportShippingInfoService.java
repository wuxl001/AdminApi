package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderExportShippingInfo;

/** 
* @ClassName: OrderExportShippingInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月11日 下午5:22:51  
*/
public interface OrderExportShippingInfoService {
	int deleteByPrimaryKey(String id);

    int insert(OrderExportShippingInfo record);

    int insertSelective(OrderExportShippingInfo record);

    OrderExportShippingInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderExportShippingInfo record);

    int updateByPrimaryKey(OrderExportShippingInfo record);
    /**
     * 根据订单Id和集装箱Id获取出口海运信息
    * @Title: find 
    * @Description: TODO 
    * @param @param orderId
    * @param @param containerId
    * @param @return
    * @author dengyanghao
    * @return OrderExportShippingInfo
    * @throws
     */
    OrderExportShippingInfo find(String orderId,String containerId);

}
