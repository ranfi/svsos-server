<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>每日签到</title>
<meta id="viewport" name="viewport"
	content="width=device-width;initial-scale=1.0;minimum-scale=1.0; maximum-scale=1.0'user-scalable=no" />
<script src="${ctx}/static/jquery/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap-switch/css/bootstrap-switch.min.css"
	type="text/css" />
<style type="text/css">
@charset "utf-8";

* {
	margin: 0;
	padding: 0;
	list-style: none;
}

html {
	width: 100%;
	height: 100%;
}

body {
	background-color: #fff;
	line-height: 1.5em;
	color: #000;
	font-size: 14px;
	height: 100%;
	font-family: \5FAE\8F6F\96C5\9ED1;
}

h1,h2,h3,h4,h5,h6 {
	font-size: 1em;
}

a {
	text-decoration: none;
}

a:focus {
	outline: 0;
}

img {
	display: block;
}

.clear:after {
	display: block;
	height: 0;
	visibility: hidden;
	clear: both;
	content: ".";
}

input {
	outline: 0px;
}

.fbold {
	font-weight: bold;
}

.fGray {
	color: #666;
}
/*公用-对齐方式*/
.alignCenter {
	text-align: center;
}

.alignRight {
	text-align: right;
}

.margin1em {
	margin: 1em;
}

.navbox {
	background-color: #f5f5f5;
	border-bottom: 1px solid #cccccc;
}

.nav {
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
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	background-color: #ffffff;
	height: 30px;
}

.nav a {
	display: block;
	width: 100%;
	height: 100%;
	color: #333;
	line-height: 2em;
}

.nav .active a {
	color: #ffffff;
	background-color: #2e97f0;
}

.tabCont01 {
	display: none;
}

.tabCont01.on {
	display: block;
}

.order01 {
	border-bottom: 1px solid #cccccc;
	padding: 0.5em 1em;
	line-height: 1.5em;
}

.i-b-50 {
	display: inline-block;
	width: 50%;
}

.info_left {
	display: inline-block;
	width: 100%;
}

.info_right {
	display: inline-block;
	width: 50%;
}

.order_user {
	display: box;
	display: -webkit-box;
	display: -moz-box;
	width: 100%;
	flex-flow: row wrap;
	line-height: 1.8em;
}

.order_user>p {
	box-flex: 1;
	-webkit-box-flex: 1;
	-moz-box-flex: 1;
}

.user_tel {
	width: 30%;
}

.orderbt {
	display: inline-block;
	width: 50px;
	height: 23px;
	line-height: 23px;
	background-color: #2e97f0;
	color: #ffffff;
	text-align: center;
	border-radius: 5px;
	margin-left: 4px;
}

.tel_bt {
	background-color: #2e97f0;
	height: 23px;
	line-height: 23px;
	background-color: #2e97f0;
	color: #ffffff;
	text-align: center;
	border-radius: 2px;
	padding: 2px 5px;
}
</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4>每日签到</h4>
		</div>
		<div class="panel-body">
			<div style="padding: 10px;">
				<form class="form-horizontal" method="post" name="myform">
					<c:if test="${hasSingn==0}">
						<a href="javascript:void(0);" onclick="toSingin()"
							class="btn btn-default btn-lg active" role="button">签到</a>
					</c:if>
					<c:if test="${hasSingn==1}">
						<button type="button" class="btn btn-default btn-lg"
							disabled="disabled">已签到</button>
					</c:if>
					<div class="tabConts">
						<div class="tabCont01 on" id="ShowLists">
							<c:if test="${not empty signinLists}">
								<c:forEach items="${signinLists}" var="list" varStatus="status">
									<div class="order01">
										<div class="order_info">
											<span class="info_left fbold">${list.signinDate}您签到时间为：${list.signinTime}</span>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/**
		 * 以下为html5代码,获取地理位置
		 */
		function toSingin() {
			//检查浏览器是否支持地理位置获取
			if (navigator.geolocation) {
				//若支持地理位置获取,成功调用showPosition(),失败调用showError
				// alert("正在努力获取位置...");
				var config = {
					enableHighAccuracy : true,
					timeout : 50000,
					maximumAge : 30000
				};
				navigator.geolocation.getCurrentPosition(showPosition,
						showError, config);
			} else {
				//alert("Geolocation is not supported by this browser.");
				alert("定位失败,用户已禁用位置获取权限");
			}
		}

		/**
		 * 获取地址位置成功
		 */
		function showPosition(position) {
			//获得经度纬度

			var x = position.coords.latitude;
			var y = position.coords.longitude;
			//配置Baidu Geocoding API
			var url = "./signin/savelocation" + "?callback=renderReverse"
					+ "&location=" + x + "," + y + "&output=json" + "&pois=0";
			$.ajax({
				type : "GET",
				dataType : "jsonp",
				url : url,
				success : function(json) {
					if (json == null || typeof (json) == "undefined") {
						return;
					}
					if (json.status != "0") {
						return;
					}
					location.replace(location.href);
					//setAddress();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[x:" + x + ",y:" + y + "]地址位置获取失败,请手动选择地址");
					location.replace(location.href);
				}
			});
		}

		/**
		 * 获取地址位置失败[暂不处理]
		 */
		function showError(error) {
			switch (error.code) {
			case error.PERMISSION_DENIED:
				alert("定位失败,用户拒绝请求地理定位");
				//"User denied the request for Geolocation.[用户拒绝请求地理定位]"
				break;
			case error.POSITION_UNAVAILABLE:
				alert("定位失败,位置信息是不可用");
				//"Location information is unavailable.[位置信息是不可用]"
				break;
			case error.TIMEOUT:
				alert("定位失败,请求获取用户位置超时");
				//"The request to get user location timed out.[请求获取用户位置超时]"
				break;
			case error.UNKNOWN_ERROR:
				alert("定位失败,定位系统失效");
				//"An unknown error occurred.[未知错误]"
				break;
			}
		}
	</script>
</body>
</html>
