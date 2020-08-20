<%@page import="member.Member_Dto"%>
<%@page import="news.news_comm_dto"%>
<%@page import="news.news_comm_dao"%>
<%@page import="news.News_Dao"%>
<%@page import="news.News_Dto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("seq");
int seq = Integer.parseInt(nseq);
System.out.println("detail="+seq);
%>

<% 
News_Dao dao = News_Dao.getInstance();
//dao.readcount(seq);
News_Dto dto = (News_Dto)request.getAttribute("dto");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>건강 정보 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>건강관리</h1>

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>" readonly></td></tr>	
	<tr>	
		<th>날짜</th>
			<td><%=dto.getDate()%></td></tr>
	<tr>
		<th>조회수</th>
			<td><%=dto.getViewcount()%></td></tr>
	<tr>
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>" readonly></td></tr>

</table>
<br>
<button type="button" id="exBtn">전단계</button>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>
<br>
<br>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#updateBtn").click(function () {		
		location.href = "news?work=update&seq=<%=seq%>";
	});
	
	$("#deleteBtn").click(function () {
		location.href = "news?work=del&seq=<%=seq%>";
		alert('삭제 완료');
	});
	
	$("#exBtn").click(function () {		
		location.href = "news?work=move";
	});
	
});

</script>

<%-- <%
String cseq = request.getParameter("c_seq");
int c_seq = Integer.parseInt(cseq);
System.out.println("c_seq="+c_seq);
%> --%>

<%-- <%
/* Member_Dto mem = (Member_Dto)session.getAttribute("sessionID"); */

List<news_comm_dto> clist = (List<news_comm_dto>)request.getAttribute("clist");
System.out.println("clist = "+clist);

news_comm_dao dao2 = news_comm_dao.getInstance();
//dao.readcount(seq);
news_comm_dto dto2 = (news_comm_dto)request.getAttribute("dto2");
System.out.println("dto2 abc= "+dto2);

%> --%>

<% 
news_comm_dao dao2 = news_comm_dao.getInstance();
news_comm_dto dto2 = (news_comm_dto)request.getAttribute("dto2");

List<news_comm_dto> clist = (List<news_comm_dto>)request.getAttribute("clist");

%>

<%
System.out.println(clist);


System.out.println("news comm dto" +dto2);
%>

<%
String ccseq = request.getParameter("c_seq");
int c_seq = Integer.parseInt(ccseq);
System.out.println("c_seq="+c_seq);
%>


<%
if(clist.size() == 0){	// 하나도 없다
	%>
	<tr bgcolor="#f6f6f6">
		<td colspan="3" align="center">고객 리스트가 존재하지 않습니다</td>
	</tr>
	<tr>
		<td height="2" bgcolor="#0000ff" colspan="3"></td>
	</tr>
	<%
}else{	// 있다
	for(int i = 0;i < clist.size(); i++){
		dto2 = clist.get(i);
		%>
		<tr bgcolor="#f6f6f6">
			<td align="center" bgcolor="yellow">
				<input type="checkbox" name="delck" value="<%=dto2.getId() %>">
			</td>		
			<td>
				<%=dto2.getId() %>
			</td>
			<td>
					<%=dto2.getContent()%>
			</td>
		</tr>
		<tr>
			<td height="2" bgcolor="#0000ff" colspan="3"></td>
		</tr>		
		<%
	}
}
%>

<form action="news?work=detail" method="get">
	<input type="hidden" name="b_seq" value="2"/>
	 <%-- <input type="hidden" name="c_seq" value="<%=dto2.getC_seq()%>"/>  --%>
		
		<td><input type="text" name="c_content"/>
			<input type="submit" value="댓글 작성"/>
		</td>

</form>



</body>
</html>
