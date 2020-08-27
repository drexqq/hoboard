<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>

<title>회원 정보 상세 보기</title>
<div>개인 회원</div>
<div>${dto.id}${dto.name}${dto.tel}${dto.email}${dto.address }${dto.d_Address }
<input type="button" name="pmdBtn" onclick="javascript:self.close();" value="목록보기"/>
<input type="button" name="pmdelBtn" onclick="location.href='admin?adm=adminIDel&id=${dto.id}'" value="삭제"/>
</div>
