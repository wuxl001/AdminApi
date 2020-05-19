package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.common.page.Page;
/**
 * 业务消息中心
* @ClassName: ManagerNoticeMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午6:00:32
 */
public interface ManagerNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ManagerNotice record);

    int insertSelective(ManagerNotice record);

    ManagerNotice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ManagerNotice record);

    int updateByPrimaryKey(ManagerNotice record);
    /**
     * 根据订单Id以及集装箱Id获取业务消息
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return List<ManagerNotice>
    * @throws
     */
    List<ManagerNotice> select(@Param("orderId")String orderId,@Param("containerId")String containerId,@Param("userId")String userId,@Param("page")Page page);
   
    /**
     * 根据订单id查询补料说明历史
     * @param orderId
     * @return
     */
    List<ManagerNotice> selectInform(@Param("orderId")String orderId);
    /**
     * 用户未读消息统计
    * @Title: findByUserId 
    * @Description: TODO 
    * @param @param userId
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findByUserId(String userId);
    /**
     * 业务消息列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @return
    * @author dengyanghao
    * @return List<ManagerNotice>
    * @throws
     */
    List<ManagerNotice> findByPage(@Param("m")ManagerNotice managerNotice,@Param("page")Page page);
    /**
     * 批量修改用户消息为已读
    * @Title: updateStatus 
    * @Description: TODO 
    * @param @param userId
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int updateStatus(@Param("userId")String userId);
}