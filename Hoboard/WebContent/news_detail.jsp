<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@page import="news.News_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("nseq");
int seq = Integer.parseInt(nseq);
System.out.println(seq);
%>

<% 
News_Dao ndao = News_Dao.getInstance();
//dao.readcount(seq);
News_Dto ndto = (News_Dto)request.getAttribute("ndto");
%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>news_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>건강관리</h1>

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=ndto.getTitle()%>" readonly></td></tr>	
	<tr>	
		<th>날짜</th>
			<td><%=ndto.getDate()%></td></tr>
	<tr>
		<th>조회수</th>
			<td><%=ndto.getViewcount()%></td></tr>
	<tr>
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=ndto.getContent()%>" readonly></td></tr>

</table>

<button type="button" id="exBtn">전단계</button>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>


<script type="text/javascript">
$(document).ready(function () {
	
	$("#updateBtn").click(function () {		
		location.href = "news_update.do?work=update&nseq=<%=seq%>";
	});
	
	$("#deleteBtn").click(function () {
		location.href = "news_update.do?work=del&nseq=<%=seq%>";
		alert('삭제 완료');
	});
	
	$("#exBtn").click(function () {		
		location.href = "news_list.do";
	});
	
});

</script>










</body>
</html>
