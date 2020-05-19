package cn.szag.oms.manager.service.impl;

import cn.szag.oms.manager.common.dao.manager.ModulePermissionMapper;
import cn.szag.oms.manager.common.dao.manager.RoleMapper;
import cn.szag.oms.manager.common.dao.manager.RolememberMapper;
import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolememberMapper rolememberMapper;

    @Autowired
    private ModulePermissionMapper modulePermissionMapper;


    public int deleteByPrimaryKey(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    public Role selectByPrimaryKey(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    // 添加角色先添加菜单模块
    public int insertSelective(Role record,String[] moduleIds) {

        if (moduleIds !=null && moduleIds.length>0) {
            for (String moduleId : moduleIds) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", UuidUtil.get32UUID());
                map.put("targetId", record.getId());
                map.put("moduleId", moduleId);
                modulePermissionMapper.addModuleIds(map);
            }
        }

        return roleMapper.insertSelective(record);
    }

    /**
     *  分页查询
     * @param role
     * @param page
     * @return
     */
    public Page selectList(Role role, Page page) {
        List<Role> list = roleMapper.selectList(role,page);
        page.setResults(list);
        return page;
    }


    /**
     * 删除角色，逻辑删除，并需要删除中间表
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Role record) {
        rolememberMapper.deleteByRoleId(record.getId());
        modulePermissionMapper.deleteByRoleId(record.getId());
        return roleMapper.updateByPrimaryKeySelective(record);
    }


    // 修改用户，需要先删除中间表，然后重新添加
    @Override
    public void updateRole(Role role, String[] moduleIds) {
        // 先删除中间表
    	modulePermissionMapper.deleteByRoleId(role.getId());
        // 添加用户表和中间表
        if (moduleIds !=null &&moduleIds.length>0) {
            String roleId = role.getId();
            for (String moduleId : moduleIds) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", UuidUtil.get32UUID());
                map.put("targetId", role.getId());
                map.put("moduleId", moduleId);
                modulePermissionMapper.addModuleIds(map);
            }
        }
        roleMapper.updateByPrimaryKeySelective(role);
    }

	@Override
	public List<Role> selectRoleList(String userId) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleList(userId);
	}

	@Override
	public int findByName(String id, String name) {
		// TODO Auto-generated method stub
		return roleMapper.findByName(id, name);
	}
}
