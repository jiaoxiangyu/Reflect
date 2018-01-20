<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="UserServlet?tag=update&id=${user.id}" method="post">
		name:<input type="text" name="name" value="${user.name}"><br>
		sex:<input type="text" name="sex" value="${user.sex}" ><br>
		age:<input type="text" name="age" value="${user.age}"><br>
		<input type="submit" value="修改">
		<h4>${meg }</h4>
	</form>
</body>
</html>