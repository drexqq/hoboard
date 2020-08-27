<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<meta charset="UTF-8">
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.form{
 position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 550px;
  margin: 60px auto 100px;
  padding: 30px;
  text-align: center;
 box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 350px;
  height: 15px;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}

.ta {
padding: 10%;
text-align:center;
 width: 500px;
 height: 100px;
}

.ta th{
font-size: 17px;
height: 50px;
text-align: center;
}
.bbtn {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #fd7e14;;
  width: 100px;
  border: 0;
  padding: 10px;
  color: #FFFFFF;
  font-size: 16px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

</style>

<head>
<meta charset="utf-8">
<title>아이디/비밀번호 찾기</title>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>


<!-- body--------------------------------------------------------------------------------------- -->

<div class="form">
<form action="find?idpw=findID&name=${dto.name}&email=${dto.email}" method="post">
<table class="ta">
	<tr>
		<H1>아이디 찾기</H1>  
	</tr>
	<tr>
		<th>이  름 </th><td><input type="text" id="name" name="name"></td>
 	</tr>
 	<tr>
 		<th>메  일 <th><input type="text" id="email" name="email"></td>
 	</tr>
 	<tr>
 		<td colspan="2"><button type="submit" class="bbtn">확  인</button></td>
	</tr>
</form>
 </table>
 
</div>

<hr>

<div class="form">
<form action="find?idpw=findPW" method="post">
<table class="ta">
	<tr>
		<H1>비밀번호 찾기</H1>  
	</tr>
	<tr>
		<th>아이디 </th><td><input type="text" id="id" name="id"></td>
 	</tr>
 	<tr>
 		<th>이  름 <th><input id="name" name="name"></td>
 	</tr>
 	<tr>
 		<td colspan="2"><button type="submit" class="bbtn">확  인</button></td>
	</tr>
 </table>
 </form>
 </div> 
<!-- footer----------------------------------------------------------------------------------------->
<%@ include file="module/footer.jsp"%>
<!-- ----------------------------------------------->