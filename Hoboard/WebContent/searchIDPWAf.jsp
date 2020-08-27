<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.container{
 width: 800px;
  padding: 25% 0 0;
  margin: auto;
 background-image : url('img/YH/02.jpg');   /* 배경 이미지 불러오기 */
  background-repeat : no-repeat;  /* 반복 안함 */
  background-size : cover;
}

.div{
position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 550px;
  margin: 220px auto 210px;
  padding: 30px;
  text-align: center;
 box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}

/* .form{
  margin: 60px auto 100px;
  padding: 30px;
  text-align: center;
} */

.ta {
text-align:center;
 width: 500px;
 height: 200px;
}

.ta th{
font-size: 17px;
height: 50px;
text-align: center;
}

.bbtn{
 height:40px;
 font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #0D6400;
  width: 120px;
  border: 0;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

</style>
<head>
<meta charset="utf-8">
<title>HEALTHY & HAPPY</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

 <%
 String id = (String)request.getAttribute("id");
 System.out.println("가져온 아이디:"+id);

 String pwd = (String)request.getAttribute("pwd");
 System.out.println("가져온 비밀번호:"+pwd);
 %>

<!-- body--------------------------------------------------------------------------------------- -->
<div class="container">
<div class="form">
<%
if(pwd == null){
%>
<div class="div">
<table class="ta">
<col><col>
	<tr>
		<th colspan="2">아이디를 찾았습니다!</th>
	</tr>
	<tr>
		<th colspan="2" >아&nbsp;이&nbsp;디&nbsp;:&emsp;[&emsp;<%=id %>&emsp;]&emsp;</th>
	</tr>
	<tr>
		<th><button class="bbtn" onclick="location.href='Yhidpwd.jsp'">비밀번호 찾기</button></th>
		<th><button class="bbtn" onclick="location.href='Yhlogin.jsp'">로그인 하러가기</button></th>
	</tr>
</table>
</div>
<%}else{%>
<div class="div">
<table class="ta">
<col><col>
	<tr>
		<th colspan="2">비밀번호를 찾았습니다!</th>
	</tr>
	<tr>
		<th>비&nbsp;밀&nbsp;번&nbsp;호&nbsp;:&emsp; [&emsp; <%=pwd %> &emsp; ]</th>
	</tr>
	<tr>
		<th><button class="bbtn" onclick="location.href='Yhlogin.jsp'">로그인 하러가기</button></th>
	</tr>
</table>
</div>
</div>
<%	
}
%>



</div>    
<!-- footer--------------------------------------------------------------------------------------- -->
<%@ include file="module/footer.jsp"%>
<!-- ----------------------------------------------->
</body>
</html>