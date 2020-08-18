<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>review_detail</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<h1>후기정보</h1>
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
<!--TODO Connect session ID -->
	<c:choose>
		<c:when test="${ sessionScope.sessionID == detaillist.indvd_id }">
			<div align="center">
				<button type="button"
					onclick="updateBbs(${detaillist.getReview_seq()})">수정</button>
				<button type="button"
					onclick="deleteBbs(${detaillist.getReview_seq()})">삭제</button>
			</div>
		</c:when>
		<c:when test="${ sessionScope.sessionID != detaillist.indvd_id }">
			<div></div>
		</c:when>
	</c:choose>

	아이디 :
	<c:out value="${sessionScope.sessionID}"></c:out>
	<div class="w3-border w3-padding">댓글</div>
	<div class="w3-border w3-padding">
		<c:if test="${ sessionScope.sessionID == null }">
			<textarea rows="5" cols="50" class="w3-input w3-border newLogin"
				readonly>로그인 후 댓글 달기</textarea>
		</c:if>
		<c:if test="${ sessionScope.sessionID != null }">
			<i class="fa fa-user w3-padding-16"></i>
			<form action="COMM" method="post">
				<input type="hidden" name="seq" id="seq" value="${detaillist.getReview_seq()}"> 
				<input type="hidden" name="no" id="no" value="${detaillist.getReview_seq()}"> 
				<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
					<textarea rows="5" cols="50" class="w3-input w3-border" placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
				<input type="submit" value="댓글등록">
			</form>
		</c:if>
	</div>


	<table border="1">
		<c:forEach items="${ commentlist }" var="list" varStatus="status"
			begin="0" end="10">
			${ fn:length(commentlist) }
			<c:choose>
					<%-- test="${ list.board_no == seq && fn:length(commentlist) == 0 || list == null}" --%>
				<%-- <c:when 
					test="${ fn:length(commentlist) eq 0 }"
					>
					<tr>
						<td colspan="3" align="center">작성된 덧글이 없습니다.</td>
					</tr> 
				</c:when> --%>
				<c:when
					test="${ list.board_no == seq && sessionScope.sessionID == list.id}">
					<tr>
						<th>ID111:</th>
						<td>${list.id}</td>
						<th>작성일:</th>
						<td>${list.date}</td>
						<td>
						<input type="button" onclick="cupdateBbs(${list.seq});" value="수정">
						<input type="button" onclick="cdeleteBbs(${list.seq});" value="삭제">
						</td>
					</tr>
					<tr>
						<th>내용:</th>
						<td>${list.content}</td>
					</tr>
				</c:when>
				<c:when
					test="${ list.board_no == seq && sessionScope.sessionID != list.id}">
					<tr>
						<th>ID:</th>
						<td>${list.id}</td>
						<th>작성일:</th>
						<td>${list.date}</td>
					<tr>
						<th>내용:</th>
						<td>${list.content}</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
	<br>
	<br>


<script type="text/javascript">
function updateBbs(seq) {
	location.href = "review?key=updateview&seq=" + seq;
}

function deleteBbs(seq) {
	location.href = "review?key=delete&seq=" + seq;
}

function cupdateBbs(seq) {
	location.href = "COMM?key=updatecomment&seq=" + seq + "&boardnum=" + ${detaillist.getReview_seq()};
}

function cdeleteBbs(seq) {
	location.href = "COMM?key=deletecomment&seq=" + seq + "&boardnum=" + ${detaillist.getReview_seq()};
}


</script>

</body>
</html>