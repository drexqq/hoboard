<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@page import="news.News_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	News_Dto ndto = (News_Dto)request.getAttribute("ndto");
	System.out.println("dto:"+ndto.toString());	
	
	News_Dao ndao = News_Dao.getInstance();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>news_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>update</h1>
<form action="news_update.do?work=move" method="get">
 <input type="hidden" name="work" value="updateAf"> 
 <input type="hidden" name="nseq" value="<%=ndto.getNews_seq()%>">

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=ndto.getTitle()%>"></td></tr>	
	<tr>	
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=ndto.getContent()%>"></td></tr>

</table>

<input type="button" onclick="location.href =news_list.do?work=move" value="목록으로 돌아가기">
<input type="submit" value="수정 완료"> 
</form>
</body>
</html>
