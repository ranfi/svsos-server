<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
    <title>网点工单查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta id="viewport" name="viewport" content="width=device-width;initial-scale=1.0;minimum-scale=1.0; maximum-scale=1.0'user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/order.js" type="text/javascript"></script>
    <link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="wrapper">
        <nav>
            <ul class="mallTab" >
                <li class="tabLi selected "><a href="javascript:void(0);">待接工单</a></li>
                <li class="tabLi"><a href="javascript:void(0);">待完成工单</a></li>
                <li class="tabLi"><a href="javascript:void(0);">全部工单</a></li>
             
            </ul>
        </nav>
        <section>
            <div class="tabConts">
                <div class="tabCont01 on" id="ShowLists">
                <c:forEach items="${neworderLists}" var="list" varStatus="status">              
                    <div class="order01">                                      
                        <div class="order_info">
                         <a href="javascript:void(0);" onclick="todetail('${list.orderLsh}')"> <span class="info_left fbold">${list.orderLsh}</span><span class="info_right alignRight">${list.remark}</span></a>                     
                        </div>
                      
                        <div class="order_user">
                            <p class="fbold">${list.name}</p>
                            <p class="fbold alignCenter">洗衣机</p>                            
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:${list.tel}">${list.tel}</a></p>
                        </div>
                        <p>${list.address}</p>
                        <div class="order_info">
                            <span class="info_left">${list.createTime}</span><span class="info_right alignRight fGray">未接单<a href="javascript:void(0);" class="orderbt" onclick="accept('${list.orderLsh}')">接单</a></span>
                        </div>                      
                    </div>

                   </c:forEach>
                </div>
                <div class="tabCont01">
                <c:forEach items="${acporderLists}" var="list" varStatus="status">
                    <div class="order01">
                        <div class="order_info">                        
                           <a href="javascript:void(0);" onclick="todetail('${list.orderLsh}')"><span class="info_left fbold">${list.orderLsh}</span><span class="info_right alignRight">${list.remark}</span></a>
                        </div>
                        <div class="order_user">
                            <p class="fbold">${list.name}</p>
                            <p class="fbold alignCenter">洗衣机</p>
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:${list.tel}">${list.tel}</a></p>
                        </div>
                        <p>${list.address}</p>
                        <div class="order_info">
                            <span class="info_left">${list.createTime}</span><span class="info_right alignRight fGray">已接单</span>
                        </div>
                    </div>
                   </c:forEach>
                </div>
                <div class="tabCont01">
                <c:forEach items="${allorderLists}" var="list" varStatus="status">
                    <div class="order01">
                        <div class="order_info">
                         <a href="javascript:void(0);" onclick="todetail('${list.orderLsh}')"><span class="info_left fbold">${list.orderLsh}</span><span class="info_right alignRight">${list.remark}</span></a>
                        </div>
                        <div class="order_user">
                            <p class="fbold">${list.name}</p>
                            <p class="fbold alignCenter">洗衣机</p>
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:${list.tel}">${list.tel}</a></p>
                        </div>
                        <p>${list.address}</p>
                        <div class="order_info">
                            <span class="info_left">${list.createTime}</span><c:choose><c:when test="${list.status==2}"><span class="info_right alignRight fGray">未接单<a href="javascript:void(0);" class="orderbt" onclick="accept('${list.orderLsh}')">接单</a></span></c:when><c:when test="${list.status==3}"><span class="info_right alignRight fGray">已接单</span> </c:when><c:when test="${list.status==4}"><span class="info_right alignRight fGray">已完成</span> </c:when><c:when test="${list.status==5}"><span class="info_right alignRight fGray">已回访</span></c:when><c:otherwise><span class="info_right alignRight fGray">已结算</span></c:otherwise></c:choose> 		                  
                        </div>
                    </div>
                   </c:forEach>
                </div>
            </div>
        </section>
       <footer>
		    <nav>
		        <div class="bottom">
		            <ul class="bottomNav" >
		                <li class="bottomIco bottomIco1 selected"><a href="${ctx}/wx/order"><i class="bottomBg"></i>我的工单</a></li>
		                <li class="bottomIco bottomIco3"><a href="${ctx}/wx/signin"><i class="bottomBg"></i>每日签到</a></li>
		                <li class="bottomIco bottomIco4"><a href="${ctx}/wx/user/profile"><i class="bottomBg"></i>个人中心</a></li>
		            </ul>
		        </div>
		    </nav>
		</footer>
    </div>
    <script type="text/javascript">        
        $(function(){
        	   $(".mallTab .tabLi").click(function(){
        	       var index = $(this).index();
        	       $(".mallTab .selected").removeClass("selected");
        	       $(this).addClass("selected");
        	       $(".tabCont01.on").removeClass("on");
                   $(".tabCont01").eq(index).addClass("on");
        	   });
        	});
    </script>
</body>
</html>
