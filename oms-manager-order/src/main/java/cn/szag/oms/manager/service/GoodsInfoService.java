package cn.szag.oms.manager.service;


import java.util.List;

import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.page.Page;



/** 
* @ClassName: GoodsInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019�?9�?18�? 上午10:48:55  
*/
public interface GoodsInfoService {
	
	int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
    /**
     * 根据输入条件查询出货物列表信�?
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param info
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return GoodsInfo
    * @throws
     */
    List<GoodsInfo> findByPage(GoodsInfo info,Page page);
}
