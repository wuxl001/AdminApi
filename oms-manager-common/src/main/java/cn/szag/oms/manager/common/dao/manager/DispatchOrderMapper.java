package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.DispatchOrder;

/**
 * 调度
* @ClassName: DispatchOrderMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午7:39:33
 */

public interface DispatchOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(DispatchOrder record);

    int insertSelective(DispatchOrder record);

    DispatchOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DispatchOrder record);

    int updateByPrimaryKey(DispatchOrder record);
    /**
     * 根据运输单id获取调度集合
    * @Title: select 
    * @Description: TODO 
    * @param @param transferID
    * @param @return
    * @author dengyanghao
    * @return List<DispatchOrder>
    * @throws
     */
    List<DispatchOrder> select(String transferID);
}