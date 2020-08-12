<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="join-wrap">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-10">
        <div class="link-wrap wrap">
          <div class="indvd-link wrap">
            <div class="box">
              <div class="icon">
                <img src="img/indvd_join.png" alt="INDVD" />
              </div>
              <div class="text">개인 회원</div>
              <div class="desc">
                Hoboard에 등록된 병원에<br />
                예약할 수 있는 개인 회원으로 가입합니다.
              </div>
            </div>
            <a class="link btn" href="INDVD_JOIN">개인 회원 가입</a>
          </div>
          <div class="busi-link wrap">
            <div class="box">
              <div class="icon">
                <img src="img/busi_join.png" alt="BUSI" />
              </div>
              <div class="text">병원 회원</div>
              <div class="desc">
                Hoboard에 병원을 등록합니다.<br />
                회원들로부터 예약을 받을 수 있습니다.
              </div>
            </div>
            <a class="link btn" href="BUSI_JOIN">병원 회원 가입</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ include file="module/footer.jsp"%>
