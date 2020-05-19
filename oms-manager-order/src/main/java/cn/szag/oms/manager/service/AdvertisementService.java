package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.Advertisement;
import cn.szag.oms.manager.common.page.Page;

/** 
* @ClassName: BaseAdvertisementService 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月20日 下午3:50:44  
*/
public interface AdvertisementService {
	
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
    List<Advertisement> getAdvList(Advertisement adv,Page page);
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
    String generateNo(Advertisement adv);
}
