package cn.szag.oms.manager.service.impl;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.dao.manager.AutomaticOrderMapper;
import cn.szag.oms.manager.common.dao.manager.ModuleMapper;
import cn.szag.oms.manager.common.dao.manager.SequenceDao;
import cn.szag.oms.manager.common.dao.manager.UserMapper;
import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.StringUtils;
import cn.szag.oms.manager.common.utils.sequence.SequenceGenerator;
import cn.szag.oms.manager.service.AutomaticOrderService;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自助下单功能
 */
@Service("automaticOrderService")
public class AutomaticOrderServiceImpl implements AutomaticOrderService {

	@Autowired
	private AutomaticOrderMapper automaticOrderMapper;

	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
    private ModuleMapper moduleMapper;

	@Override
	public int deleteByPrimaryKey(String id) {

		return automaticOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderImport record) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.insert(record);
	}

	@Override
	public OrderImport selectByPrimaryKey(String containerId, String id) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.selectByPrimaryKey(containerId, id);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderImport record) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderImport record) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.updateByPrimaryKey(record);
	}

	/**
	 * 删除订单，逻辑删除，
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(OrderImport record) {
		// TODO 调用业务管理中心 API，同步到 B 库。
		return automaticOrderMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 把订单的信息写入到数据库，并且调用业务管理的api，同步到B库
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(OrderImport record) {
		return automaticOrderMapper.insertSelective(record);
	}

	/**
	 * 查询用户的订单号是否存在
	 * 
	 * @param orderNo
	 * @return
	 */
	@Override
	public OrderImport selectByOrderNO(String orderNo) {
		OrderImport orderImport = automaticOrderMapper.selectByOrderNO(orderNo);
		return orderImport;
	}

	/**
	 * 根据user的权限展示订单
	 * 
	 * @param user
	 * @param automaticOrder
	 * @param page
	 * @return
	 */
	@Override
	public AjaxRes selectList(User user, AutomaticOrder automaticOrder, Page page,String parentId) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<OrderImport> list = automaticOrderMapper.selectList(user, automaticOrder, page);
			page.setResults(list);
			map.put("list", page);
			map.put("permitBtn", moduleMapper.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			map.put("permitBtn2",moduleMapper.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
		} catch (Exception e) {
			e.printStackTrace();
			new AjaxRes(Const.FAIL, "订单查询失败");
		}
		return new AjaxRes(Const.SUCCEED, "订单查询成功", map);
	}

	// 查询通知时间大于24小时未审核的订单
	@Override
	public Page findlistBy24(User user, AutomaticOrder automaticOrder, Page page) {
		List<OrderImport> list = automaticOrderMapper.findlistBy24(user, automaticOrder, page);
		page.setResults(list);
		return page;
	}
	@Override
	public AjaxRes orderTrackingList(User user, AutomaticOrder automaticOrder, Page page) {

		try {
			List<OrderImport> list = automaticOrderMapper.orderTrackingList(user, automaticOrder, page);
			page.setResults(list);
		} catch (Exception e) {
			e.printStackTrace();
			new AjaxRes(Const.FAIL, "订单查询失败");
		}
		return new AjaxRes(Const.SUCCEED, "订单查询成功", page);
	}

	@Override
	public AjaxRes standardImportList(User user, AutomaticOrder automaticOrder, Page page) {
		try {
			List<Attention> list = automaticOrderMapper.standardImportList(user, automaticOrder, page);
			JSONObject json = JSONObject.fromObject(automaticOrder);
	        System.out.println(json.toString());
			System.out.println("".equals(automaticOrder.getBoxNo()));
			page.setResults(list);
		} catch (Exception e) {
			e.printStackTrace();
			new AjaxRes(Const.FAIL, "订单查询失败");
		}
		return new AjaxRes(Const.SUCCEED, "订单查询成功", page);
	}

	@Override
	public String generateOrderNo(AutomaticOrder orderImport) {
		// 锟斤拷锟斤拷锟斤拷询锟斤拷锟斤拷
		AutomaticOrder orderImport1 = new AutomaticOrder();
		orderImport1.setOrderNo(orderImport.getOrderNo());

		// 锟斤拷始锟斤拷锟斤拷锟斤拷id
		if (StringUtils.isBlank(orderImport1.getOrderNo())) {// 锟叫讹拷锟斤拷水锟斤拷锟角凤拷为锟斤拷
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "I", "");
			orderImport1.setOrderNo(serialNum);
			this.generateOrderNo(orderImport1);
		}
		// 锟斤拷询锟斤拷锟斤拷水锟斤拷锟角凤拷锟窖达拷锟节讹拷锟斤拷?
		OrderImport result = automaticOrderMapper.findOrderNo(orderImport.getOrderNo());
		if (result != null) {
			// 锟斤拷锟斤拷锟剿拷锟斤拷汛锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷傻锟斤拷锟�
			// 锟斤拷锟斤拷锟铰碉拷锟斤拷水 ?
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "I", "");

			orderImport1.setOrderNo(serialNum);

			this.generateOrderNo(orderImport1);
		}
		return orderImport1.getOrderNo();
	}
	
	@Override
	public OrderImport findcustomerId(String companyId,String origin,String inport) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.findcustomerId(companyId,origin,inport);
	}

	@Override
	public int findByExtractOrderNum(String extractOrderNum, String id) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.findByExtractOrderNum(extractOrderNum, id);
	}

	@Override
	public int selectCount(User user, AutomaticOrder automaticOrder) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.selectCount(user, automaticOrder);
	}

	@Override
	public List<String> selectAll(AutomaticOrder automaticOrder, User user) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.selectAll(automaticOrder, user);
	}

	@Override
	public List<OrderImport> selectAll2(AutomaticOrder automaticOrder, User user) {
		// TODO Auto-generated method stub
		return automaticOrderMapper.selectAll2(automaticOrder, user);
	}

}
