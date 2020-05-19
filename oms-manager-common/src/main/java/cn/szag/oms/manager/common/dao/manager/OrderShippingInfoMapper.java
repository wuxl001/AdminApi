package cn.szag.oms.manager.common.dao.manager;


import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.ShippinInfoDate;

/**
 * 海运
* @ClassName: OrderShippingInfoMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:29:02
 */
public interface OrderShippingInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderShippingInfo record);

    int insertSelective(OrderShippingInfo record);

    OrderShippingInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderShippingInfo record);

    int updateByPrimaryKey(OrderShippingInfo record);
    /**
     * 根据提单号查找海运信息
    * @Title: selectByOrderNo 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return OrderShippingInfo
    * @throws
     */
    OrderShippingInfo selectByOrderId(@Param("orderId")String orderId,@Param("containerId")String containerId);
    /**
     * 根据订单Id和集装箱Id获取进度时间
    * @Title: selectByOrderNo 
    * @Description: TODO 
    * @param @param orderId
    * @param @param boxNo
    * @param @return
    * @author dengyanghao
    * @return OrderShippingInfo
    * @throws
     */
    ShippinInfoDate select(@Param("orderId")String orderId,@Param("boxNo")String boxNo,@Param("id")String id ,@Param("type")Integer type);
}