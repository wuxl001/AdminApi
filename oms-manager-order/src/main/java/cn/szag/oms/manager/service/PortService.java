package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.page.Page;



/**
 * 港口信息 
* @ClassName: PortService 
* @Description: TODO
* @author dengyanghao
* @date 2019�?9�?18�? 上午10:21:23  
*/
public interface PortService {
	int deleteByPrimaryKey(String id);

    int insert(Port record);

    int insertSelective(Port record);

    Port selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Port record);

    int updateByPrimaryKeyWithBLOBs(Port record);

    int updateByPrimaryKey(Port record);
    /**
     * 分页查询港口信息
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param port
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<Port>
    * @throws
     */
    List<Port> findByPage(Port port,Page page);

}
