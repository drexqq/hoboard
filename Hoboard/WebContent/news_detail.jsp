<%@page import="news.News_COMM_Dto"%>
<%@page import="news.news_comm_dao"%>
<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@page import="news.News_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("seq");
int seq = Integer.parseInt(nseq);
System.out.println("detail="+seq);
%>

<% 
News_Dao dao = News_Dao.getInstance();
//dao.readcount(seq);
News_Dto dto = (News_Dto)request.getAttribute("dto");
%>

<%-- <% 
news_comm_dao dao2 = news_comm_dao.getInstance();
news_comm_dto dto2 = (news_comm_dto)request.getAttribute("dto2");
System.out.println("news comm dto" +dto);
%>  --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건강 정보 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>건강관리</h1>

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>" readonly></td></tr>	
	<tr>	
		<th>날짜</th>
			<td><%=dto.getDate()%></td></tr>
	<tr>
		<th>조회수</th>
			<td><%=dto.getViewcount()%></td></tr>
	<tr>
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>" readonly></td></tr>

</table>
<br>
<button type="button" id="exBtn">전단계</button>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>
<br>
<br>

<textarea name="c_content"></textarea>
<button type="button" id="commBtn">댓글 등록</button>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#updateBtn").click(function () {		
		location.href = "news?work=update&seq=<%=seq%>";
	});
	
	$("#deleteBtn").click(function () {
		location.href = "news?work=del&seq=<%=seq%>";
		alert('삭제 완료');
	});
	
	$("#exBtn").click(function () {		
		location.href = "news?work=move";
	});
	
	$("#commBtn").click(function () {		
		location.href = "news?work=c_update&seq=<%=seq%>";
	});
});

</script>










</body>
</html>
