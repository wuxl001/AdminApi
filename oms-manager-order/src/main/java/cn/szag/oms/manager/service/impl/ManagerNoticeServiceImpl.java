package cn.szag.oms.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.szag.oms.manager.common.dao.manager.AutomaticOrderMapper;
import cn.szag.oms.manager.common.dao.manager.UserMapper;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.EmailDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.ManagerNoticeMapper;
import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.service.ManagerNoticeService;
@Service("managerNoticeService")
public class ManagerNoticeServiceImpl implements ManagerNoticeService {
	@Autowired
	private ManagerNoticeMapper managerNoticeMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AutomaticOrderMapper automaticOrderMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ManagerNotice record) {
		// 获得通知人的id
		User user = userMapper.selectByPrimaryKey(record.getReceiverId());
		OrderImport orderImport = automaticOrderMapper.selectByPrimaryKey(null,record.getOrderId());
		String titleType = "";
		if (record.getTitleType() == 0){
			titleType="进口";
		}
		if (record.getTitleType() == 1){
			titleType="出口";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(orderImport.getAgentAdviceDate());
		String str =user.getUsername()+"您好，你有OMS"+orderImport.getTransportWay()+""+titleType+"的集装箱号为"+orderImport.getBoxNo()+"的消息提醒";
		String stc =orderImport.getTransportWay()+"消息提醒"+dateString+" 您的订单号为"+orderImport.getId()+"集装箱"+orderImport.getWorklistNo()+"单证资料未齐，原因："+record.getContent()+"";
		EmailDemo emailDemo = new EmailDemo();
		emailDemo.send(user.getEmail(),str,stc);
		return managerNoticeMapper.insert(record);
	}

	@Override
	public int insertSelective(ManagerNotice record) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.insertSelective(record);
	}

	@Override
	public ManagerNotice selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ManagerNotice record) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ManagerNotice record) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<ManagerNotice> select(String orderId,String containerId,String userId,Page page) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.select(orderId, containerId,userId, page);
	}
	@Override
	public int findByUserId(String userId) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.findByUserId(userId);
	}

	@Override
	public List<ManagerNotice> findByPage(ManagerNotice managerNotice,Page page) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.findByPage(managerNotice,page);
	}

	@Override
	public int updateStatus(String userId) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.updateStatus(userId);
	}

	@Override
	public List<ManagerNotice> selectInform(String orderId) {
		// TODO Auto-generated method stub
		return managerNoticeMapper.selectInform(orderId);
	}

}
