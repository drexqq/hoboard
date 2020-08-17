<%@page import="Ask.Ask_Dto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	List<Ask_Dto> list = (List<Ask_Dto>)request.getAttribute("list");
	
	System.out.println(list.toString());
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<h1>고객센터</h1>

<table border="1">
<col width="70"><col width="600"><col width="150"><col width="150"><col width="150">

<tr>
<td><input type="text" id="search"></td>
	<select id="choice">
		<option value="sel">----------선택해주세요</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>

<%-- 	<input type="text" id="search" placeholder="검색어를 입력해주세요" value="<%=searchWord%>">
	<button class="btn" onclick="searchBbs()" value="<%=searchWord%>">검색</button> --%>

<tr>
	<th>번호</th><th>제목</th><th>조회수</th><th>작성일자</th>
</tr>
<%
if(list.size() == 0){
	%>
	<tr>
		<td colspan="4">게시 된 글이 없습니다. 글을 작성해주세요.</td>
	</tr>
	
	<%
}else{
	for(int i = 0;i < list.size(); i++){
		Ask_Dto dto = list.get(i);
		%>
		<tr>		
			<td>
				<%=dto.getSeq()%>
			</td>
			<td>
<%-- 			<a href="ask_detail.do?nseq=<%=ndto.getSeq() %>"> --%>
				<%=dto.getTitle()%>
			</td>
			<td>
				<%=dto.getWdate()%>
			</td>
		</tr>	
		<%
	}
}
%>

</table>

</body>
</html>