<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%-- //세션로그인
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
	<%
}

mem = (MemberDto)ologin;
%> --%>


<%
List<Review_Dto> list = (List<Review_Dto>)request.getAttribute("reviewlist");

System.out.println(list);

%>



<%-- //검색
<%

String searchWord = request.getParameter("searchWord");
String choice = request.getParameter("choice");

if(choice == null || choice.equals("")){
	choice = "sel";
}
// 검색어를 지정하지 않고 Choice가 넘어 왔을 때
if(choice.equals("sel")){
	searchWord = "";
}
if(searchWord == null){
	searchWord = "";
	choice = "sel";
}

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>후기 작성 게시판</title>
</head>
<body>
<input type="text" name="text"><button>검색</button> 
&nbsp;&nbsp;&nbsp;
<select id="sel">
	<option value="1" selected="selected">선택</option>
	<option value="2">제목</option>
	<option value="3">병원이름</option>
	<option value="4">작성자</option>
	<option value="5">내용</option>	
	<option value="6">평점</option>
</select>
<br><br>

리스트 확인
<table border="1">
<%-- <%
for(int i=0; i<5; i++){
%>
<table border="1">
<col width="70"><col width="70"><col width="70">
<tr>
	<th>[=busi_name%>병원이름]</th><th>TITLE</th><td><a href="review_detail.jsp">베지밀한끼두유</a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th>name</th><td>홍길..</td>
	<!-- W_DATE     --> <td>17:00</td>
	<!-- VIEW COUNT --> <td>5회</td>
	<!-- SCORE      --> <td>4.5평점</td>
</tr>	

<%
}
%> --%>

<%-- <%
if(list.size() == 0){
	%>
	<tr>
		<td colspan = "3" align="center">작성된 후기가 존재하지 않습니다.</td>
	</tr>
<%
}else{
	for(int i=0; i < list.size(); i++){
		Review_Dto rev = list.get(i);
	%>

<tr>
	<th><%=rev.getBusi_id()%></th><th><%=rev.getTitle()%></th><td><a href="review_detail.jsp"><%=rev.getContent()%></a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th><%=rev.getIndvd_id()%></th>
	<!-- W_DATE     --> <td><%=rev.getW_date() %></td>
	<!-- VIEW COUNT --> <td><%=rev.getViewcount() %></td>
	<!-- SCORE      --> <td><%=rev.getScore() %></td>
</tr>

	<%
	}
}
%> --%>

</table>

<!-- TODO -->
<button type="button" onclick="writeBbs(<%-- <%=dao.getSeq() %> --%>)">글쓰기</button>

<script type="text/javascript">
function writeBbs() {
	location.href = "REVIEW?review=writeView";
}

</script>
	

</body>
</html>