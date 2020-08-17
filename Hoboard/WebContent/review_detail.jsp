<%@page import="review.Review_Dto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	Review_Dto dto = (Review_Dto) request.getAttribute("detaillist");
%>


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


	<table style="width: 600" border="1">
		<col width="300">
		<col width="500">
		<tr>
			<th>병원 카테고리</th>
			<td><%=dto.getBusi_cate()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=dto.getIndvd_id()%></td>
		</tr>
		<tr>
			<th>파일다운로드</th>
			<td><input type="button" name="btndown" value="파일"
				onclick="location.href='file?filename=<%=dto.getFilename()%>&seq=<%=dto.getReview_seq()%>'"></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=dto.getWdate()%></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=dto.getViewcount()%></td>
		</tr>
		<tr>
			<td><textarea rows="20px" cols="70px" name="content"
					readonly="readonly"><%=dto.getContent()%></textarea></td>
		</tr>




	</table>
	
<div class="w3-border w3-padding">댓글</div>
			<div class="w3-border w3-padding">
				<c:if test="${ sessionScope.sessionID == null }">
					<textarea rows="5" cols="50" class="w3-input w3-border newLogin" readonly>로그인 후 댓글 달기</textarea>
				</c:if>
				<c:if test="${ sessionScope.sessionID != null }">
					<i class="fa fa-user w3-padding-16"></i>
					<form>
						<input type="hidden" name="no" id="no" value="<%=dto.getReview_seq()%>"> 
						<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
						<textarea rows="5" cols="50" class="w3-input w3-border" placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
						<input type="button" class="w3-button w3-border" id="reply_btn" onclick="replybtn()" value="댓글 등록">
					</form>
				</c:if>
			</div>

<%-- 
<div class="w3-border w3-padding">댓글</div>
			<div class="w3-border w3-padding">
				<c:if test="${ sessionScope.sessionID == null }">
					<textarea rows="5" cols="50" class="w3-input w3-border newLogin" readonly>로그인 후 댓글 달기</textarea>
				</c:if>
				<c:if test="${ sessionScope.sessionID != null }">
					<i class="fa fa-user w3-padding-16"></i> ${ content.id }
					<form>
						<input type="hidden" name="no" id="no" value="${ dto.review_seq }"> 
						<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
						<textarea rows="5" cols="50" class="w3-input w3-border" placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
						<input type="button" class="w3-button w3-border" id="reply_btn" value="댓글 등록">
					</form>
				</c:if>
			</div>
	 --%>
<br>
<br>


<!--TODO Connect session ID -->

	
<c:if test="${ sessionScope.sessionID == dto.indvd_id }">	
	<div align="center">
		<button type="button" onclick="updateBbs(<%=dto.getReview_seq()%>)">수정</button>
		<button type="button" onclick="deleteBbs(<%=dto.getReview_seq()%>)">삭제</button>
	</div>
</c:if>


<script type="text/javascript">
function updateBbs(seq) {
	location.href = "review?key=updateview&seq=" + seq;
}

function deleteBbs(seq) {
	location.href = "review?key=delete&seq=" + seq;
}

function 


/* 
$("#reply_btn").click(function(){
	if($("#reply_content").val().trim() === ""){
		alert("댓글을 입력하세요.");
		$("#reply_content").val("").focus();
	}else{
		$.ajax({
			url: "/COMM/write",
            type: "POST",
            data: {
                no : $("#no").val(),
                id : $("#id").val(),
                reply_content : $("#reply_content").val()
            },
            success: function () {
            	alert("댓글 등록 완료");
            	$("#reply_content").val("");
            	getReply();
            },
		})
	}
})
 */





</script>

</body>
</html>