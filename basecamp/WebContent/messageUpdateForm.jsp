<%@ page import="basecamp.vo.Message" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<body>

		

	<jsp:useBean id="message"
			scope = "request"
			class = "basecamp.vo.Message"
			type = "basecamp.vo.Message"/>
	
	<form action="update?no=<%=message.getNo() %>" method="post">
	이메일 : <input type="text" name="email" value="<%=message.getEmail() %>" readonly><br>
	암호 : <input type="password" name="password"><br>
	<textarea rows="5" cols="20" name="content"><%=message.getContent() %></textarea><br>
		<input type="submit" value="수정">
	</form>	
</body>
</html>