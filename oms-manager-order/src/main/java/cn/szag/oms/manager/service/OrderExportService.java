package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
/** 
* @ClassName: OrderExportService 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月11日 下午4:51:26  
*/
public interface OrderExportService {
	
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
    int findCount(int keyWord,User user);
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
    int findByBookingNo(String bookingNo,String orderId);
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
    List<OrderExport> findByPage(User user,OrderExport orderExport,Page page);
    /**
     * 获取订单号
    * @Title: generateOrderNo 
    * @Description: TODO 
    * @param @param orderExport
    * @param @return
    * @author dengyanghao
    * @return String
    * @throws
     */
    String generateOrderNo(OrderExport orderExport);
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
    int findOrderCount(Integer keyWord,User user);
    
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
    int findOrder(Integer keyWord,User user);

    /**
     * 根据工作单号查询有效数据总数量
     * @param no
     * @return
     */
    int findOrderNoCount(String no);
}
