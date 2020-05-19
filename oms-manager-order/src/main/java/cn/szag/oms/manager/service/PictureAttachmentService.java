package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.PictureAttachment;





/** 
 * 附件信息
* @ClassName: PictureAttachmentService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午7:41:29  
*/
public interface PictureAttachmentService {
	
	int deleteByPrimaryKey(String id);

    int insert(PictureAttachment record);

    int insertSelective(PictureAttachment record);

    PictureAttachment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PictureAttachment record);


    int updateByPrimaryKey(PictureAttachment record);
    /**
     * 根据柜号获取附件集合
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return List<PictureAttachment>
    * @throws
     */
    List<PictureAttachment> select(String containerId);
}
