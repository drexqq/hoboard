<%@page import="Reserve.Reserve_Dto"%>
<%@page import="member.Member_Dto"%>
<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>

<%@ include file="module/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<%-- <%
Object ologin = session.getAttribute("login");
Member_Dto mem = null;
if(ologin == null){
	%>
	<script type="text/javascript">
	alert("로그인 할 수 없습니다.");
	location.href = "login.jsp";
	</script>
	<%
}
mem = (Member_Dto)ologin;

%> --%>

<%-- //�˻�
<%

String searchWord = request.getParameter("searchWord");
String choice = request.getParameter("choice");

if(choice == null || choice.equals("")){
	choice = "sel";
}
// �˻�� �������� �ʰ� Choice�� �Ѿ� ���� ��
if(choice.equals("sel")){
	searchWord = "";
}
if(searchWord == null){
	searchWord = "";
	choice = "sel";
}

%> --%>



<%
List<Review_Dto> list = (List<Review_Dto>)request.getAttribute("reviewlist");
%>

<input type="text" name="text"><button>검색</button> 
&nbsp;&nbsp;&nbsp;
<select id="sel">
	<option value="1" selected="selected">선택</option>
	<option value="2">작성자</option>
	<option value="3">병원이름</option>
	<option value="4">제목</option>
	<option value="5">내용</option>	
	<option value="6">평점</option>
</select>
<br><br>



<table style="width: 700">
<col width="100"><col width="300"><col width="300">
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>

	<th>ID</th>
	<th>CONTENT</th>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<%
if(list == null || list.size() == 0){
	%>
	<tr>
		<td colspan = "3" align="center">작성된 후기가 없습니다.</td>
	</tr>
<%
}else{
	for(int i=0; i < list.size(); i++){
		Review_Dto rev = list.get(i);
	%>

<tr>
	<th>[병원이름]</th><th><%=rev.getTitle()%></th><td><a href="review_detail.jsp&=<%=rev.getReview_seq()%>"><%=rev.getContent()%></a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th>작성자</th><td><%=rev.getIndvd_id()%></td>
	<!-- W_DATE     --> <th>작성일</th><td><%=rev.getW_date() %></td>
	<!-- VIEW COUNT --> <th>조회수</th><td><%=rev.getViewcount() %></td>
	<!-- SCORE      --> <th>평점</th><td><%=rev.getScore() %></td>
</tr>
<tr>
	<td height="2" bgcolor="#000fff" colspan="3"></td>
</tr>
	<%
	}
}
%>
</table>
<br>
<br>

<% //TODO after scriptlet session ID add change %>
<button type="button" id="getId" onclick="writeBtn()" value="<%="fff"%>">글쓰기</button>


<script type="text/javascript">
function writeBtn(){
	
	
	let ID = document.getElementById("getId").value;
	
	//TODO dummy
	let BUSI_ID = "hanbang";
	let seq = "7"; 

	location.href="REVIEW?review=writeView&id=" + ID + "&busi=" + BUSI_ID + "&seq=" + seq;
}
</script>

<%@ include file="module/footer.jsp"%>