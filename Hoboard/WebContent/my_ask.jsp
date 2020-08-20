<%@page import="ask.Ask_Dto"%>
<%@page import="java.util.List"%>
<%@page import="member.Member_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	
<%
//Ask_Dao dao = Ask_Dao.getInstance();
List<Ask_Dto> list = (List<Ask_Dto>)request.getAttribute("list");

int len = (int)request.getAttribute("len");
String searchWord = (String)request.getAttribute("searchWord");
String choice = (String)request.getAttribute("choice");
int pageNumber = (Integer)request.getAttribute("pageNumber");

	//System.out.println("Asklist = "+list.toString());
	System.out.println("Asklen = "+len+" s"+searchWord+" c="+choice+" page "+pageNumber );

%>

<% 	
	System.out.println("AskpageNumber:"+pageNumber);
%>	

<%
//목록 리스트를 검색한것만 가져옴
//10개씩 넘김 
	int AskPage = len/10;
	if(len % 10 > 0){	
		AskPage = AskPage + 1;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my_ask.jsp</title>

<script type="text/javascript">
$(document).ready(function() {
	let searchWord = "<%=searchWord %>";
	if(searchWord == "") return;
		
	let obj = document.getElementById("choice");
	obj.value = "<%=choice%>";
	obj.setAttribute("selected", "selected");	
});
</script>

</head>
<body>
<!-- <form action="Ask_list.jsp" method="get">
<input type="hidden" name="work" value="move">  -->

<h1>Q&A 게시판</h1>
<h4>고객님의 소중한 의견 <%=len%> 건이 등록 돼 있습니다.</h4>

<table border="1">
<col width="70"><col width="600"><col width="150"><col width="150"><col width="150">

<tr>
	<th>번호</th><th>제목</th><th>댓글 수</th><th>작성일자</th>
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
		<tr class="table-row">
			<th><%=i+1 %></th>
			<td>
			<a href="ask.do?one=detail&seq=<%=dto.getSeq()%>">
				<%=dto.getTitle()%>
			</td>
			<td>
				<%=dto.getId()%>
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

<br><br>
<a href="ask_write.jsp">글쓰기</a>
<br><br>

<%
for(int i = 0;i < AskPage; i++){
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

	<select id="choice">
		<option value="sel">----------선택해주세요</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>

	<input type="text" id="search" placeholder="검색어를 입력해주세요"
		value="<%=searchWord%>">
	<button class="btn" onclick="searchAsk()" value="<%=searchWord%>">검색</button>

	<script type="text/javascript">
function searchAsk() {
	let choice = document.getElementById("choice").value;
	let word = document.getElementById("search").value;
//	alert(choice);
//	alert(word);

	/* if(word == ""){
		document.getElementById("search").value = "";
		document.getElementById("choice").value = 'sel';
	} */
	
	location.href = "ask.do?one=search&searchWord="+word+"&choice="+choice;
}
 function goPage( pageNum ) {	
	
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "my_ask.jsp?pageNumber=" + pageNum;
	location.href = "ask.do?one=move&searchWord=" + word + "&choice=" + choice + "&pageNumber=" + pageNum;
} 

<%-- function goPage(obj) {
	alert('?')
	let clickPage = obj;
	let choice = $("#choice").val();
	let search = $("#search").val();
	$.ajax({
		url:"Ask_list.do",
		type:"get",
		datatype:"text",
		data:"work=pageList&pageNumber="+clickPage+"&choice='<%=choice%>'&search='<%=searchWord%>'",
		success:function(obj){
			len = obj.len;
			clen = parseInt(len/10);
			
			if (len%10>0) {
				clen = clen +1;
			}
			let liststr = "";
			let list = obj.list;
			
			if (list.length<=0) {
				liststr += "<tr><td colspan='4' style='text-align: center'>작성된 글이 없습니다</td></tr>"
			}else {
				for (var i = 0; i < list.length; i++) {
					liststr +="<tr class='table-row'>";
					liststr +="<th>"+(i+1)+"</th>";
					liststr +="<td>"+"<a href='Ask_list.do?work=Ask.detail.do&seq="+list[i].seq+"'>"+list[i].title+"</a></td>";
					liststr +="<td align='center'>"+list[i].id+"</td>";
					liststr +="<td align='center'>"+list[i].wdate+"</td>";
					liststr +="</tr>";
					
				}
				if($(".table-row").length>0) $(".table-row").remove();
				
				$(".table-header").eq(-1).after(liststr);
			}
		}
	});
} --%>
</script>


</body>
</html>