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
<h1>�ı�����</h1>


<table border="1">
<col width="200"><col width="400">
<tr>
	<th>�����̸� ī�װ�</th>
</tr>
<tr>
	<td>����</td>
</tr>
<tr>
	<td>�̸�</td>
	<td>�ۼ���</td>
	<td>��ī��Ʈ</td>
	
</tr>
<tr>
	<td>�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.�������Դϴ�.</td>
</tr>
</table>

<%-- <%
if(dto.getId().equals(mem.getId())){
	%>	
	<button type="button" onclick="updateBbs(<%=dto.getSeq() %>)">����</button>
	<button type="button" onclick="deleteBbs(<%=dto.getSeq() %>)">����</button>
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