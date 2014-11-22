<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String selected = request.getParameter("num"); %>
<html>
<head>
    <title>销售工单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta id="viewport" name="viewport" content="width=device-width;initial-scale=1.0;minimum-scale=1.0; maximum-scale=1.0'user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/order.js" type="text/javascript"></script>
    <style type="text/css">
        @charset "utf-8";
        *{margin:0;padding:0;list-style:none;}
        html{width:100%;height:100%;}
        body{background-color:#fff;line-height:1.5em;color:#000;font-size:14px;height: 100%;font-family: \5FAE\8F6F\96C5\9ED1;}
        h1,h2,h3,h4,h5,h6{font-size:1em;}
        a{text-decoration:none;}
        a:focus {outline:0;}
        img{display:block;}
        .clear:after{display:block;height:0;visibility:hidden;clear:both;content:".";}
        input{outline:0px;}
        .fbold{font-weight: bold;}
        .fGray{color: #666;}

            /*公用-对齐方式*/
        .alignCenter{text-align:center;}
        .alignRight{text-align:right;}
        .margin1em{margin: 1em;}


        .navbox{background-color: #f5f5f5;border-bottom: 1px solid #cccccc;}
        .nav{
            column-count: 3;
            column-gap: 0;
            -webkit-column-count: 3;
            -webkit-column-gap: 0;
            -moz-column-count: 3;
            -moz-column-gap: 0;
            -ms-column-count: 3;
            -ms-column-gap: 0;
            border: 1px solid #2e97f0;
            border-radius: 3px;
            background-color: #ffffff;
        }
        .nav a{display:block;width:100%;height: 100%;color: #333;line-height: 2em; }
        .nav .active a{color: #ffffff;background-color: #2e97f0;}
        .tabCont01{display: none;}
        .tabCont01.on{display: block;}
        .order01{border-bottom: 1px solid #cccccc;padding: 0.5em 1em;line-height: 1.5em;}
        .i-b-50{display: inline-block;width: 50%;}
        .info_left{display: inline-block;width: 50%;}
        .info_right{display: inline-block;width: 50%;}
        .order_user{
            display:box;display:-webkit-box;display:-moz-box;
            width: 100%;
            flex-flow:row wrap;
            line-height: 1.8em;
        }
        .order_user >p{box-flex:1;-webkit-box-flex:1;-moz-box-flex:1;}
        .user_tel{width: 30%;}
        .orderbt{display: inline-block;width: 50px;height: 23px;line-height:23px;background-color: #2e97f0;color: #ffffff;text-align: center;border-radius: 5px;margin-left: 4px;}
        .tel_bt{background-color: #2e97f0;height: 23px;line-height:23px;background-color: #2e97f0;color: #ffffff;text-align: center;border-radius: 2px;padding: 2px 5px;}
    </style>
</head>
<body>
    <div class="wrapper">
        <header>
        </header>
       <section class="navbox">
           <ul class="nav alignCenter margin1em">
               <li class="active"><a href="javascript:void(0);">待接工单</a></li>
               <li><a href="javascript:void(0);">待完成工单</a></li>
               <li><a href="javascript:void(0);">全部工单</a></li>
           </ul>
       </section>
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
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:1383838438">${list.tel}</a></p>
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
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:1383838438">${list.tel}</a></p>
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
                            <p class="user_tel alignRight"><a class="tel_bt" href="tel:1383838438">${list.tel}</a></p>
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
    </div>
    <script type="text/javascript">
        $(".nav li").click(function(e){
            e.preventDefault();
            var index = $(this).index();
            $(".nav .active").removeClass("active");
            $(this).addClass("active");
            $(".tabCont01.on").removeClass("on");
            $(".tabCont01").eq(index).addClass("on");
        });
    </script>
</body>
</html>
