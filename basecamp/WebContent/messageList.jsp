<%@ page import="basecamp.vo.Message" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>

<title>방명록</title>
</head>
<body>
	<form action="add" method="post">
		이메일 : <input id="email" type="text" name="email"><br>
		암호 : <input type="password" name="password"><br>
		<textarea rows="5" cols="20" name="content"></textarea><br>
		<input type="submit" value="글쓰기">
	</form>

	<script>
		$("#email").blur(function () {
			if(!EmailValidator($("#email").val())) {
				alert("이메일 형식이 잘못 되었습니다.");
				$("#email").val("");
			}
		});
		
		function EmailValidator(email) {
		    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		    return re.test(email);
		}
	</script>
	
	<jsp:useBean id="messages"
			scope = "request"
			class = "java.util.ArrayList"
			type = "java.util.ArrayList<basecamp.vo.Message>"/>
	
	<%	
		for(Message message : messages) {
	%>
	<%=message.getEmail() %>,
	<%=message.getCreatedDate() %><br>
	<%=message.getContent() %><br>
	<a href="update?no=<%=message.getNo() %>">[수정]</a><br><br>
	<%} %>
	
</body>
</html>