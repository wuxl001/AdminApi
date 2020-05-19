package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

public interface OrderExportMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderExport record);

    int insertSelective(OrderExport record);

    OrderExport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderExport record);

    int updateByPrimaryKeyWithBLOBs(OrderExport record);

    int updateByPrimaryKey(OrderExport record);
    /**
     * 根据传入编号返回会相应的数量
    * @Title: findCount 
    * @Description: TODO 
    * @param @param keyWord
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findCount(@Param("keyWord")int keyWord,@Param("user")User user);
    /**
     * 查询是否存在此订舱号
    * @Title: findByBookingNo 
    * @Description: TODO 
    * @param @param bookingNo
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findByBookingNo(@Param("bookingNo")String bookingNo,@Param("id")String orderId);
    /**
     * 根据用户当前权限获取相应出口订单数据
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param user
    * @param @param orderExport
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderExport>
    * @throws
     */
    List<OrderExport> findByPage(@Param("user")User user,@Param("orderExport")OrderExport orderExport,Page page);
    /**
     * 根据订单号查询对象
    * @Title: findOrderNo 
    * @Description: TODO 
    * @param @param orderNo
    * @param @return
    * @author dengyanghao
    * @return OrderExport
    * @throws
     */
    OrderExport findOrderNo(String orderNo);
    /**
     * 根据编号获取相应条件的订单数量
    * @Title: findOrderCount 
    * @Description: TODO 
    * @param @param keyWord
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findOrderCount(@Param("keyWord")Integer keyWord,@Param("user")User user);
    /**
     * 根据id获取订单信息
    * @Title: find 
    * @Description: TODO 
    * @param @param id
    * @param @return
    * @author dengyanghao
    * @return OrderExport
    * @throws
     */
    OrderExport find(String id);
    /**
     * 查询（未审核/单证未齐全/24小时未审核）订单数量
    * @Title: findOrder 
    * @Description: TODO 
    * @param @param keyWord
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findOrder(@Param("keyWord")Integer keyWord,@Param("user")User user);

    /**
     *
     * @param no
     * @return
     */
    int findOrderNoCount(String no);
}