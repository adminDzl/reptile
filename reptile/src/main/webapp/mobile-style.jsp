<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0056)http://caibaojian.com/demo/2015/3/register/success.html# -->
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">


<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/css/mobile/app.css">
<script src="${pageContext.request.contextPath}/jslib/js/mobile/hm.js"></script>


<title>下载应用</title>
</head>
<body class="success">
	<div class="page-wrap">
		<div class="info">
			<div class="ok-btn">
				<img src="${pageContext.request.contextPath}/jslib/images/mobile/ok.png"
					alt="打开成功">
			</div>
			<p class="entry-con">感谢您加入</p>
			<h2 class="entry-hd">恭喜注册成功</h2>
			<ul class="info-list">
				<li>您的账号：15507621887</li>
				<li>您的密码：621887</li>
			</ul>
		</div>
		<div class="download">
			<h3 class="entry-hd">立即下载</h3>
			<p class="entry-con">即可免费领取一年VIP使用</p>
			<div class="download-btn">
				<a href="http://x2.vipcn.org/v12/cwx/gaodeditu.apk"><img
					src="${pageContext.request.contextPath}/jslib/images/mobile/ios-btn.png"
					alt="苹果版下载">
				</a> <a href="http://x2.vipcn.org/v12/cwx/gaodeditu.apk"
					class="android-btn" id="J_weixin"><img
					src="${pageContext.request.contextPath}/jslib/images/mobile/android-btn.png"
					alt="安卓版下载">
				</a>
			</div>
		</div>
		${weiID }${gongID }
		<div class="app">
			<img src="${pageContext.request.contextPath}/jslib/images/mobile/app.jpg"
				alt="应用预览">
			<p class="entry-con">
				下载完成后，在健康档案中打开“扫一扫”<br>再次扫描您看到的二维码<br>如有下载困难，请致电：0760-88888888
			</p>
		</div>
		<div class="footer-bg">
			<p class="entry-con">注：微信用户请在右上角选择“在浏览器中打开”，再选择下载应用</p>
		</div>
	</div>
	<div id="weixin-tip">
		<p>
			<img
				src="${pageContext.request.contextPath}/jslib/images/mobile/live_weixin.png"
				alt="微信打开"><span id="close" title="关闭" class="close">×</span>
		</p>
	</div>
	<script type="text/javascript">
		var is_weixin = (function() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		})();
		window.onload = function() {
			var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight
					: document.documentElement.clientHeight;
			var btn = document.getElementById('J_weixin');
			var tip = document.getElementById('weixin-tip');
			var close = document.getElementById('close');
			if (is_weixin) {
				btn.onclick = function(e) {
					tip.style.height = winHeight + 'px';
					tip.style.display = 'block';
					return false;
				}
				close.onclick = function() {
					tip.style.display = 'none';
				}
			}
		}
	</script>

</body>
</html>