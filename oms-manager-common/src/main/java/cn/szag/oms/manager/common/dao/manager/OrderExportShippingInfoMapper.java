package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderExportShippingInfo;

public interface OrderExportShippingInfoMapper {
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
    OrderExportShippingInfo find(@Param("orderId")String orderId,@Param("containerId")String containerId);
}