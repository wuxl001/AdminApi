package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.BaseBoxNoMessage;
import cn.szag.oms.manager.common.domain.manager.EShippinInfoDate;
import cn.szag.oms.manager.common.domain.manager.OrderWorklistStatus;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

public interface OrderWorklistStatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderWorklistStatus record);

    int insertSelective(OrderWorklistStatus record);

    OrderWorklistStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderWorklistStatus record);

    int updateByPrimaryKey(OrderWorklistStatus record);
    /**
     * 查询所有
    * @Title: select 
    * @Description: TODO 
    * @param @return
    * @author dengyanghao
    * @return List<OrderWorklistStatus>
    * @throws
     */
    List<OrderWorklistStatus> select(@Param("orderId")String orderId);
    
    /**
     * 根据输入条件查询列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param ows
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderWorklistStatus>
    * @throws
     */
    List<OrderWorklistStatus> findByPage(@Param("ows")OrderWorklistStatus ows , @Param("user")User user,@Param("page")Page page);
    /**
     * 查询集装箱信息
    * @Title: selectOrderId 
    * @Description: TODO 
    * @param @param orderId
    * @param @param status
    * @param @return
    * @author dengyanghao
    * @return List<BaseBoxNoMessage>
    * @throws
     */
    List<BaseBoxNoMessage> selectOrderId(@Param("orderId")String orderId,@Param("status")String status,@Param("containerId")String containerId);
    /**
     * 根据报关单查询集装箱
    * @Title: findByClearanceId 
    * @Description: TODO 
    * @param @param clearanceId
    * @param @return
    * @author dengyanghao
    * @return List<OrderWorklistStatus>
    * @throws
     */
    List<OrderWorklistStatus> findByClearanceId(@Param("clearanceId")String clearanceId);
    /**
     * 根据编号查询对应的下拉数据
    * @Title: selectDown 
    * @Description: TODO 
    * @param @param keyWord
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return List<String>
    * @throws
     */
    List<String> selectDown(@Param("keyWord")String keyWord,@Param("user")User user);
    /**
     * 根据输入条件查询出口列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param ows
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderWorklistStatus>
    * @throws
     */
    List<OrderWorklistStatus> findByPageE(@Param("ows")OrderWorklistStatus ows , @Param("user")User user,@Param("page")Page page);
    /**
     * 根据订单id和柜id查询进度时间信息
    * @Title: findExportScheduleDate 
    * @Description: TODO 
    * @param @param orderId
    * @param @param id
    * @param @return
    * @author dengyanghao
    * @return EShippinInfoDate
    * @throws
     */
    EShippinInfoDate findExportScheduleDate(@Param("orderId")String orderId,@Param("id")String id);
    /**
     * 查询集装箱信息
    * @Title: selectOrderId 
    * @Description: TODO 
    * @param @param orderId
    * @param @param status
    * @param @return
    * @author dengyanghao
    * @return List<BaseBoxNoMessage>
    * @throws
     */
    List<BaseBoxNoMessage> selectOrderEId(@Param("orderId")String orderId,@Param("status")String status,@Param("containerId")String containerId);
    /**
     * 根据输入条件查询出口列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param ows
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderWorklistStatus>
    * @throws
     */
    List<OrderWorklistStatus> findByPageMove(@Param("ows")OrderWorklistStatus ows , @Param("user")User user,@Param("page")Page page);
}