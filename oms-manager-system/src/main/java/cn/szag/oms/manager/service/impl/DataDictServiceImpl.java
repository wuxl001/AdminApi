package cn.szag.oms.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.DataDictMapper;
import cn.szag.oms.manager.common.domain.manager.DataDict;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.DataDictService;

@Service("dataDictService")
public class DataDictServiceImpl implements DataDictService {
	
	@Autowired
	private DataDictMapper dataDictMapper;
	
	public List<DataDict> findDataDictByPage(Page page, DataDict record) {
		List<DataDict> list = new ArrayList<DataDict>();
		try {
			record.setDelFlag("0");
			record.setParentId("0");
			list = dataDictMapper.findByPage(record,page);
			Items(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public void insert(DataDict dataDict) {
		dataDictMapper.insertSelective(dataDict);
	}
	
	public List<DataDict> findByParentId(String parentId) {
		return dataDictMapper.findByParentId(parentId);
	}
	
	public synchronized void updateDataDict(DataDict dataDict) {
		dataDictMapper.updateByPrimaryKeySelective(dataDict);
	}
	
	public void del(DataDict dataDict) {
		dataDictMapper.updateByPrimaryKeySelective(dataDict);
	}
	
	public DataDict findDataDictById(String id) {
		return dataDictMapper.findDataDictById(id);
	}
	
	public void delByParentId(DataDict dataDict) {
		dataDictMapper.delByParentId(dataDict);
	}

	// 根据 name 查询（目前用于：套餐创建 - 新增 - 下拉框）
	public List<DataDict> selectByName(String name) {
		return dataDictMapper.selectByName(name);
	}
	
	public void Items(List<DataDict> list){
		for (DataDict m : list) {
			if(m.getChildren()==null){
				DataDict ml= new DataDict();
				ml.setParentId(m.getId());
				List<DataDict> children = dataDictMapper.findByPage(ml,null);
				m.setChildren(children);
				Items(children);
			}
		}
	}

	// 查询 delFlag 为 0 的记录数
	public int countByDelFlag() {
		return dataDictMapper.countByDelFlag();
	}
	
	// 判断新增的字典名称是否重复
	public List<DataDict> selectByNameAndParentId(DataDict data) {
		return dataDictMapper.selectByNameAndParentId(data);
	}
	
}