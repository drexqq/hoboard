<%@page import="Ask.Ask_Dto"%>
<%@page import="Ask.Ask_Dao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("seq");
int seq = Integer.parseInt(nseq);
System.out.println("ask_detail"+seq);
%>

<% 
Ask_Dao dao = Ask_Dao.getInstance();
//dao.readcount(seq);
System.out.println("!!!!!!!!!!!!!!!!!!!!!");
Ask_Dto dto = (Ask_Dto)request.getAttribute("dto");
%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>Q&A 문의에 대한 답변</h1>

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>" readonly></td></tr>	
	<tr>	
		<th>날짜</th>
			<td><%=dto.getWdate()%></td></tr>
	<tr>
		<th>조회수</th>
			<td><%=dto.getComm()%></td></tr>
	<tr>
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>" readonly></td></tr>

</table>

<button type="button" id="exBtn">전단계</button>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>
</form>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#updateBtn").click(function () {	
		location.href = "ask.do?one=update&seq=<%=seq%>";
	});
	
	$("#deleteBtn").click(function () {
		location.href = "ask.do?one=del&seq=<%=seq%>";
		alert('삭제 완료');
	});
	
	$("#exBtn").click(function () {		
		location.href = "ask.do?one=move";
	});
	
});

</script>





</body>
</html>
