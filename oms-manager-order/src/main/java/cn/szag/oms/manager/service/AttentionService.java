package cn.szag.oms.manager.service;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

/** 
* @ClassName: AttentionService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 上午11:49:10  
*/
public interface AttentionService {
	 int deleteByPrimaryKey(String id);

	 int insert(Attention record);

	 int insertSelective(Attention record);

	 Attention selectByPrimaryKey(String id);

	 int updateByPrimaryKeySelective(Attention record);

	 int updateByPrimaryKey(Attention record);
	 
	 int followNumber(String isAttention , User user);
	 
	 AjaxRes followList(User user, String condition, Page page);
	 
	 Attention findByUserId(Attention record);
}
