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
          <a href="mypage?c=setting">
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
                <a href="REVIEW?review=main&id=fff"><img src="img/mypage_review.svg" alt="" /></a>
              </div>
            </div>
          </a>
        </div>
        <div class="col-3">
          <a href="my_ask.jsp">
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
<script>
$(document).ready(function(){
  var session = "<c:out value='${ sessionID }'/>";
  if(session == null || session == "") {
    alert("로그인이 종료되었습니다. 다시 로그인 해주세요 !");
    location.href = "login.jsp";
  }
})
</script>
<script src="js/mypage.js"></script>
<%@ include file="module/footer.jsp"%>
