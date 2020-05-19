package cn.szag.oms.manager.service.impl;


import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.dao.manager.ModuleMapper;
import cn.szag.oms.manager.common.dao.manager.ModulePermissionMapper;
import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.MenuTreeUtil;
import cn.szag.oms.manager.service.ModuleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理功能
 */
@Service("moduleManagerService")
public class ModuleManagerServiceImpl implements ModuleManagerService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ModulePermissionMapper modulePermissionMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
    	modulePermissionMapper.deleteByModuleId(id);
        return moduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Module record) {
        return moduleMapper.insert(record);
    }

    @Override
    public Module selectByPrimaryKey(String id) {
        return moduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Module record) {
        return moduleMapper.updateByPrimaryKey(record);
    }


    // 删除角色先删除中间表
    @Override
    public int updateByPrimaryKeySelective(Module record) {

        modulePermissionMapper.deleteByModuleId(record.getId());
        return moduleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加菜单
     * @param module
     * @return
     */
    @Override
    public AjaxRes insertSelective(Module module) throws RuntimeException {

        // 查询同级别的菜单名称，不能重复
        String name = module.getName();
        if (name == null){
            return new AjaxRes(Const.FAIL,"资源名称不能为空");
        }
        String type = module.getType();
        if (type == null){
            return new AjaxRes(Const.FAIL,"资源类型不能为空");
        }
        /*List<Module> modules = moduleMapper.findModuleByName(name,type);
        if (modules.size() !=0 ){
            return new AjaxRes(Const.FAIL,"同级资源名称不能重复");
        }*/
        moduleMapper.insertSelective(module);
        return new AjaxRes(Const.SUCCEED,"添加菜单成功");
    }

    // 查询菜单管理功能
    @Override
    public AjaxRes findByPage(Module module, Page page , User user,String parentId) {
        List<Module> list = moduleMapper.findByPage(module, page);
        list = MenuTreeUtil.buildModuleTree(list);
        page.setResults(list);
        if (list.size()==0){
            if (module.getName()!=null){

                return new AjaxRes(Const.FAIL,"输入的资源名称不正确，请重新输入");
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", page);
        map.put("permitBtn", moduleMapper.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
        return new AjaxRes(Const.SUCCEED,"查询菜单成功",map);
    }
	@Override
	public List<Module> findBtn(String userId, String type,String parentId) {
		// TODO Auto-generated method stub
		return moduleMapper.findBtn(userId, type,parentId);
	}

	@Override
	public List<Module> findMenu(String userId) {
		List<Module> list = moduleMapper.findMenu(userId);
		list = MenuTreeUtil.buildModuleTree(list);//转换节点格式
		list = MenuTreeUtil.getDynamicMenus(list);//转换vue路由
		return list;
	}

	@Override
	public List<String> findRoleMus(String roleId) {
		// TODO Auto-generated method stub
		return moduleMapper.findRoleMus(roleId);
	}

	@Override
	public int findByName(String id, String name,String type) {
		// TODO Auto-generated method stub
		return moduleMapper.findByName(id, name,type);
	}

	@Override
	public int findJurisdiction(String moduleId, String userId) {
		// TODO Auto-generated method stub
		return modulePermissionMapper.findJurisdiction(moduleId, userId);
	}

	@Override
	public List<Module> findBtn2(String userId, String type) {
		// TODO Auto-generated method stub
		return moduleMapper.findBtn2(userId, type);
	}

}
