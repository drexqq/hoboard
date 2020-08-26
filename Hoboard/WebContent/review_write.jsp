<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<!-- Member_Dto N_list = (Member_Dto)request.getAttribute("businame");
Reserve_Dto C_list = (Reserve_Dto)request.getAttribute("reservecate");
Reserve_Dto R_list = (Reserve_Dto)request.getAttribute("reservelist"); -->
<%
String fname = (new Date().getTime()) + "";
System.out.println("fname:" + fname);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>후기 쓰기</title>
</head>
<body>

<form action="file" method="post" enctype="multipart/form-data">
<input type="hidden" name="indvd_id" value="${ R_list.getIndvd_id() }">
<input type="hidden" name="busi_id" value="${ R_list.getBusi_id() }"> 

<table border="1" align="center">
<col width="100"><col width="300">
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<th>
		병원 카테고리
	</th>
		<td>
			<input type="text" name="cate" value="${ N_list.getName() }-${ C_list.getCate() }" readonly="readonly">
		</td>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<th>
		아이디 :
	</th>
		<td>
			<input type="text" name="id" value="${R_list.getIndvd_id() }" readonly="readonly">
		</td>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<th>
		제목 :
	</th>
		<td>
			<input type="text" name="title" value="">
		</td>
</tr>
<tr>
	<th>
		파일업로드 :
	</th>
		<td>
			<input type="file"  name="filename"  onchange="checkFile(this)"  style="width: 400px">
		</td>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<th>
		평점을 입력해주세요 :
	</th>
		<td>
			<select name="score">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</td>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<td>
		<textarea rows="20px" cols="70px" name="content"></textarea>
	</td>
</tr>
<tr>
	<td align="center" colspan="2">
		<input type="submit" value="후기작성">
		<input type="button" onclick="location='review?key=main&id=${R_list.getIndvd_id()}'" value="후기작성취소하기">
	</td>
</tr>
</table>

<script type="text/javascript">
function checkFile(f){

	// files info
	var file = f.files;

	// file[0].name
	// check
	if(!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) alert('gif, jpg, jpeg, png 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);

	// check true return
	else return;

	// check not img file reset
	f.outerHTML = f.outerHTML;
}


</script>

</form>
</body>
</html>