<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="module/header.jsp"%>
<div class="join-wrap">
	<div class="container">
		<div class="row justify-content-center">
				<div class="join-title">회원가입</div>
			<div class="form-wrap">
				<form action="member" method="post">
					<input type="hidden" name="auth" value="1" />
					<div class="input-wrap clearfix">
						<label for="name">이름</label> <input type="text" id="name"
							name="name" class="textChk" />
					</div>
					<div class="input-wrap btn-added clearfix">
						<label for="id">아이디</label> <input type="text" id="id" name="id"
							class="textChk" />
						<button type="button" class="btn check_dup" data-name="id">check</button>
					</div>
					<div class="input-wrap clearfix">
						<label for="pw">비밀 번호</label> <input type="password" id="pw"
							name="pw" class="textChk" />
					</div>
					<div class="input-wrap clearfix">
						<label for="pw_Check">비밀 번호 확인</label> <input type="password"
							id="pw_Check" name="pw_Check" class="textChk" />
					</div>
					<div class="input-wrap btn-added clearfix">
						<label for="email">이메일</label> <input type="text" id="email"
							name="email" class="textChk" />
						<button type="button" class="btn check_dup" data-name="email">check</button>
					</div>
					<div class="input-wrap clearfix">
						<label for="tel">전화 번호</label> <input type="text" id="tel"
							name="tel" class="textChk" />
					</div>
					<div class="input-wrap address-wrap clearfix">
						<label for="">주소</label>
						<div class="btn btn-secondary find" id="findPostCode">우편번호 찾기</div>
						<input type="text" id="post_Num" name="post_Num" class="textChk address" readonly="readonly" />
						<input type="text" id="address" name="address" class="textChk address" readonly="readonly" />
						<input type="text" id="d_Address" name="d_Address" class="textChk address" />
					</div>
					<div class=#>
						<label for="">Hoboard 이용약관</label>
						<input type="text" id="join_check" class="#" readonly="readonly"/>
					
					</div>
				</form>
			</div>
		</div>
		<div class="btn-wrap">
			<button type="button" class="btn btn-lg" id="joinBtn">회원가입</button>
		</div>
	</div>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/util.js"></script>
<script src="js/form.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="module/footer.jsp"%>
