<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/common/tld/c.tld"%>
<%@taglib prefix="fn" uri="/common/tld/fn.tld"%>
<%@taglib prefix="fmt" uri="/common/tld/fmt.tld"%>

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "V1:201401106";%>

<%
java.util.Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "default";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var mtm = mtm || {};
mtm.contextPath = '<%=contextPath%>';
mtm.basePath = '<%=basePath%>';
mtm.version = '<%=version%>';
</script>

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/css/layout.css" type="text/css" media="screen" />

<%-- 引入jQuery  --%>
<script src="<%=contextPath%>/jslib/js/jquery-1.5.2.min.js" type="text/javascript"></script>
<script src="<%=contextPath%>/jslib/js/hideshow.js" type="text/javascript"></script>
<script src="<%=contextPath%>/jslib/js/jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/js/jquery.equalHeight.js"></script>

<%-- 引入my97日期时间控件 
<script type="text/javascript" src="<%=contextPath%>/jslib/js/WdatePicker/WdatePicker.js" charset="utf-8"></script>
--%>
