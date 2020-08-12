<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
String id = (String) session.getAttribute("sessionID");

if(id == null){
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
	<%
}
--%> 



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
</head>
<body>

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
</body>

<script type="text/javascript">
var name = "${ name }";
if(name != null && name != "") {
	alert(name+"님 ㅎㅇ");
	location.href = "index.jsp";
}

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


</html>