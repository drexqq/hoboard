<<<<<<< HEAD
<%@page import="ask.Ask_Comm_Dto"%>
<%@page import="ask.Ask_Comm_Dao"%>
<%@page import="ask.Ask_Dto"%>
<%@page import="ask.Ask_Dao"%>
=======
<<<<<<< HEAD
<%@page import="Ask.Ask_Comm_Dto"%>
<%@page import="Ask.Ask_Comm_Dao"%>
<%@page import="Ask.Ask_Dto"%>
<%@page import="Ask.Ask_Dao"%>
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nseq = request.getParameter("seq");
int seq = Integer.parseInt(nseq);
System.out.println("ask_detail"+seq);
%>

<% 
Ask_Dao dao = Ask_Dao.getInstance();
Ask_Dto dto = (Ask_Dto)request.getAttribute("dto");
%> 

<%
Ask_Comm_Dao dao2 = Ask_Comm_Dao.getInstance();
Ask_Comm_Dto dto2 = (Ask_Comm_Dto)request.getAttribute("dto2");

System.out.println("A_C_D = "+ dto2);

%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask_detail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>Q&A 문의에 대한 답변</h1>

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto.getTitle()%>" readonly></td></tr>	
	<tr>	
		<th>날짜</th>
			<td><%=dto.getWdate()%></td></tr>
	<tr>
		<th>조회수</th>
			<td><%=dto.getComm()%></td></tr>
	<tr>
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto.getContent()%>" readonly></td></tr>

</table>

<button type="button" id="exBtn">전단계</button>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>
</form>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#updateBtn").click(function () {	
		location.href = "ask.do?one=update&seq=<%=seq%>";
	});
	
	$("#deleteBtn").click(function () {
		location.href = "ask.do?one=del&seq=<%=seq%>";
		alert('삭제 완료');
	});
	
	$("#exBtn").click(function () {		
		location.href = "ask.do?one=move";
	});
	
});

</script>

<h1>댓글을 입력하세요</h1>
<form action="ask.do?two=c_write&seq=<%=nseq%>" method="post">
 <input type="hidden" name="two" value="c_updateAf"> 
 <input type="hidden" name="nseq" value="<%=dto2.getC_seq()%>">

<table border="1">
	<tr>
		<th>제목</th>
			<td><input type="text" name="title" size="50px" value="<%=dto2.getContent()%>"></td></tr>	
	<tr>	
		<th>내용</th>
			<td><input type="text" name="content" cols="50px" value="<%=dto2.getContent()%>"></td></tr>

</table>


</form>
</body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="mypage-wrap ask-detail">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title-wrap clearfix">
          <h2 class="page-title">${ dto.title } 문의에 대한 답변</h2>
          <div class="info">
            <span class="num">문의 번호 : ${ dto.seq }</span>
            <span class="date">문의 날짜 : ${ dto.wdate }</span>
          </div>
        </div>
        <div class="ask-wrap">
          <div class="ask-head">상세 내용</div>
          <div class="ask-body">${ dto.content }</div>
        </div>
        <div class="comm-wrap">
          <div class="comm-head">
            <i class="ri-customer-service-2-line"></i>답변 내용
          </div>
          <div class="comm-body">
            <c:choose>
              <c:when test="${ dto.comm eq 0 }">
                <div class="result">여기는 문의 답변 완료</div>
              </c:when>
              <c:otherwise>
                <div class="no-result">
                  죄송합니다. 아직 문의에 대한 답변이 없습니다.
                </div>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="btn-wrap">
          <a href="ask">문의 목록</a>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="module/footer.jsp"%>
>>>>>>> 5c33b9fc7420f2884ec458355cc5ad91088fc9e7
