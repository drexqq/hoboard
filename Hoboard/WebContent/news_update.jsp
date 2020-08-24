<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@page import="news.News_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	News_Dto dto = (News_Dto)request.getAttribute("dto");
	System.out.println("dto:"+dto.toString());	
	
	News_Dao dao = News_Dao.getInstance();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>news_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>건강 정보 수정 페이지</h1>
<form action="news?work=update" method="get">
 <input type="hidden" name="work" value="updateAf"> 
 <input type="hidden" name="seq" value="<%=dto.getNews_seq()%>">

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>"></td></tr>
	<tr>
		<th>파일첨부</th>
			<td><input type="text" name="file" size="50px" value=""></td></tr>	
	<tr>	
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>"></td></tr>

</table>

<input type="button" onclick="location.href='news'" value="글 목록">
<input type="submit" value="수정 완료"> 
</form>
</body>
</html>
