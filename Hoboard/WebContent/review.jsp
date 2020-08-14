<%@page import="review.Review_Dao"%>
<%@page import="Reserve.Reserve_Dto"%>
<%@page import="member.Member_Dto"%>
<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>

<%@ include file="module/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<%

%>



<%

System.out.println("넘어가주세요");

String searchWord = (String)request.getAttribute("searchWord");
String choice = (String)request.getAttribute("choice");
System.out.println("searchWord 확인 : " + "[" + searchWord + "] " + "choice 확인 :" + " " + "["+ choice + "]");
//int P_pageNumber = (int)request.getParameter("pageNumber");
//int P_page = (int)request.getParameter("page");
String P_pageNumber = String.valueOf(request.getAttribute("pageNumber"));
String P_Review_Page = String.valueOf(request.getAttribute("page"));

System.out.println(request.getAttribute("pageNumber"));
System.out.println(request.getAttribute("page"));

int pageNumber = Integer.parseInt(P_pageNumber);
int Review_Page = Integer.parseInt(P_Review_Page);





//int pageNumber = Integer.parseInt("P_pageNumber");
//int Review_Page = Integer.parseInt("P_page");


//List<Review_Dto> list = (List<Review_Dto>)request.getAttribute("reviewlist");
List<Review_Dto> list = (List<Review_Dto>)request.getAttribute("paginglist");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>후기 게시판</title>
</head>
<body>

<script type="text/javascript">
$(document).ready(function() {
	let searchWord = "<%=searchWord%>";
	if(searchWord == "") return;
		
	let obj = document.getElementById("choice");
	obj.value = "<%=choice%>";
	obj.setAttribute("selected", "selected");	
});
</script>

<input type="text" id="search" value="<%-- <%=searchWord%> --%>"><button onclick="searchBbs()">검색</button>
&nbsp;&nbsp;&nbsp;
<select id="choice">
	<option value="sel" selected="selected">선택</option>
	<option value="indvd_id">작성자</option>
	<option value="busi_cate">병원이름</option>
	<option value="title">제목</option>
	<option value="content">내용</option>	
	<option value="score">평점</option>
</select>
<br><br>



<table style="width:700">
<col width="300"><col width="300"><col width="350">
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



<%
if(rev.getDel() == 0){
%>
<tr>
	<%-- <%=arrow( rev.getDepth() ) %> --%>			
	<th>[<%=rev.getBusi_cate()%>]</th>	
	<th><%=rev.getTitle()%></th>
	<td><a href="REVIEW?review=detail&seq=<%=rev.getReview_seq()%>"><%=rev.getContent()%></a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th>작성자</th><td><%=rev.getIndvd_id()%></td>
	<!-- WDATE     -->  <th>작성일</th><td><%=rev.getWdate() %></td>
	<!-- VIEW COUNT --> <th>조회수</th><td><%=rev.getViewcount() %></td>
	<!-- SCORE      --> <th>평점</th><td><%=rev.getScore() %></td>
</tr>	
	
<%
}else{
%>	
<%
}
%>
<tr>
	<td height="2" bgcolor="#000fff" colspan="3"></td>
</tr>
	<%
	}
}
%>
</table>

<%
for(int i = 0;i < Review_Page; i++){
	if(pageNumber == i){	// 1 [2] [3] 
		%>
		<span style="font-size: 15pt; color: #0000ff; font-weight: bold;">
			<%=i+1 %>
		</span>&nbsp;
		<%
	}
	else{	// 그외 페이지
		%>
		<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)" 
			style="font-size: 15pt; color: #000; font-weight:bold;  text-decoration: none">
			[<%=i+1 %>]
		</a>&nbsp;		
		<%
	}	
}
%>






<br>
<br>

<% //TODO after scriptlet session ID add change %>
<button type="button" id="getId" onclick="writeBtn()" value="<%="fff"%>">글쓰기</button>
<!-- after -> reserve button  -->
<script type="text/javascript">
function writeBtn(){
	
	
	let ID = document.getElementById("getId").value;
	
	//TODO dummy
	let BUSI_ID = "hanbang";
	let seq = "7"; 
	
	//reserve status="3" select button "show" else
	//after id , busi_id , seq must
	location.href="REVIEW?review=writeview&id=" + ID + "&busi=" + BUSI_ID + "&seq=" + seq;
}


</script>

<!-- 검색버튼  -->
<script type="text/javascript">
function searchBbs() {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;


    if(word == ""){
		document.getElementById("search").value = "";
		document.getElementById("choice").value = 'sel';
	} 
	
	location.href = "REVIEW?review=main&searchWord=" + word + "&choice=" + choice;
}

function goPage( pageNum ) {	
	
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "REVIEW?review=main&searchWord=" + word + "&choice=" + choice + "&pageNumber=" + pageNum;
}



</script>

<%@ include file="module/footer.jsp"%>