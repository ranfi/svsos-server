package com.svsos.backend.controller;

import com.svsos.backend.controller.common.ApplicationController;
import com.svsos.backend.service.AccountService;
import com.svsos.backend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends ApplicationController {

	@Autowired
	private MenuService menuService;
    @Autowired
    private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
        model.addAttribute("roleList", accountService.getCurrentUser().getRoleList());
        System.out.println(accountService.getCurrentUser().getRoleList());
        model.addAttribute("menuTree", menuService.getCurrentUserMenuTree());
		return "main";
	}

}
