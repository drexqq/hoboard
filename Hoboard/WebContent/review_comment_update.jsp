<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>덧글 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>


	<h1>[원본글]</h1>

	<a href="review?key=main">글 목록으로</a>

	<table style="width: 600" border="1">
		<col width="300">
		<col width="500">
		<tr>
			<th>병원 카테고리</th>
			<td>${detaillist.getBusi_cate()}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${detaillist.getTitle()}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${detaillist.getIndvd_id()}</td>
		</tr>
		<tr>
			<th>파일다운로드</th>
			<td><input type="button" name="btndown" value="파일"
				onclick="location.href='file?filename=${detaillist.getFilename()}&seq=${detaillist.getReview_seq()}'"></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${detaillist.getWdate()}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${detaillist.getViewcount()}</td>
		</tr>
		<tr>
			<td><textarea rows="20px" cols="70px" name="content"
					readonly="readonly">${detaillist.getContent()}</textarea></td>
		</tr>
	</table>



	<h1>[나의 댓글수정]</h1>

	<form action="COMM" method="get">
		<input type="hidden" name="key" value="updatecomplete"> <input
			type="hidden" name="seq" value="${commentlist.getSeq()}"> <input
			type="hidden" name="boardnum" value="${detaillist.getReview_seq()}">

		<table border="1">
			<tr>
				<th>ID:</th>
				<td>${commentlist.getId()}</td>
				<th>작성일:</th>
				<td>${commentlist.getDate()}</td>
				<td><input type="submit" value="수정완료">
				<td>
			<tr>
				<th>내용:</th>
				<td><input type="text" name="content" placeholder="${commentlist.getContent()}"></td>
			</tr>
		</table>
	</form>