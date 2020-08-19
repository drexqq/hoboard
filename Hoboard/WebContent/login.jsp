<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="login-wrap">
  <div class="login-form">
    <p class="login-head">&lt;Hoboard /&gt;</p>
    <p class="title">Log in</p>
    <form action="login.do" method="post">
      <div class="input-wrap">
        <label for="id">ID</label>
        <input type="text" name="id" id="id" />
      </div>
      <div class="input-wrap">
        <label for="pw">Password</label>
        <input type="password" name="pw" id="pw" />
      </div>
      <div class="check-wrap">
        <input type="checkbox" name="save_id" id="save_id" value="save_id" />
        <label for="save_id">아이디 저장</label>
      </div>
      <div class="btn-wrap">
        <button class="btn" id="login_btn" value="login">로그인</button>
      </div>
      <div class="util-wrap clearfix">
        <a class="join" href="join.jsp">회원가입</a>
        <a class="find" href="#">아이디 / 비밀번호 찾기</a>
      </div>
    </form>
  </div>
</div>
<script src="js/login.js"></script>
<%@ include file="module/footer.jsp"%>
