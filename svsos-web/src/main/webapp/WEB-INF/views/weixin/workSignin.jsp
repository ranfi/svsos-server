<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>每日签到</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />
<script src="${ctx}/static/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
<link href="${ctx}/static/bootstrap/3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap-switch/css/bootstrap-switch.min.css" type="text/css" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet"/>
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
					<div style="padding-top:10px;"></div>
					<table class="table table-striped">
						<tr>
		 				  <th>签到日期</th>
						  <th>签到时间</th>
						  <th>签到地址</th>
						</tr>
						<c:if test="${not empty signinLists}">
									<c:forEach items="${signinLists}" var="list" varStatus="status">
						<tr>
							<td>${list.signinDate}</td>
							<td>
								${list.signinTime}
							</td>
							<td>${list.location}</td>
						</tr>
							</c:forEach>
						</c:if>
					</table>
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
				//alert("定位失败,用户已禁用位置获取权限");
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
					//alert("[x:" + x + ",y:" + y + "]地址位置获取失败,请手动选择地址");
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
				//alert("定位失败,用户拒绝请求地理定位");
				//"User denied the request for Geolocation.[用户拒绝请求地理定位]"
				break;
			case error.POSITION_UNAVAILABLE:
				//alert("定位失败,位置信息是不可用");
				//"Location information is unavailable.[位置信息是不可用]"
				break;
			case error.TIMEOUT:
				//alert("定位失败,请求获取用户位置超时");
				//"The request to get user location timed out.[请求获取用户位置超时]"
				break;
			case error.UNKNOWN_ERROR:
				//alert("定位失败,定位系统失效");
				//"An unknown error occurred.[未知错误]"
				break;
			}
		}
	</script>
	
	       <footer>
		    <nav>
		        <div class="bottom">
		            <ul class="bottomNav" >
		                <li class="bottomIco bottomIco1 "><a href="${ctx}/wx/order"><i class="bottomBg"></i>我的工单</a></li>
		                <li class="bottomIco bottomIco3 selected"><a href="${ctx}/wx/signin"><i class="bottomBg"></i>每日签到</a></li>
		                <li class="bottomIco bottomIco4"><a href="${ctx}/wx/user/profile"><i class="bottomBg"></i>个人中心</a></li>
		            </ul>
		        </div>
		    </nav>
		</footer>
</body>
</html>
