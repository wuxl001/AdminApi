package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderClearanceInfo;

/**
 * 报关信息
* @ClassName: OrderClearanceInfoMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 下午4:41:45
 */
public interface OrderClearanceInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderClearanceInfo record);

    int insertSelective(OrderClearanceInfo record);

    OrderClearanceInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderClearanceInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderClearanceInfo record);

    int updateByPrimaryKey(OrderClearanceInfo record);
    /**
     * 根据部分信息查询报关明细
    * @Title: select 
    * @Description: TODO 
    * @param @param record
    * @param @return
    * @author dengyanghao
    * @return OrderClearanceInfo
    * @throws
     */
    OrderClearanceInfo select(@Param("orderId")String orderId,@Param("containerId")String containerId);
}