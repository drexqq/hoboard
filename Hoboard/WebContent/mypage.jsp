<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>

<div class="mypage-wrap">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h2 class="page-title">마이페이지</h2>
      </div>
    </div>
    <div class="box-wrap">
      <div class="row">
        <div class="col-3">
          <a href="mypage/setting.jsp">
            <div class="box">
              <div class="title">개인정보관리</div>
              <div class="icon clearfix">
                <img src="img/mypage_setting.svg" alt="" />
              </div>
            </div>
          </a>
        </div>
        <div class="col-3">
          <a href="">
            <div class="box">
              <div class="title">예약 내역</div>
              <div class="icon clearfix">
                <img src="img/mypage_reserve.svg" alt="" />
              </div>
            </div>
          </a>
        </div>
        <div class="col-3">
          <a href="">
            <div class="box">
              <div class="title">후기 관리</div>
              <div class="icon clearfix">
                <img src="img/mypage_review.svg" alt="" />
              </div>
            </div>
          </a>
        </div>
        <div class="col-3">
          <a href="mypage/ask.jsp">
            <div class="box">
              <div class="title">1:1 문의하기</div>
              <div class="icon clearfix">
                <img src="img/mypage_ask.svg" alt="" />
              </div>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="module/footer.jsp"%>
