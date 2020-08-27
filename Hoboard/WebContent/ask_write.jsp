<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>


<div class="board-write ask-write">
	<div class="container">
		<div class="col12">
			<div class="lang" style="font-size: 30px;">Q&A 글쓰기</div>
			<form action="ask?two=write" method="post">
				<input type="hidden" name="id" value="${ sessionScope.sessionID }">
				<div class="title-wrap">
					<div class="title">
						제목<input type="text" name="title" size="50px"> <br>
						<div class="content-title">내용</div>
						<textarea rows="200px" cols="200px" name="content"></textarea>
						<div class="btns-wrap">
							<div>
								<input class="s_write" type="submit" value="글쓰기">
							</div>
							<div>
								<input class="s_del" type="reset" value="초기화">
							</div>
							<div>
								<input class="s_list" type="button" value="글목록"
									onclick="location.href='ask'">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="module/footer.jsp"%>
<!-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask.write.jsp</title>
</head>
<body>
<h1>Q&A 글쓰기</h1>

<form action="ask?two=write" method="post">
<input type="hidden" name="id" value="admin">
<table border="1">
<col width="200"><col width="400">
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="50px">
	</td>
</tr>
<tr>
	<th>파일첨부</th>
	<td>
		<input type="text" name="title" size="50px">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="20" cols="50px" name="content"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		&nbsp;&nbsp;<input type="submit" value="글쓰기">
	</td>
</tr>

</table>

</form>

<a href="my_ask.jsp">글목록</a>
</body>
</html> -->