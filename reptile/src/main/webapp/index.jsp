<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	if( path ==null || path.length()==0 ){
		response.sendRedirect( "/winxinControl/main.wei");
	}else{
   		response.sendRedirect( basePath + "/winxinControl/main.wei"); 
	}
   
%>
