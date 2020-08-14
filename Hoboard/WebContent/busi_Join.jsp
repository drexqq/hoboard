<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="join-wrap">
  <div class="container">
    <div class="row justify-content-center">
      <div class="form-wrap">
        <form action="MEMBER" method="post">
          <input type="hidden" name="auth" value="2" />
          <div class="input-wrap clearfix">
            <label for="name">병원 이름</label>
            <input type="text" id="name" name="name" class="textChk" />
          </div>
          <div class="input-wrap clearfix">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" class="textChk" />
            <button type="button" class="btn check_dup" data-name="id">check</button>
          </div>
          <div class="input-wrap clearfix">
            <label for="pw">비밀 번호</label>
            <input type="password" id="pw" name="pw" class="textChk" />
          </div>
          <div class="input-wrap clearfix">
            <label for="pw_Check">비밀 번호 확인</label>
            <input
              type="password"
              id="pw_Check"
              name="pw_Check"
              class="textChk"
            />
          </div>
          <div class="input-wrap clearfix">
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" class="textChk" />
            <button type="button" class="btn check_dup" data-name="email">check</button>
          </div>
          <div class="input-wrap clearfix">
            <label for="tel">전화 번호</label>
            <input type="text" id="tel" name="tel" class="textChk" />
          </div>
          <div class="input-wrap address-wrap clearfix">
            <label for="">주소</label>
            <div class="btn btn-secondary find">우편번호 찾기</div>
            <input type="text" id="post_Num" name="post_Num" class="textChk" />
            <input type="text" id="address" name="address" class="textChk" />
            <input
              type="text"
              id="d_Address"
              name="d_Address"
              class="textChk"
            />
          </div>
          <div class="input-wrap btn-added clearfix">
            <label for="logo">병원로고</label>
            <input type="text" id="logo" name="logo" />
            <button type="button" class="btn" id="dayBtn">파일첨부</button>
          </div>
          <div class="input-wrap clearfix">
            <label for="homepage">홈페이지</label>
            <input type="text" id="homepage" name="homepage" />
          </div>

          <div class="input-wrap clearfix">
            <p>진료시간</p>
            <small
              >요일별 진료시간을 작성하지 않으시면 '휴무'로 지정됩니다.</small
            >
            <small>점심시간은 작성하지 않으시면 '없음'으로 지정됩니다.</small>
            <c:forEach
              items="${ busiTime }"
              var="time"
              varStatus="status"
              begin="0"
              end="7"
            >
              <div class="time-wrap clearfix">
                <label for="${ time.key }">
                  <i class="ri-checkbox-line"></i>
                  <span>${ time.value }</span>
                </label>
                <span class="default-text">
                  <c:if test="${ status.index != 7 }">휴무</c:if>
                  <c:if test="${ status.index == 7 }">없음</c:if>
                </span>
                <input
                  type="text"
                  id="${ time.key }"
                  name="time${ status.index }"
                  class="weekChk"
                  placeholder="09:00~18:00"
                  value=""
                />
              </div>
            </c:forEach>
          </div>

          <div class="input-wrap clearfix">
            <p>특수 진료</p>
            <div class="check-wrap clearfix">
              <c:forEach
                items="${ busiTime }"
                var="time"
                varStatus="status"
                begin="8"
              >
                <div class="check clearfix">
                  <input
                    type="checkbox"
                    id="${ time.key }"
                    name="time${ status.index }"
                    value="${ time.key }"
                  />
                  <label for="${ time.key }">${ time.value }</label>
                </div>
              </c:forEach>
            </div>
          </div>

          <div class="input-wrap clearfix">
            <p>진료 분야</p>
            <div class="check-wrap clearfix">
              <c:forEach items="${ busiCate }" var="cate" varStatus="status">
                <div class="check clearfix">
                  <input
                    type="checkbox"
                    id="${ cate.key }"
                    name="cate${ status.index }"
                    value="${ cate.key }"
                    class="cateChk"
                  />
                  <label for="${ cate.key }">${ cate.value }</label>
                </div>
              </c:forEach>
            </div>
          </div>

          <div class="input-wrap clearfix">
            <p>편의 시설</p>
            <div class="check-wrap clearfix">
              <c:forEach
                items="${ busiAmenity }"
                var="amenity"
                varStatus="status"
              >
                <div class="check clearfix">
                  <input
                    type="checkbox"
                    id="${ amenity.key }"
                    name="amenity${ status.index }"
                    value="${ amenity.key }"
                  />
                  <label for="${ amenity.key }">${ amenity.value }</label>
                </div>
              </c:forEach>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="btn-wrap">
      <button type="button" class="btn btn-primary btn-lg" id="joinBtn">
        회원가입
      </button>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/form.js"></script>
<%@ include file="module/footer.jsp"%>
