<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>

<title>회원 관리</title>

<div>병원 회원</div>
<c:forEach items="${ hmlist }" var="hm" varStatus="status" begin="0" end="9">
<div>${hm.id }${hm.name }${hm.tel }${hm.email }
<input type="button" name="hmdBtn" onclick="location.href='admin?adm=adminBD'" value="상세보기"/>
<input type="button" name="hmdelBtn" onclick="location.href='admin?adm=adminBDel&id=${hm.id}'" value="삭제"/>
</div>
</c:forEach>

<br><br>


<div>개인 회원</div>
<c:forEach items="${ pmlist }" var="pm" varStatus="status" begin="0" end="9">
<div>${pm.id}${pm.name}${pm.tel}${pm.email}
<button class="pmdBtn" onclick="window.open('admin?adm=adminPD&id=${pm.id}','상세 보기',
'width=1000, height=300 ,location=no,status=no,scrollbars=no');">상세 보기</button>
<input type="button" name="pmdelBtn" onclick="location.href='admin?adm=adminIDel&id=${pm.id}'" value="삭제"/>
</div>
</c:forEach>

<%@ include file="module/footer.jsp"%>