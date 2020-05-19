package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.PictureAttachmentMapper;
import cn.szag.oms.manager.common.domain.manager.PictureAttachment;
import cn.szag.oms.manager.service.PictureAttachmentService;

@Service("pictureAttachmentService")
public class PictureAttachmentServiceImpl implements PictureAttachmentService {
	@Autowired
	private PictureAttachmentMapper pictureAttachmentMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PictureAttachment record) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.insert(record);
	}

	@Override
	public int insertSelective(PictureAttachment record) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.insertSelective(record);
	}

	@Override
	public PictureAttachment selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PictureAttachment record) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PictureAttachment record) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PictureAttachment> select(String containerId) {
		// TODO Auto-generated method stub
		return pictureAttachmentMapper.select( containerId);
	}

}
