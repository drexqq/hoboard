<%@page import="member.Member_Dto"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건강사항 글쓰기</title>
</head>
<body>
<h1>건강 정보 쓰기</h1>

<form action="news_file" enctype="multipart/form-data" method="post">

<table border="1">
<col width="200"><col width="400">
<tr>
	<th>제목</th>
	<td><input type="text" name="title" size="50px"></td>
</tr>
<tr>
	<th>파일첨부</th>
	<td><input type="file" name="file"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea rows="20" cols="50px" name="content"></textarea></td>
</tr>
<tr>
	<td colspan="2">&nbsp;&nbsp;
	<input type="submit" value="글쓰기">
	<input type="reset" value="초기화">
	<input type="button" value="글목록" onclick="news">
	</td>
</tr>
</table>

</form>
</body>
</html> 