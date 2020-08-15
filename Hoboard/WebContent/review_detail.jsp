<%@page import="review.Review_Dto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
Review_Dto dto = (Review_Dto)request.getAttribute("detaillist");


%>

<!-- <script type="text/javascript">
function onDownload(id) {

	var o = document.getElementById("ifrm_filedown");	

	o.src = "REVIEWFILE?id="+id;

}
</script> -->


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>review_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>


<h1>후기정보</h1>


<table style="width:600" border="1">
<col width="300"><col width="500">
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
		<td><input type="button" name="btndown" value="파일"onclick="location.href='file?filename=<%=dto.getFilename() %>&seq=<%=dto.getReview_seq() %>'"></td>
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
	<td>
	<textarea rows="20px" cols="70px" name="content" readonly="readonly"><%=dto.getContent()%></textarea>
	</td>
</tr>
</table>

<%-- <form action="REVIEW" method="get" align="center">
	<input type="hidden" name="review" value="answer">
	<input type="hidden" name="seq" value="<%=dto.getReview_seq() %>">
	<input type="submit" value="답글">
</form> --%>
<%-- <form action="REVIEW" method="get" align="center">
	<input type="hidden" name="review" value="comment">
	<input type="hidden" name="seq" value="<%=dto.getReview_seq() %>">
	<input type="submit" value="댓글">
</form> --%>

<br><br>


<!--TODO Connect session ID -->
<%-- <%
if(dto.getIndvd_id().equals(mem.getId())){
	%>	 --%>
<div align="center">
<button type="button" onclick="updateBbs(<%=dto.getReview_seq() %>)">수정</button>
<button type="button" onclick="deleteBbs(<%=dto.getReview_seq() %>)">삭제</button>
</div>
<%-- 	<%
}
%> --%>


<script type="text/javascript">
function updateBbs(seq) {
	location.href = "review?key=updateview&seq=" + seq;
}

function deleteBbs(seq) {
	location.href = "review?key=delete&seq=" + seq;
}




</script>
	
</body>
</html>