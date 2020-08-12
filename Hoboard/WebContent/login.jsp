<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<form action="login.do" method="post">

아이디 <input type="text" name="id" id="id">
<br><br>
비밀 번호 <input type="password" name="pw" id="pw">
<br><br>
<input type='checkbox' name="save_id" id="save_id" value="save_id"/>아이디 저장
<br><br>
<br><br>

<button id="login_btn" value="login">로그인</button>
<button onclick="location.href='join.jsp';">회원 가입</button>
<button onclick="location.href='#';">아이디/비밀번호 찾기</button>

</form>

<script type="text/javascript">
/* $("#login_btn").click(function() {
//	alert('click');
	if( $("#id").val().trim() == "" ){
		alert("id를 입력해 주십시오");
		$("#id").focus();
	}
	else if( $("#pw").val().trim() == "" ){
		alert("password를 입력해 주십시오");
		$("#pw").focus();
	}
	else{
		$("#frm").attr("action", "index.jsp").submit();
	}
	
}); */

</script>


<%@ include file="module/footer.jsp"%>