<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="mypage-wrap">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h2 class="page-title form-title">개인회원상세정보</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="join-wrap">
				<div class="form-wrap">
					<div class="input-wrap clearfix">
						<label for="name">이름</label> <input type="text" id="name"
							name="name" class="textChk" value="${ dto.name }"
							readonly="readonly" />
					</div>
					<div class="input-wrap clearfix">
						<label for="id">아이디</label> <input type="text" id="id" name="id"
							class="textChk" value="${ dto.id }" readonly="readonly" />
					</div>
					<div class="input-wrap clearfix">
						<label for="email">이메일</label> <input type="text" id="email"
							name="email" class="textChk" value="${ dto.email }"
							readonly="readonly" />
					</div>
					<div class="input-wrap clearfix">
						<label for="tel">전화 번호</label> <input type="text" id="tel"
							name="tel" class="textChk" readonly="readonly"  value="${ dto.tel }" />
					</div>
					<div class="input-wrap address-wrap clearfix">
						<label for="">주소</label>
						<input type="text" id="post_Num" name="post_Num"
							class="textChk address" value="${  dto.post_Num }"
							readonly="readonly" /> <input type="text" id="address"
							name="address" class="textChk address" value="${ dto.address }"
							readonly="readonly" /> <input type="text" id="d_Address"
							name="d_Address" class="textChk address"
							value="${ dto.d_Address }" />
					</div>
				</div>
				<div class="btn-wrap">
					<button type="button" class="btn btn-lg" id="turnBtn"
						onclick="location.href='admin?adm=adminM'">글목록으로</button>
				</div>
			</div>
		</div>
	</div>
</div>




<%@ include file="module/footer.jsp"%>
