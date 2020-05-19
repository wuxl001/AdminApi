package cn.szag.oms.manager.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.domain.manager.Department;
import cn.szag.oms.manager.common.domain.manager.DictionaryManaged;
import cn.szag.oms.manager.common.domain.manager.Resources;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;





public class MenuTreeUtil {
	/**
	 * 建立树菜单
	 * 
	 * @param menusDB
	 *            菜单集合（不是树）
	 * @return 有样式的树的html字符串
	 */
	public static String buildTreeHtml(List<Resources> menusDB, int inx) {
		StringBuffer html = new StringBuffer();
		int index = inx;
		// String parentId = "";

		for (int i = 0; i < menusDB.size(); i++) {
			Resources node = menusDB.get(i);

			if (!"0".equals(node.getParentId())) {
				// parentId = node.getId();
				continue;
			}
			// if (parentId.equals(node.getParentId())) {
			boolean childrenHas = false;
			List<Resources> children = getChildren(menusDB, node);
			if (!children.isEmpty())
				childrenHas = true;
			if (index == 0) {
				html.append("\r\n<li id='menu" + node.getId() + "' class='active' >");
			} else {
				html.append("\r\n<li id='menu" + node.getId() + "' >");
			}
			html.append("\r\n<a ");
			html.append(" href='#maincontent' style='cursor:pointer;'  onclick=\"openMenu('" + node.getType()
					+ "','menu" + node.getId() + "','menu0','" + node.getName() + "','"
					+ (StringUtils.isNotBlank(node.getLinkUrl()) ? node.getLinkUrl().trim() : "noset") + "')\" ");
			if (childrenHas)
				html.append(" target='mainFrame' class='dropdown-toggle' ");
			html.append(" >");
			if (StringUtils.isNotEmpty(node.getIcon()))
				html.append("\r\n<i class= " + node.getIcon() + " ></i>");
			html.append("\r\n<span class='menu-text'>" + node.getName() + "</span>");
			if (childrenHas)
				html.append("<b class='arrow icon-angle-down'></b>");
			html.append("</a>");
			build(menusDB, node, html);
			html.append("</li>");

			index++;
			// }
		}
		return html.toString();
	}

	private static void build(List<Resources> menusDB, Resources node, StringBuffer html) {
		List<Resources> children = getChildren(menusDB, node);
		if (!children.isEmpty()) {
			html.append("\r\n<ul class='submenu'>");
			for (Resources child : children) {
				boolean childrenHas = false;
				List<Resources> children2 = getChildren(menusDB, child);
				if (!children2.isEmpty())
					childrenHas = true;
				html.append("\r\n<li id='menu" + child.getId() + "' >");
				html.append("\r\n<a ");
				html.append(" href='#maincontent' style='cursor:pointer;' onclick=\"openMenu('" + child.getType()
						+ "','menu" + child.getId() + "','menu" + child.getParentId() + "','" + child.getName() + "','"
						+ (StringUtils.isNotBlank(child.getLinkUrl()) ? child.getLinkUrl().trim() : "noset") + "')\" ");
				if (childrenHas)
					html.append(" target='mainFrame' class='dropdown-toggle' ");
				html.append(" >");
				if (!"0".equals(child.getParentId()))
					html.append("\r\n<i class='icon-double-angle-right' ></i>");
				html.append("\r\n<span class='menu-text'>");
				if (StringUtils.isNotEmpty(child.getIcon())) {
					html.append("\r\n<i class= " + child.getIcon() + " ></i>&nbsp;&nbsp;" + child.getName());
				} else {
					html.append(child.getName());
				}
				html.append("</span>");
				if (childrenHas)
					html.append("<b class='arrow icon-angle-down'></b>");
				html.append("</a>");
				build(menusDB, child, html);
				html.append("</li>");
			}
			html.append("\r\n</ul>");
		}
	}

	private static List<Resources> getChildren(List<Resources> menusDB, Resources node) {
		List<Resources> children = new ArrayList<Resources>();
		String id = node.getId();
		for (Resources child : menusDB) {
			if (id.equals(child.getParentId())) {
				children.add(child);
			}
		}
		return children;
	}
	private static List<Module> getModuleChildren(List<Module> menusDB, Module module) {
		List<Module> children = new ArrayList<Module>();
		String id = module.getId();
		for (Module child : menusDB) {
			if (id.equals(child.getParentid())) {
				List<Module> moduleList = getModuleChildren(menusDB, child);
				child.setChildren(moduleList);
				children.add(child);
			}
		}
		return children;
	}
	private static List<DictionaryManaged> getChildren4DataDict(List<DictionaryManaged> menusDB, DictionaryManaged node) {
		List<DictionaryManaged> children = new ArrayList<DictionaryManaged>();
		String id = node.getId();
		for (DictionaryManaged child : menusDB) {
			if (id.equals(child.getParentid())) {
				children.add(child);
			}
		}
		return children;
	}

	/**
	 * 建立树菜单
	 * 
	 * @param menusDB
	 *            菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
	public static List<Resources> buildTree(List<Resources> menusDB) {
		List<Resources> res = new ArrayList<Resources>();
		for (Resources node : menusDB) {
			if ("0".equals(node.getParentId())) {
				List<Resources> children = getChildren(menusDB, node);
				node.setNodes(children);
				build(menusDB, node, res);
				res.add(node);
			}
		}
		return res;
	}
	/**
	 * 建立树菜单
	 * 
	 * @param menusDB
	 *            菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
	public static List<Module> buildModuleTree(List<Module> menusDB) {
		List<Module> res = new ArrayList<Module>();
		for (Module children : menusDB) {
			if ("0".equals(children.getParentid())) {
				List<Module> module = getModuleChildren(menusDB, children);
				children.setChildren(module);
				build(menusDB, children, res);
				res.add(children);
			}
		}
		return res;
	}
	
	

	
	
	//——————————————————————————————————————————————————

	/**
	 * 建立树字典
	 * 
	 * @param menusDB
	 *            菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
	public static List<DictionaryManaged> buildTree4DataDict(List<DictionaryManaged> menusDB) {
		List<DictionaryManaged> res = new ArrayList<DictionaryManaged>();
		for (DictionaryManaged node : menusDB) {
			if ("0".equals(node.getParentid())) {
				List<DictionaryManaged> children = getChildren4DataDict(menusDB, node);
				node.setNodes(children);
				build4DataDict(menusDB, node, res);
				res.add(node);
			}
		}
		return res;
	}

	private static void build(List<Resources> menusDB, Resources node, List<Resources> res) {
		List<Resources> children = getChildren(menusDB, node);
		if (!children.isEmpty()) {
			for (Resources child : children) {
				List<Resources> children2 = getChildren(menusDB, child);
				child.setNodes(children2);
				build(menusDB, child, res);
			}
		}
	}
	private static void build(List<Module> menusDB, Module node, List<Module> res) {
		List<Module> children = getModuleChildren(menusDB, node);
		if (!children.isEmpty()) {
			for (Module child : children) {
				List<Module> children2 = getModuleChildren(menusDB, child);
				child.setChildren(children2);
				build(menusDB, child, res);
			}
		}
	}
	private static void build4DataDict(List<DictionaryManaged> menusDB, DictionaryManaged node, List<DictionaryManaged> res) {
		List<DictionaryManaged> children = getChildren4DataDict(menusDB, node);
		if (!children.isEmpty()) {
			for (DictionaryManaged child : children) {
				List<DictionaryManaged> children2 = getChildren4DataDict(menusDB, child);
				child.setNodes(children2);
				build4DataDict(menusDB, child, res);
			}
		}
	}

	/**
	 * 顶部的菜单
	 * 
	 * @param menusDB
	 */
	public static void buildHeader(List<Resources> menusDB) {
		Resources resources;
		for (int i = 0; i < menusDB.size(); i++) {
			resources = menusDB.get(i);
			if (!"0".equals(resources.getParentId())) {
				menusDB.remove(i);
				i--;
			}
		}
	}
	/**
	 * 建立树菜单
	 * 
	 * @param menusDB
	 *            菜单集合（不是树）
	 * @return 有样式的树的菜单集合
	 */
	public static List buildTree4Class(List menusDB, String cls) {
		List res = new ArrayList();
		for (Object node : menusDB) {
			if("Department".equals(cls)) {
				List<Department> treeDB = (List<Department>) menusDB;

				Department nds = ((Department) node);
				if ("0".equals(nds.getParentid())) {
					List<Department> children = getChildren4Class(treeDB, nds,cls);
					nds.setNodes(children);
					build4Class(treeDB, nds, res,cls);
					res.add(nds);
				}
			}else if("module".equals(cls)) {
				List<Module> treeDB = (List<Module>) menusDB;
				Module nds = ((Module) node);
				if ("0".equals(nds.getParentid())) {
					List<Module> children = getChildren4Class(treeDB, nds,cls);
					nds.setChildren(children);
					build4Class(treeDB, nds, res,cls);
					res.add(nds);
				}
			}
		}
		return res;
	}
	private static void build4Class(List menusDB, Object node, List res,String cls) {
		List children = getChildren4Class(menusDB, node,cls);
		if (!children.isEmpty()) {
			if("Department".equals(cls)){
				for (Object child : children) {
					List children2 = getChildren4Class(menusDB, child,cls);
					((Department)child).setNodes(children2);
					build4Class(menusDB, child, res,cls);
				}
			}
			
		}
	}
	private static List getChildren4Class(List menusDB, Object node,String cls) {
		List children = new ArrayList();
		if("Department".equals(cls)){
			String id = ((Department)node).getId();
			for (Object child : menusDB) {
				if (id.equals(((Department)child).getParentid())) {
					children.add(child);
				}
			}
		}
		return children;
	}
	/**
	 * 生成VUE前端所需路由
	* @Title: getDynamicMenus 
	* @Description: TODO 
	* @param @param menus
	* @param @return
	* @author dengyanghao
	* @return List
	* @throws
	 */
	public static List getDynamicMenus(List menus){
		String json = "";
		String jsonChildren = "";
		List children = new ArrayList<>();
		for (Object object : menus) {
			Map<String,Object> map =  new HashMap<String, Object>();
			Map<String,Object> metaMap1 =  new HashMap<String, Object>();
			List list = new ArrayList<>();
			Module module = (Module)object;
			List<Module> moduleList = module.getChildren();
			if(!moduleList.isEmpty()){
				for (Module module2 : moduleList) {
					Map<String,Object> mapChildren =  new HashMap<String, Object>();
					Map<String,Object> metaMap =  new HashMap<String, Object>();
					mapChildren.put("path", "/"+module2.getId());
					mapChildren.put("component",module2.getIdpath());
					metaMap.put("title", module2.getName());
					//metaMap.put("affix", true);
					mapChildren.put("meta",metaMap);
					mapChildren.put("name",module2.getName());
					list.add(mapChildren);
				}
			}
			map.put("name",module.getName());
			map.put("path", "/"+module.getId());
			map.put("component","Layout");
			map.put("redirect",module.getId());
			map.put("alwaysShow",true);
			metaMap1.put("title", module.getName());
			metaMap1.put("icon", module.getIcon()==null ? "":module.getIcon());
			map.put("meta",metaMap1);
			map.put("children",list);
			children.add(map);
		}
		return children;
	}
}