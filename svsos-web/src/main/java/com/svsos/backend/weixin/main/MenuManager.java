package com.svsos.backend.weixin.main;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import com.svsos.backend.weixin.pojo.AccessToken;
import com.svsos.backend.weixin.pojo.Button;
import com.svsos.backend.weixin.pojo.CommonButton;
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
        btn11.setName("新/待接收工单");  
        btn11.setType("view");  
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx05df8f67d2213386&redirect_uri=http://weixin.svsos.com/svsos-web/wx/order&response_type=code&scope=snsapi_base&state=123#wechat_redirect"); 
  
        ViewButton btn12 = new ViewButton();  
        btn12.setName("待完成工单");  
        btn12.setType("view");  
        btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx05df8f67d2213386&redirect_uri=http://weixin.svsos.com/svsos-web/wx/order&response_type=code&scope=snsapi_base&state=123#wechat_redirect");  
  
        ViewButton btn13 = new ViewButton();  
        btn13.setName("所有工单");  
        btn13.setType("view");  
        btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx05df8f67d2213386&redirect_uri=http://weixin.svsos.com/svsos-web/wx/order&response_type=code&scope=snsapi_base&state=123#wechat_redirect"); 
  
        ViewButton btn14 = new ViewButton();  
        btn14.setName("工单地图");  
        btn14.setType("view");  
        btn14.setUrl("http://www.baidu.com");  
  
        CommonButton btn21 = new CommonButton();  
        btn21.setName("21");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
        CommonButton btn22 = new CommonButton();  
        btn22.setName("22");  
        btn22.setType("click");  
        btn22.setKey("22");  
  
        CommonButton btn23 = new CommonButton();  
        btn23.setName("23");  
        btn23.setType("click");  
        btn23.setKey("23");  
  
        CommonButton btn24 = new CommonButton();  
        btn24.setName("24");  
        btn24.setType("click");  
        btn24.setKey("24");  
  
        CommonButton btn25 = new CommonButton();  
        btn25.setName("25");  
        btn25.setType("click");  
        btn25.setKey("25");  
  
        ViewButton btn31 = new ViewButton();  
        btn31.setName("个人信息");  
        btn31.setType("view");  
        btn31.setUrl("http://www.baidu.com"); 
  
        ViewButton btn32 = new ViewButton();  
        btn32.setName("帮助/说明");  
        btn32.setType("view");  
        btn32.setUrl("http://www.baidu.com");  
  
        ViewButton btn33 = new ViewButton();  
        btn33.setName("联系随售");  
        btn33.setType("view");  
        btn33.setUrl("http://www.baidu.com");   
  
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("工单查询");  
        mainBtn1.setSub_button(new ViewButton[] { btn11, btn12, btn13, btn14 });  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("工单完成");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });  
  
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
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
  
        return menu;  
    } 
}  
