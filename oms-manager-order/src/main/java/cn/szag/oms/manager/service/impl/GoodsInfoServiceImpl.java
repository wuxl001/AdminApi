package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.GoodsInfoMapper;
import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.GoodsInfoService;

@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService{
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.insertSelective(record);
	}

	@Override
	public GoodsInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<GoodsInfo>  findByPage(GoodsInfo info, Page page) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.findByPage(info, page);
	}

}
