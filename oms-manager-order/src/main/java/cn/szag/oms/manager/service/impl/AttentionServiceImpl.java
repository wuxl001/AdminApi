package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.dao.manager.AttentionMapper;
import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.service.AttentionService;

@Service("attentionService")
public class AttentionServiceImpl implements AttentionService {
	@Autowired
	private AttentionMapper attentionMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return attentionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Attention record) {
		// TODO Auto-generated method stub
		return attentionMapper.insert(record);
	}

	@Override
	public int insertSelective(Attention record) {
		// TODO Auto-generated method stub
		return attentionMapper.insertSelective(record);
	}

	@Override
	public Attention selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return attentionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Attention record) {
		// TODO Auto-generated method stub
		return attentionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Attention record) {
		// TODO Auto-generated method stub
		return attentionMapper.updateByPrimaryKey(record);
	}

	@Override
	public int followNumber(String isAttention,User user) {
		// TODO Auto-generated method stub
		return attentionMapper.followNumber(isAttention,user);
	}
	@Override
	public AjaxRes followList(User user, String condition, Page page) {
		try {
			List<Attention> list = attentionMapper.followList(user, condition, page);
			// page.setPageSize(10000);
			page.setResults(list);
		} catch (Exception e) {
			e.printStackTrace();
			new AjaxRes(Const.FAIL, "关注列表查询失败");
		}
		return new AjaxRes(Const.SUCCEED, "关注列表订单查询成功", page);
	}

	@Override
	public Attention findByUserId(Attention record) {
		// TODO Auto-generated method stub
		return attentionMapper.findByUserId(record);
	}
}
