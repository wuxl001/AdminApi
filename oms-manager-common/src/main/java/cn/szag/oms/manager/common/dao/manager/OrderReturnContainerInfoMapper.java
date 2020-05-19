package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderReturnContainerInfo;

/**
 * 报空信息
* @ClassName: OrderReturnContainerInfoMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月17日 上午8:46:33
 */
public interface OrderReturnContainerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderReturnContainerInfo record);

    int insertSelective(OrderReturnContainerInfo record);

    OrderReturnContainerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderReturnContainerInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderReturnContainerInfo record);

    int updateByPrimaryKey(OrderReturnContainerInfo record);
    /**
     * 根据订单Id以及柜号获取报空信息
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @param boxNo
    * @param @return
    * @author dengyanghao
    * @return OrderReturnContainerInfo
    * @throws
     */
    OrderReturnContainerInfo select(@Param("orderId")String orderId,@Param("containerId")String containerId);
}