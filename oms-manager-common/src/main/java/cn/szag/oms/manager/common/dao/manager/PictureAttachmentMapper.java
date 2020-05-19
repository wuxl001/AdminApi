package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.PictureAttachment;

/**
 * 附件
* @ClassName: PictureAttachmentMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:29:38
 */
public interface PictureAttachmentMapper {
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
    List<PictureAttachment> select(@Param("containerId")String containerId);
}