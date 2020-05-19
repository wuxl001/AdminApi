package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.AdvertisementMapper;
import cn.szag.oms.manager.common.dao.manager.SequenceDao;
import cn.szag.oms.manager.common.domain.manager.Advertisement;
import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.StringUtils;
import cn.szag.oms.manager.common.utils.sequence.SequenceGenerator;
import cn.szag.oms.manager.service.AdvertisementService;
@Service("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
	@Autowired
	private AdvertisementMapper  advertisementMapper;
	@Autowired
	private SequenceDao sequenceDao;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return advertisementMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Advertisement record) {
		// TODO Auto-generated method stub
		return advertisementMapper.insert(record);
	}

	@Override
	public int insertSelective(Advertisement record) {
		// TODO Auto-generated method stub
		return advertisementMapper.insertSelective(record);
	}

	@Override
	public Advertisement selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return advertisementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Advertisement record) {
		// TODO Auto-generated method stub
		return advertisementMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Advertisement record) {
		// TODO Auto-generated method stub
		return advertisementMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Advertisement record) {
		// TODO Auto-generated method stub
		return advertisementMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Advertisement> getAdvList(Advertisement adv, Page page) {
		// TODO Auto-generated method stub
		return advertisementMapper.getAdvList(adv, page);
	}

	@Override
	public String generateNo(Advertisement adv) {
		// 锟斤拷锟斤拷锟斤拷询锟斤拷锟斤拷
		Advertisement adv1 = new Advertisement();
		adv1.setNo(adv.getNo());
		// 锟斤拷始锟斤拷锟斤拷锟斤拷id
		if (StringUtils.isBlank(adv1.getNo())) {// 锟叫讹拷锟斤拷水锟斤拷锟角凤拷为锟斤拷
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "G", "");
			adv1.setNo(serialNum);
			this.generateNo(adv1);
		}
		// 锟斤拷询锟斤拷锟斤拷水锟斤拷锟角凤拷锟窖达拷锟节讹拷锟斤拷?
		Advertisement result = advertisementMapper.findNo(adv.getNo());
		if (result != null) {
			// 锟斤拷锟斤拷锟剿拷锟斤拷汛锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷傻锟斤拷锟�
			// 锟斤拷锟斤拷锟铰碉拷锟斤拷水 ?
			String serialNum = SequenceGenerator.getInstance().getNextKey(sequenceDao, "G", "");
			adv1.setNo(serialNum);
			this.generateNo(adv1);
		}
		return adv1.getNo();
	}

}
