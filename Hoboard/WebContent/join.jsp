<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex_Join</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<button type="button" id="i_JoinBtn">개인</button>
<button type="button" id="b_Joinbtn">병원</button>

<script type="text/javascript">

$("#i_JoinBtn").click(function() {
	//alert('click');
	location.href = "INDVD_JOIN";
});
$("#b_Joinbtn").click(function() {
	//alert('click2');
	location.href = "BUSI_JOIN"
});	

</script>

</body>
</html>