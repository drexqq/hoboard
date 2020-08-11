<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="module/header.jsp"%>
<div class="join-wrap">
	<div class="container">
		<div class="row justify-content-center">
			<div class="form-wrap">
				<form action="MEMBER" method="post">
					<input type="hidden" name="auth" value="2" />
					<div class="input-wrap clearfix">
						<label for="name">병원 이름</label> <input type="text" id="name"
							name="name" />
					</div>
					<div class="input-wrap btn-added clearfix">
						<label for="id">아이디</label> <input type="text" name="id" />
						<button type="button" class="btn" id="id_Check">check</button>
					</div>
					<div class="input-wrap clearfix">
						<label for="pw">비밀 번호</label> <input type="password" id="pw"
							name="pw" /> <br />
						<br />
					</div>
					<div class="input-wrap clearfix">
						<label for="pw_Check">비밀 번호 확인</label> <input type="password"
							id="pw_Check" name="pw_Check" /> <br />
						<br />
					</div>
					<div class="input-wrap clearfix">
						<label for="email">이메일</label> <input type="text" id="email"
							name="email" />
					</div>
					<div class="input-wrap clearfix">
						<label for="tel">전화 번호</label> <input type="text" id="tel"
							name="tel" /> <br />
						<br />
					</div>
					<div class="input-wrap address-wrap clearfix">
						<label for="">주소</label>
						<div class="btn btn-secondary find">우편번호 찾기</div>
						<input type="text" id="post_Num" name="post_Num"  /> <input
							type="text" id="address" name="address"  /> <input
							type="text" id="d_Address" name="d_Address" />
					</div>
					<div class="input-wrap btn-added clearfix">
						<label for="logo">병원로고</label> <input type="text" id="logo"
							name="logo" />
						<button type="button" class="btn" id="dayBtn">파일첨부</button>
					</div>
					<div class="input-wrap clearfix">
						<label for="homepage">홈페이지</label> <input type="text"
							id="homepage" name="homepage" />
					</div>
					
					<div class="input-wrap clearfix">
						<p>진료시간</p>
						<c:forEach items="${ busiTime }" var="time" varStatus="status" begin="0" end="7">
							<div class="time-wrap clearfix">
								<label for="${ time.key }">
									<i class="ri-checkbox-line"></i>
									<span>${ time.value }</span>
								</label>
								<input type="text" id="${ time.key }" name="time${ status.index }" value="" />
							</div>
						</c:forEach>
					</div>
					
					<div class="input-wrap clearfix">
						<p>특수 진료</p>
						<div class="check-wrap clearfix">
							<c:forEach items="${ busiTime }" var="time" varStatus="status" begin="8">
								<div class="check clearfix">
									<input type="checkbox" id="holiday" name="time${ status.index }" value="${ time.key }" />
									<label for="holiday">${ time.value }</label>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<div class="input-wrap clearfix">
						<p>진료 분야</p>
						<div class="check-wrap clearfix">
							<c:forEach items="${ busiCate }" var="cate" varStatus="status">
								<div class="check clearfix">
									<input type="checkbox" id="${ cate.key }" name="cate${ status.index }" value="${ cate.key }" /> 
									<label for="${ cate.key }">${ cate.value }</label>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<div class="input-wrap clearfix">
						<p>편의 시설</p>
						<div class="check-wrap clearfix">
							<c:forEach items="${ busiAmenity }" var="amenity" varStatus="status">
								<div class="check clearfix">
									<input type="checkbox" id="${ amenity.key }" name="amenity${ status.index }" value="${ amenity.key }" />
									<label for="parking">${ amenity.value }</label>
								</div>	
							</c:forEach>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		<div class="btn-wrap">
			<button type="button" class="btn btn-primary btn-lg" id="joinBtn">
				회원가입</button>
		</div>
	</div>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
  // time checkboxes
  $(".time-wrap")
    .find("label")
    .each(function () {
      $(this).on("click", function () {
        $(this).toggleClass("on");
      });
    });
  // submit
  $("#joinBtn").on("click", function () {
    $("form").submit();
  });
</script>
<%@ include file="module/footer.jsp"%>
