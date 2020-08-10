<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 쓰기</title>
</head>
<body>
<table border="1">
<col width="200"><col width="400">
<tr>
	<th>병원 카테고리</th>
</tr>
<tr>
	<th>아이디 :</th>
	<td>mem.getId</td>
</tr>
<tr>
	<th>제목 :</th>
	<td>
	<input type="text" name="title" value="getTitle 작성">
	</td>
</tr>
<tr>
	<th>평점 :</th>
	<td>
	<select name="score">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
	</select>
	</td>
</tr>
<tr>
	<td>
	<textarea rows="20" cols="50px" name="content"></textarea>
	</td>
</tr>
</table>
<button type="button" onclick="writeBbs()">글쓰기완료</button>

<script type="text/javascript">
function writeBbs(seq) {
	location.href = "review.jsp";
}
</script>
	


</body>
</html>