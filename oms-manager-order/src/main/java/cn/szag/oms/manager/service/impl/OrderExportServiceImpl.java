package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderExportMapper;
import cn.szag.oms.manager.common.dao.manager.SequenceDao;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.StringUtils;
import cn.szag.oms.manager.common.utils.sequence.SequenceGenerator;
import cn.szag.oms.manager.service.OrderExportService;
@Service("orderExportService")
public class OrderExportServiceImpl implements OrderExportService{
	@Autowired
	private OrderExportMapper orderExportMapper;
	@Autowired
	private SequenceDao sequenceDao;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderExportMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderExport record) {
		// TODO Auto-generated method stub
		return orderExportMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderExport record) {
		// TODO Auto-generated method stub
		return orderExportMapper.insertSelective(record);
	}

	@Override
	public OrderExport selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderExportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderExport record) {
		// TODO Auto-generated method stub
		return orderExportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderExport record) {
		// TODO Auto-generated method stub
		return orderExportMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderExport record) {
		// TODO Auto-generated method stub
		return orderExportMapper.updateByPrimaryKey(record);
	}

	@Override
	public int findCount(int keyWord, User user) {
		// TODO Auto-generated method stub
		return orderExportMapper.findCount(keyWord, user);
	}

	@Override
	public int findByBookingNo(String bookingNo,String orderId) {
		// TODO Auto-generated method stub
		return orderExportMapper.findByBookingNo(bookingNo,orderId);
	}

	@Override
	public List<OrderExport> findByPage(User user, OrderExport orderExport, Page page) {
		// TODO Auto-generated method stub
		return orderExportMapper.findByPage(user, orderExport, page);
	}

	@Override
	public String generateOrderNo(OrderExport orderExport) {
		OrderExport orderExport1 = new OrderExport();
		orderExport1.setOrderNo(orderExport.getOrderNo());
		if (StringUtils.isBlank(orderExport1.getOrderNo())) {// 锟叫讹拷锟斤拷水锟斤拷锟角凤拷为锟斤拷
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "E", "");
			orderExport1.setOrderNo(serialNum);
			this.generateOrderNo(orderExport1);
		}
		OrderExport result = orderExportMapper.findOrderNo(orderExport.getOrderNo());
		if (result != null) {
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "E", "");
			orderExport1.setOrderNo(serialNum);

			this.generateOrderNo(orderExport1);
		}
		return orderExport1.getOrderNo();
	}

	@Override
	public int findOrderCount(Integer keyWord,User user) {
		// TODO Auto-generated method stub
		return orderExportMapper.findOrderCount(keyWord,user);
	}

	@Override
	public OrderExport find(String id) {
		// TODO Auto-generated method stub
		return orderExportMapper.find(id);
	}

	@Override
	public int findOrder(Integer keyWord, User user) {
		// TODO Auto-generated method stub
		return orderExportMapper.findOrder(keyWord, user);
	}

	@Override
	public int findOrderNoCount(String no) {
		return orderExportMapper.findOrderNoCount(no);
	}

}
