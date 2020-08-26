<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>



<title>관리자 페이지</title>

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
<td>병원 회원 (${ fn:length(hmlist) })명 /개인 회원 (${ fn:length(pmlist) })명</td>
<td><input type="button" name="memBtn" onclick="location.href='admin?adm=adminM'" value="바로가기"/></td>
</tr>
<tr>
<td>Q&A 관리</td>
<td>답변 대기 (${ fn:length(rlist) })건</td>
<td><input type="button" name="qnaBtn" onclick="location.href='ask'" value="바로가기"/></td>
</tr>
<tr>
<td>이용 후기 관리</td>
<td>작성 된 후기 (${ fn:length(rlist) })건</td>
<td><input type="button" name="revBtn" onclick="location.href='review'" value="바로가기"/></td>
</tr>
<tr>
<td>건강 정보 게시판 관리</td>
<td>건강 정보 (${ fn:length(nlist) })건</td>
<td><input type="button" name="newsBtn" onclick="location.href='news'"value="바로가기"/></td>
</tr>
</table>

<br><br>

<div>Q&A 게시판</div>
<div><input type="button" name="qnaBtn" onclick="location.href='ask'" value="바로가기"/></div>
<c:forEach items="${ qlist }" var="qlist" varStatus="status" begin="0" end="4">
<div>${qlist.wdate}${qlist.id}${qlist.title}</div>
</c:forEach>

<br><br>


<div>최신 이용 후기</div>
<div><input type="button" name="revBtn" onclick="location.href='review'" value="바로가기"/></div>
<c:forEach items="${ rlist }" var="rlist" varStatus="status" begin="0" end="4">
<div>${rlist.wdate}${rlist.indvd_id}${rlist.title}${rlist.score}</div>
</c:forEach>


<br><br>


<div>건강 정보 게시판</div>
<div><input type="button" name="newsBtn" onclick="location.href='news'" value="바로가기"/></div>

<!-- 원글 -->
<c:forEach items="${ nlist }" var="nlist" varStatus="status" begin="0" end="4">
<div>${nlist.date}${nlist.id}${nlist.title}</div>
</c:forEach>



<%@ include file="module/footer.jsp"%>