<%@page import="ask.Ask_Dao"%>
<%@page import="java.util.List"%>
<%@page import="ask.Ask_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("seq");
int seq = Integer.parseInt(nseq);
System.out.println("ask_detail"+seq);
%>
<% 
	Ask_Dto dto = (Ask_Dto)request.getAttribute("dto");
	System.out.println("dto:"+dto.toString());	
	
	Ask_Dao dao = Ask_Dao.getInstance();
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask_update_jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>수정하기</h1>
<form action="ask.do?one=move" method="get">
<input type="hidden" name="one" value="updateAf">
 <input type="hidden" name="seq" value="<%=seq%>">

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>"></td></tr>	
	<tr>	
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>"></td></tr>

</table>

<input type="button" onclick="location.href =ask.do?one=move" value="목록보기">
<input type="submit" value="수정 완료"> 
</form>
</body>
</html>
