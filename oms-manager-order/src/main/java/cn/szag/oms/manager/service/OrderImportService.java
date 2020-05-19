package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;




/** 
 * 进口订单
* @ClassName: OrderImportService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 上午10:19:40  
*/
public interface OrderImportService {
	int deleteByPrimaryKey(String id);

    int insert(OrderImport record);

    int insertSelective(OrderImport record);

    OrderImport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderImport record);

    int updateByPrimaryKeyWithBLOBs(OrderImport record);

    int updateByPrimaryKey(OrderImport record);
    /**
     * 获取进口订单各个状态
    * @Title: findCount 
    * @Description: TODO 
    * @param @param codeName
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findCount(int keyWord,User user);
    /**
     * 获取各个状态订单列表
    * @Title: findByStatus 
    * @Description: TODO 
    * @param @param codeName
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderImport>
    * @throws
     */
    List<OrderImport> findByStatus(int keyWord,User user,Page page);
    /**
     * 根据模糊查询条件获取订单列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param order
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderImport>
    * @throws
     */
    List<OrderImport> findByPage(OrderImport order,User user,Page page);
    /**
     * 查询24小时未审核订单
    * @Title: select24 
    * @Description: TODO 
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int select24(User user);

    /**
     * 根据工作单号查询有效数据总数量
     * @param no
     * @return
     */
    int findOrderNoCount(String no);

}
