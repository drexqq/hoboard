<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>

<title>회원 정보 상세 보기</title>
<div>개인 회원</div>
<c:forEach items="${ dto }" var="dto" varStatus="status" begin="0" end="9">
<div>${pm.id}${pm.name}${pm.tel}${pm.email}${pm.address }${pm.d_Address }
<input type="button" name="pmdBtn" onclick="location.href='admin?adm=adminPD'" value="목록보기"/>
<input type="button" name="pmdelBtn" onclick="location.href='#'" value="삭제"/>
</div>
</c:forEach>

<%@ include file="module/footer.jsp"%>