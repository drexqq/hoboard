<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정 완료 확인 창</title>
</head>
<body>
<h3>댓글 수정이 완료됐습니다.</h3>
<input type='button' onclick='CKBtn()' value='확인'/>

<script type="text/javascript">
function CKBtn() {
window.opener.location.reload();
window.close();  
}


</script>

</body>
</html>