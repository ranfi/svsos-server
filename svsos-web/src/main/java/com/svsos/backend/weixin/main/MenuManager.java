package com.svsos.backend.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.svsos.backend.weixin.pojo.AccessToken;
import com.svsos.backend.weixin.pojo.Button;
import com.svsos.backend.weixin.pojo.ComplexButton;
import com.svsos.backend.weixin.pojo.Menu;
import com.svsos.backend.weixin.pojo.ViewButton;
import com.svsos.backend.weixin.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author zhouliangjun
 * @date 2014-10-28
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx05df8f67d2213386";
		// 第三方用户唯一凭证密钥
		String appSecret = "a7c5877fd3ad5c508d297eb8171ca9ab";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ViewButton btn11 = new ViewButton();
		btn11.setName("未接收工单");
		btn11.setType("view");
		btn11.setUrl("http://weixin.svsos.com/svsos-web/wx/order");

		ViewButton btn12 = new ViewButton();
		btn12.setName("未完成工单");
		btn12.setType("view");
		btn12.setUrl("http://weixin.svsos.com/svsos-web/wx/order");

		ViewButton btn13 = new ViewButton();
		btn13.setName("所有工单");
		btn13.setType("view");
		btn13.setUrl("http://weixin.svsos.com/svsos-web/wx/order");

		ViewButton btn31 = new ViewButton();
		btn31.setName("个人信息");
		btn31.setType("view");
		btn31.setUrl("http://weixin.svsos.com/svsos-web/wx/user/profile");

		ViewButton btn32 = new ViewButton();
		btn32.setName("帮助/说明");
		btn32.setType("view");
		btn32.setUrl("http://weixin.svsos.com/svsos-web/wx/user/help");

		ViewButton btn33 = new ViewButton();
		btn33.setName("联系随售");
		btn33.setType("view");
		btn33.setUrl("http://weixin.svsos.com/svsos-web/wx/user/aboutus");

		// ComplexButton mainBtn1 = new ComplexButton();
		// mainBtn1.setName("我的工单");
		// mainBtn1.setSub_button(new ViewButton[] { btn11, btn12, btn13 });

		ViewButton btn1 = new ViewButton();
		btn1.setName("我的工单");
		btn1.setType("view");
		btn1.setUrl("http://weixin.svsos.com/svsos-web/wx/order");

		ViewButton btn2 = new ViewButton();
		btn2.setName("每日签到");
		btn2.setType("view");
		btn2.setUrl("http://weixin.svsos.com/svsos-web/wx/signin");

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("个人中心");
		mainBtn3.setSub_button(new ViewButton[] { btn31, btn32, btn33 });

		/**
		 * 这是公众号svsos目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, mainBtn3 });

		return menu;
	}
}
