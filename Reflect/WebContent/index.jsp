<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="add.jsp">添加User</a>
	<a href="UserServlet?tag=delete&id=13">删除User</a>
	<a href="UserServlet?tag=update&id=7">修改User</a>
	<a href="UserServlet?tag=find&id=7">查找User</a>
	<h4>${meg}</h4>
</body>
</html>