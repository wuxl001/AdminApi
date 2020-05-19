package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderImportMapper;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.OrderImportService;
@Service("orderImportService")
public class OrderImportServiceImpl implements OrderImportService {
	@Autowired
	private OrderImportMapper orderImportMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderImportMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderImport record) {
		// TODO Auto-generated method stub
		return orderImportMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderImport record) {
		// TODO Auto-generated method stub
		return orderImportMapper.insertSelective(record);
	}

	@Override
	public OrderImport selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderImportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderImport record) {
		// TODO Auto-generated method stub
		return orderImportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderImport record) {
		// TODO Auto-generated method stub
		return orderImportMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderImport record) {
		// TODO Auto-generated method stub
		return orderImportMapper.updateByPrimaryKey(record);
	}

	@Override
	public int findCount(int keyWord, User user) {
		// TODO Auto-generated method stub
		return orderImportMapper.findCount(keyWord, user);
	}

	@Override
	public List<OrderImport> findByStatus(int keyWord, User user, Page page) {
		// TODO Auto-generated method stub
		return orderImportMapper.findByStatus(keyWord, user, page);
	}

	@Override
	public List<OrderImport> findByPage(OrderImport order, User user, Page page) {
		// TODO Auto-generated method stub
		return orderImportMapper.findByPage(order, user, page);
	}

	@Override
	public int select24(User user) {
		// TODO Auto-generated method stub
		return orderImportMapper.select24(user);
	}

	@Override
	public int findOrderNoCount(String no) {
		return orderImportMapper.findOrderNoCount(no);
	}

}
