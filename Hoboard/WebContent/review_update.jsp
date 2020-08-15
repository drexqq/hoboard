<%@page import="review.Review_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
Review_Dto dto =(Review_Dto)request.getAttribute("detaillist");


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<h1>후기수정</h1>
<form action="review" method="post">
<input type="hidden" name="key" value="update">
<input type="hidden" name="seq" value="<%=dto.getReview_seq() %>">
<table style="width:600" border="1" align="center">
<col width="300"><col width="500">
<tr>
	<th>병원 카테고리</th>
		<td><input type="text" name="busi_cate" readonly="readonly" size="50" value="<%=dto.getBusi_cate()%>"><td>
</tr>
<tr>
	<th>제목</th>
		<td><input type="text" name="title"  size="50" value="<%=dto.getTitle()%>"><td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="id" readonly="readonly" size="50" value="<%=dto.getIndvd_id()%>"><td> 		
</tr>
<tr>
	<th>파일다운로드</th>
		<td><input type="button" name="btndown" value="파일다운로드" onclick="location.href='filedown?filename=<%=dto.getFilename() %>&seq=<%=dto.getReview_seq() %>'"></td>
</tr>
<tr>
	<th>작성일</th>
		<td><input type="text" name="wdate" readonly="readonly" size="50" value="<%=dto.getWdate()%>"><td>
</tr>
<tr>
	<th>조회수</th>
		<td><input type="text" name="viewcount" readonly="readonly" size="50" value="<%=dto.getViewcount()%>"></td>
</tr>
<tr>
	<td>
	<textarea rows="20px" cols="70px" name="content"><%=dto.getContent()%></textarea>
	</td>
</tr>
</table>
<div align="center">
	<input type="submit" value="글수정">
</div>
</form>

</body>
</html>