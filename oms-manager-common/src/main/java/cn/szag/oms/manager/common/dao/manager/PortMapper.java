package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.page.Page;

/**
 * 港口
* @ClassName: PortMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:29:32
 */
public interface PortMapper {
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
    List<Port> findByPage(@Param("port")Port port,@Param("page")Page page);
}