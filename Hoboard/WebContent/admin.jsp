<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>



<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr>
<th colspan="3">Hoboard 운영 현황</th>
</tr>
<tr>
<td>예약 현황 보기</td>
<td>()건</td>
<td><input type="button" name="resBtn" value="바로가기"/></td>
</tr>
<tr>
<td>회원 관리</td>
<td>회원 수()</td>
<td><input type="button" name="memBtn" value="바로가기"/></td>
</tr>
<tr>
<td>Q&A 관리</td>
<td>답변 대기 ()건</td>
<td><input type="button" name="qnaBtn" onclick="location.href='ask'" value="바로가기"/></td>
</tr>
<tr>
<td>이용 후기 관리</td>
<td>총 후기 ()건</td>
<td><input type="button" name="revBtn" onclick="location.href='review'" value="바로가기"/></td>
</tr>
<tr>
<td>건강 정보 게시판 관리</td>
<td>건강 정보 ()건</td>
<td><input type="button" name="newsBtn" onclick="location.href='news'"value="바로가기"/></td>
</tr>
</table>

<br><br>



<table border="1">
<tr>
<th colspan="3">Q&A 게시판</th>
<td><input type="button" name="qnaBtn" onclick="location.href='ask'"value="바로가기"/></td>


<br><br>


<div>최신 이용 후기</div>
<div><input type="button" name="revBtn" onclick="location.href='review'" value="바로가기"/></div>
<c:forEach items="${ rlist }" var="rlist" varStatus="status" begin="0" end="5">
<div>${rlist.wdate}${rlist.indvd_id}${rlist.title}${rlist.score}</div>
</c:forEach>


<br><br>


<div>건강 정보 게시판</div>
<div><input type="button" name="newsBtn" onclick="location.href='news'" value="바로가기"/></div>

<!-- 원글 -->
<c:forEach items="${ nlist }" var="nlist" varStatus="status" begin="0" end="0">
<div>${nlist.date}${nlist.id}${nlist.title}</div>
</c:forEach>
<!-- 댓글 -->
<c:forEach items="${ clist }" var="clist" varStatus="status" begin="0" end="2">
<div>${clist}</div>
</c:forEach>

<!-- 원글 -->
<c:forEach items="${ nlist }" var="nlist" varStatus="status" begin="1" end="1">
<div>${nlist.date}${nlist.id}${nlist.title}</div>
</c:forEach>
<!-- 댓글 -->
<c:forEach items="${ clist }" var="clist" varStatus="status" begin="0" end="2">
<div>${clist}</div>
</c:forEach>

<!-- 원글 -->
<c:forEach items="${ nlist }" var="nlist" varStatus="status" begin="2" end="2">
<div>${nlist.date}${nlist.id}${nlist.title}</div>
</c:forEach>
<!-- 댓글 -->
<c:forEach items="${ clist }" var="clist" varStatus="status" begin="0" end="2">
<div>${clist}</div>
</c:forEach>


<%@ include file="module/footer.jsp"%>