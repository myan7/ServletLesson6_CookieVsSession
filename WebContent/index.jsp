<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Button</h1>
<form action="login">
<input type = "submit" value = Login>
</form>

<p>Cookie Test 1 to write Cookie from Server to Client side/Browser</p>
<h1>Cookies</h1>
<form action=cookietest1>
	<input type=submit value="Add Cookie">
</form>

<p>Cookie Test 2 to retrieve Cookie data</p>
<form action=cookietest2>
	<input type=submit value="Get Cookie">
</form>

<p>Cookie Test 3 to delete Cookie data</p>
<form action=cookietest3>
	<input type=submit value="Del Cookie">
</form> 

</body>
</html>