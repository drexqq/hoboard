<%@page import="member.Member_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>indvd_Join</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<form action= "INDVD_Join.do" method="post">
이름 	<input type="text" name="name">
<br><br>
아이디 <input type="text" name="id"><button type="button" id="joinBtn">중복확인</button>
<br><br>
비밀 번호 <input type="password" name="pw">
<br><br>
비밀 번호 확인 <input type="password" name="pw_Check">
<br><br>
이메일 <input type="text" name="email">
<br><br>
핸드폰 번호 <input type="text" name="tel">
<br><br>
주소 	<input type="text" name="post_Num">
<br>
 	<input type="text" name="address">
<br> 	
  	<input type="text" name="d_Address">
<br><br>
호보드 이용 약관<br>
<textarea name="clause">
</textarea>
<br><br>
개인 정보 취급 방침<br>
<textarea name="per_Info">
</textarea>
<br><br>
<input type='checkbox' name="s_idBtn" value="s_id" />약관에 동의	
<br><br>
<input type="submit" value="작성 완료">
</form>
</body>
</html>