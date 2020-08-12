<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>review_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>후기정보</h1>


<table border="1">
<col width="200"><col width="400">
<tr>
	<th>병원이름 카테고리</th>
</tr>
<tr>
	<td>제목</td>
</tr>
<tr>
	<td>이름</td>
	<td>작성일</td>
	<td>뷰카운트</td>
	
</tr>
<tr>
	<td>컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.컨텐츠입니다.</td>
</tr>
</table>

<%-- <%
if(dto.getId().equals(mem.getId())){
	%>	
	<button type="button" onclick="updateBbs(<%=dto.getSeq() %>)">수정</button>
	<button type="button" onclick="deleteBbs(<%=dto.getSeq() %>)">삭제</button>
	<%
}
%> --%>


<script type="text/javascript">
function updateBbs(seq) {
	location.href = "review_update.jsp";
}

function deleteBbs(seq) {
	location.href = "review.jsp"; 	/*"REVIEW?seq=" + seq */
}



</script>
	
</body>
</html>