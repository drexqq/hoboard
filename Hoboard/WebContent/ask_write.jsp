<%@page import="member.Member_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
Object ologin = session.getAttribute("sessionID");
Member_Dto mem = null;
if(ologin == null){
%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}
mem = (Member_Dto)ologin;
%> 	  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask.write.jsp</title>
</head>
<body>
<h1>Q&A 글쓰기</h1>

<form action="ask.do?two=write" method="post">
<input type="hidden" name="id" value="admin">
<table border="1">
<col width="200"><col width="400">
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="50px">
	</td>
</tr>
<!-- <tr>
	<th>파일첨부</th>
	<td>
		<input type="text" name="title" size="50px">
	</td>
</tr> -->

<tr>
	<th>내용</th>
	<td>
		<textarea rows="20" cols="50px" name="content"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		&nbsp;&nbsp;<input type="submit" value="글쓰기">
	</td>
</tr>

</table>

</form>

<a href="my_ask.jsp">글목록</a>
</body>
</html>