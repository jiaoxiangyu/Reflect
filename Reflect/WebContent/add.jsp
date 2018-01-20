<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="UserServlet?tag=add" method="post">
		name:<input type="text" name="name" ><br>
		sex:<input type="text" name="sex" ><br>
		age:<input type="text" name="age" ><br>
		<input type="submit" value="添加">
		<h4>${meg }</h4>
	</form>
</body>
</html>