package com.svsos.backend.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.svsos.backend.common.bo.Constant;
import com.svsos.backend.datamodel.MenuNode;
import com.svsos.backend.entity.Menu;
import com.svsos.backend.entity.User;
import com.svsos.backend.repositories.jpa.MenuDao;
import com.svsos.core.utils.JsonMapper;

@Component
@Transactional
@Monitored
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private AccountService accountService;

	public List<Menu> getMenuList(int parentMenuId) {
		Sort sort = new Sort(Direction.ASC, "sortKey");
		return menuDao.findByPid(parentMenuId, sort);
	}

	public List<Menu> getMenuList(int parentMenuId, String roleIds) {
		return menuDao.findByUserRoles(parentMenuId, roleIds);
	}

	public List<MenuNode> getCurrentUserMenuTree() {
		Subject currentUser = SecurityUtils.getSubject();
		User user = accountService.findUserByLoginName(currentUser.getPrincipal().toString());
		return getMenuTree(user);
	}

	public List<MenuNode> getMenuTree(User user) {
		List<MenuNode> menuTree = Lists.newArrayList();
		List<Menu> menuList = getMenuList(0, user.getRoleIds());

		createMenuTree(menuList, menuTree, user.getRoleIds());
		System.out.println(JsonMapper.nonDefaultMapper().toJson(menuTree));
		return menuTree;
	}

	public void createMenuTree(List<Menu> menuList, List<MenuNode> menuTree, String roleIds) {

		for (Menu menu : menuList) {
			MenuNode node = new MenuNode();
			node.setName(menu.getName());
			node.setLinkAddress(menu.getLinkAddress());
			node.setMenuId(menu.getId());
			if (menu.getIsLeafNode() == Constant.MenuNodeStatus.MENU_NODE.value) {
				node.setNodeType(Constant.MenuNodeType.TYPE_CHILD_TREE.value);
				menuTree.add(node);

				MenuNode startNode = new MenuNode();
				startNode.setNodeType(Constant.MenuNodeType.TREE_START.value);
				menuTree.add(startNode);
				createMenuTree(getMenuList(menu.getId(), roleIds), menuTree, roleIds);
				MenuNode endNode = new MenuNode();
				endNode.setNodeType(Constant.MenuNodeType.TREE_END.value);
				menuTree.add(endNode);

			} else {
				node.setNodeType(Constant.MenuNodeType.TYPE_CHILD_NODE.value);
				menuTree.add(node);
			}

		}

	}
}
