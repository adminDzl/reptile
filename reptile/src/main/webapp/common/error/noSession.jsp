<%@ page language="java" pageEncoding="UTF-8"%>
<div><H1>系统提示</H1></div></br>
 <div><H2>${msg}</H2></div>

<%--<script type="text/javascript">
//判断如果当前页面不为主框架，则将主框架进行跳转
try{
	   var tagert_URL = "${pageContext.request.contextPath}/userController/login.do";
		if(self==top){
	    	window.location.href = tagert_URL;
	    }else{
	    	top.location.href = tagert_URL;
	    }
	}catch(e){}
</script>
--%>