package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

/**
 * 关注
* @ClassName: AttentionMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:49:20
 */
public interface AttentionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);
    
    int followNumber(@Param("isAttention") String isAttention,@Param("user") User user);
    // 关注列表页
 	List<Attention> followList(@Param("user") User user, @Param("condition") String condition,
 			@Param("page") Page page);
 	
 	Attention findByUserId(Attention record);
}