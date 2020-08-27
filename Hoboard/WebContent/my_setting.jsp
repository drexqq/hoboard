<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../module/header.jsp"%>

<div class="mypage-wrap">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h2 class="page-title form-title">
					<c:if test="${ user.auth eq 1 }">개인</c:if>
					<c:if test="${ user.auth eq 2 }">병원</c:if>
					정보관리
				</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="join-wrap">
				<div class="form-wrap">
					<form action="mypage" method="post">
						<input type="hidden" name="auth" value="${ user.auth }" />
						<div class="input-wrap clearfix">
							<label for="name">이름</label> <input type="text" id="name"
								name="name" class="textChk" value="${ user.name }"
								readonly="readonly" />
						</div>
						<div class="input-wrap clearfix">
							<label for="id">아이디</label> <input type="text" id="id" name="id"
								class="textChk" value="${ user.id }" readonly="readonly" />
						</div>
						<div class="input-wrap clearfix">
							<label for="pw">비밀 번호</label> <input type="password" id="pw"
								name="pw" class="textChk" />
						</div>
						<div class="input-wrap clearfix">
							<label for="pw_Check">비밀 번호 확인</label> <input type="password"
								id="pw_Check" name="pw_Check" class="textChk" />
						</div>
						<div class="input-wrap clearfix">
							<label for="email">이메일</label> <input type="text" id="email"
								name="email" class="textChk" value="${ user.email }"
								readonly="readonly" />
						</div>
						<div class="input-wrap clearfix">
							<label for="tel">전화 번호</label> <input type="text" id="tel"
								name="tel" class="textChk" value="${ user.tel }" />
						</div>
						<div class="input-wrap address-wrap clearfix">
							<label for="">주소</label>
							<div class="btn btn-secondary find" id="findPostCode">우편번호 찾기</div>
							<input type="text" id="post_Num" name="post_Num" class="textChk address"
								value="${ user.post_Num }" readonly="readonly" />
							<input type="text" id="address" name="address" class="textChk address"
								value="${ user.address }" readonly="readonly" />
							<input type="text" id="d_Address" name="d_Address"
								class="textChk address" value="${ user.d_Address }" />
						</div>
						<c:if test="${ user.auth eq 2 }">
							<div class="input-wrap btn-added clearfix">
								<label for="logo">병원로고</label>
								<input type="text" id="logo" name="logo" value="${ b_user.logo }" />
								<button type="button" class="btn" id="dayBtn">파일첨부</button>
							</div>
							<div class="input-wrap clearfix">
								<label for="homepage">홈페이지</label>
								<input type="text"
									id="homepage" name="homepage" value="${ b_user.homepage }" />
							</div>

							<div class="input-wrap clearfix">
								<p>진료시간</p>
								<small>요일별 진료시간을 작성하지 않으시면 '휴무'로
									지정됩니다.</small> <small>점심시간은 작성하지 않으시면
									'없음'으로 지정됩니다.</small>
								<c:forEach items="${ busiTime }" var="time" varStatus="status"
									begin="0" end="7">
									<div class="time-wrap clearfix">
										<label for="${ time.key[0] }"> <i
											class="ri-checkbox-line"></i> <span>${ time.key[1] }</span>
										</label>
										<span class="default-text">
											<c:if test="${ status.index != 7 }">
												${ time.value }
											</c:if>
											<c:if test="${ status.index == 7 }">없음</c:if>
										</span> <input type="text" id="${ time.key[0] }"
											name="time${ status.index }" class="weekChk"
											placeholder="09:00~18:00" value="${ time.value }" />
									</div>
								</c:forEach>
							</div>

							<div class="input-wrap clearfix">
								<p>특수 진료</p>
								<div class="check-wrap clearfix">
									<c:forEach items="${ busiTime }" var="time" varStatus="status"
										begin="8">
										<div class="check clearfix">
											<input type="checkbox" id="${ time.key[0] }"
												name="time${ status.index }" value="${ time.value }" />
											<label for="${ time.key[0] }">${ time.key[1] }</label>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="input-wrap clearfix">
								<p>진료 분야</p>
								<div class="check-wrap clearfix">
									<c:forEach items="${ busiCate }" var="cate" varStatus="status">
										<div class="check clearfix">
											<input type="checkbox" id="${ cate.key[0] }"
												name="cate${ status.index }" value="${ cate.value }"
												class="cateChk" />
												<label for="${ cate.key[0] }">${ cate.key[1] }</label>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="input-wrap clearfix">
								<p>편의 시설</p>
								<div class="check-wrap clearfix">
									<c:forEach items="${ busiAmenity }" var="amenity"
										varStatus="status">
										<div class="check clearfix">
											<input type="checkbox" id="${ amenity.key[0] }"
												name="amenity${ status.index }" value="${ amenity.value }" />
											<label for="${ amenity.key[0] }">${ amenity.key[1] }</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</form>
				</div>
				<div class="btn-wrap">
					<button type="button" class="btn btn-lg" id="updateBtn">
						수정완료</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-lg" id="delBtn" onclick="location.href='admin?adm=adminDel&id=${user.id}'">
						회원탈퇴</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="js/util.js"></script>
<script src="js/mypage.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ include file="../module/footer.jsp"%>
