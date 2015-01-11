package com.svsos.backend.controller;

import com.svsos.backend.entity.Menu;
import com.svsos.backend.service.AccountService;
import com.svsos.backend.service.MenuService;
import com.svsos.core.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/createNewMenu", method = RequestMethod.GET)
	public String createNewMenu(Model model) {
		model.addAttribute("menuTree", menuService.getCurrentUserMenuTree());
		model.addAttribute("roleList", accountService.getCurrentUser().getRoleList());
		return "menu/createNewMenu";
	}

	@RequestMapping(value = "/createNewMenu", method = RequestMethod.POST)
	public String createNewMenu(Menu menu, long[] checkedUserIds) {
		System.out.println(JsonMapper.nonDefaultMapper().toJson(menu));
		System.out.println(JsonMapper.nonDefaultMapper().toJson(checkedUserIds));
		return "menu/createNewMenu";
	}
}
