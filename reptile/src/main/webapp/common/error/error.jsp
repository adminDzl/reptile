<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>系统异常</title>
</head>
<body>
<% Exception e = (Exception)request.getAttribute("exception"); %>
<H2>错误异常: <%= e.getClass().getSimpleName()%></H2>
<hr />
<br/>
<P><h2>系统内部错误： 请联系系统管理员处理！</h2>
</P>
</body>
</html>