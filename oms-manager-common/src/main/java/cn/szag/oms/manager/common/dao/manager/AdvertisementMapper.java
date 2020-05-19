package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Advertisement;
import cn.szag.oms.manager.common.page.Page;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(String id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKeyWithBLOBs(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
    /**
     * 根据输入条件查询相应列表
    * @Title: getAdvList 
    * @Description: TODO 
    * @param @param adv
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<Advertisement>
    * @throws
     */
    List<Advertisement> getAdvList(@Param("adv")Advertisement adv,Page page);
    /**
     * 根据编号查询消息
    * @Title: findNo 
    * @Description: TODO 
    * @param @param no
    * @param @return
    * @author dengyanghao
    * @return Advertisement
    * @throws
     */
    Advertisement findNo(String no);
}