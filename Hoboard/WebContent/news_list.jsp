<%@page import="news.News_Dto"%>
<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@page import="member.Member_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	
<%
//News_Dao dao = News_Dao.getInstance();
List<News_Dto> list = (List<News_Dto>)request.getAttribute("list");

int len = (int)request.getAttribute("len");
String searchWord = (String)request.getAttribute("searchWord");
String choice = (String)request.getAttribute("choice");
int pageNumber = (Integer)request.getAttribute("pageNumber");

	System.out.println("nlist = "+list.toString());
	System.out.println("len = "+len+" s"+searchWord+" c="+choice+" page "+pageNumber );

%>

<% 	
	System.out.println("pageNumber:"+pageNumber);
%>	

<%
//목록 리스트를 검색한것만 가져옴
//10개씩 넘김 
	int NewsPage = len/10;
	if(len % 10 > 0){	
		NewsPage = NewsPage + 1;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>news_list.jsp</title>

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
<!-- <form action="news_list.jsp" method="get">
<input type="hidden" name="work" value="move">  -->

<h1>요즘 많이 찾는 건강 정보</h1>
<h4>건강 정보 총 <%=len%> 건이 등록 돼 있습니다.</h4>

<table border="1">
<col width="70"><col width="600"><col width="150"><col width="150"><col width="150">

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
		News_Dto ndto = list.get(i);
		%>
		<tr class="table-row">
			<th><%=i+1 %></th>
			<td>
			<a href="news_detail.do?nseq=<%=ndto.getNews_seq() %>">
				<%=ndto.getTitle()%>
			</td>
			<td>
				<%=ndto.getViewcount()%>
			</td>
			<td>
				<%=ndto.getDate()%>
			</td>
		</tr>	
		<%
	}
}
%>

</table>

<br><br>
<a href="news_write.jsp">글쓰기</a>
<br><br>

<%
for(int i = 0;i < NewsPage; i++){
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
	<button class="btn" onclick="searchNews()" value="<%=searchWord%>">검색</button>

	<script type="text/javascript">
function searchNews() {
	let choice = document.getElementById("choice").value;
	let word = document.getElementById("search").value;
//	alert(choice);
//	alert(word);

	/* if(word == ""){
		document.getElementById("search").value = "";
		document.getElementById("choice").value = 'sel';
	} */
	
	location.href = "news_list.do?work=search&searchWord="+word+"&choice="+choice;
}
 function goPage( pageNum ) {	
	
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "news_list.jsp?pageNumber=" + pageNum;
	location.href = "news_list.do?work=move&searchWord=" + word + "&choice=" + choice + "&pageNumber=" + pageNum;
} 

<%-- function goPage(obj) {
	alert('?')
	let clickPage = obj;
	let choice = $("#choice").val();
	let search = $("#search").val();
	$.ajax({
		url:"news_list.do",
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
					liststr +="<td>"+"<a href='news_list.do?work=news.detail.do&seq="+list[i].seq+"'>"+list[i].title+"</a></td>";
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