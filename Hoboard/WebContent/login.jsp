<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<input type='checkbox' name="save_Id" id="save_Id" value="save_Id"/>아이디 저장
<br><br>
<input type="submit" value="로그인">
<br><br>
<button type="button" id="joinBtn">회원 가입</button>
<button type="button" id="searchBtn">아이디/비밀번호 찾기</button>

</form>

</body>

</html>