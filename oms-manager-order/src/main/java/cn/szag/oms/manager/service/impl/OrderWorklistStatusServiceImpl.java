package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderWorklistStatusMapper;
import cn.szag.oms.manager.common.domain.manager.BaseBoxNoMessage;
import cn.szag.oms.manager.common.domain.manager.EShippinInfoDate;
import cn.szag.oms.manager.common.domain.manager.OrderWorklistStatus;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.OrderWorklistStatusService;
@Service("orderWorklistStatusService")
public class OrderWorklistStatusServiceImpl implements OrderWorklistStatusService {
	@Autowired
	private OrderWorklistStatusMapper orderWorklistStatusMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderWorklistStatus record) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderWorklistStatus record) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.insertSelective(record);
	}

	@Override
	public OrderWorklistStatus selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderWorklistStatus record) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderWorklistStatus record) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrderWorklistStatus> select(String orderId) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.select(orderId);
	}

	@Override
	public List<OrderWorklistStatus> findByPage(OrderWorklistStatus ows, User user, Page page) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.findByPage(ows, user, page);
	}

	@Override
	public List<BaseBoxNoMessage> selectOrderId(String orderId, String status,String containerId) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.selectOrderId(orderId, status,containerId);
	}

	@Override
	public List<OrderWorklistStatus> findByClearanceId(String clearanceId) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.findByClearanceId(clearanceId);
	}

	@Override
	public List<String> selectDown(String keyWord, User user) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.selectDown(keyWord, user);
	}

	@Override
	public List<OrderWorklistStatus> findByPageE(OrderWorklistStatus ows, User user, Page page) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.findByPageE(ows, user, page);
	}

	@Override
	public EShippinInfoDate findExportScheduleDate(String orderId, String id) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.findExportScheduleDate(orderId, id);
	}

	@Override
	public List<BaseBoxNoMessage> selectOrderEId(String orderId, String status, String containerId) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.selectOrderEId(orderId, status, containerId);
	}

	@Override
	public List<OrderWorklistStatus> findByPageMove(OrderWorklistStatus ows, User user, Page page) {
		// TODO Auto-generated method stub
		return orderWorklistStatusMapper.findByPageMove(ows, user, page);
	}

}
