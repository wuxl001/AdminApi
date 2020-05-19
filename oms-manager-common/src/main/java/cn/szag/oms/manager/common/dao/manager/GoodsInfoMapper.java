package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.page.Page;

/**
 * 货物信息
* @ClassName: GoodsInfoMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:28:32
 */

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
    /**
     * 根据输入条件查询出货物列表信息
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param info
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return GoodsInfo
    * @throws
     */
    List<GoodsInfo>  findByPage(@Param("info")GoodsInfo info,@Param("page")Page page);
}