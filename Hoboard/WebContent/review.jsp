<%@page import="review.Review_Dao"%>
<%@page import="Reserve.Reserve_Dto"%>
<%@page import="member.Member_Dto"%>
<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="module/header.jsp"%>
<h1>리뷰리스트</h1>
<div>
<input type="text" id="search" value=""><button onclick="searchBbs()">검색</button>
<select id="choice">
	<option value="id" selected="selected">작성자</option>
	<option value="busi_name">병원이름</option>
	<option value="title">제목</option>
	<option value="content">내용</option>	
	<option value="score">평점</option>
</select>
</div>
<div>${ page } = page</div>
<div>${ pageNumber } = pageNumber</div>
<div>${ limit } = limit</div>
<div>${ pageNumber * limit } = pageNumber * limit</div>
<br><br>
<c:choose>
 
    <c:when test="${len eq 0}">
       <div>검색 결과가 없습니다.</div>
    </c:when>
 
    <c:otherwise>
    
    <c:forEach items="${ reviewlist }" var="list" varStatus="status" begin="0" end="4">
		<div>
			<div>${ list.title }</div>
			<div>${ list.content }</div>
			<div>${ list.viewcount }</div>
			<div>${ list.score }</div>
		</div>
		</c:forEach>
        <c:forEach items="${ reviewlist }" var="page" varStatus="status" begin="0" end="${ page }">
			<a onclick="goPage(${ status.index })">${ status.index }</a>
		</c:forEach>
    </c:otherwise>
 
</c:choose>

<!-- 검색버튼  -->
<script type="text/javascript">
function searchBbs() {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;

    if(word == ""){
		document.getElementById("search").value = "";
	}
    
	location.href = "review?searchWord=" + word + "&choice=" + choice;
}

function goPage( pageNum ) {	
	
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "review?page=" + pageNum;
}

</script>
<%-- 

<script type="text/javascript">
$(document).ready(function() {
	let searchWord = "<%=searchWord%>";
	if(searchWord == "") return;
		
	let obj = document.getElementById("choice");
	obj.value = "<%=choice%>";
	obj.setAttribute("selected", "selected");	
});
</script>


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
			style="font-size: 15pt; color: #000; font-weight:bold;  text-decoration none">
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
 --%>

<%@ include file="module/footer.jsp"%>