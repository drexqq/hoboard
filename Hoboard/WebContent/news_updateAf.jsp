<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbswriteAf</title>
</head>
<body>
<%
	System.out.println("updateAf.jsp");
	boolean result = (boolean)request.getAttribute("isS");
	System.out.println("isS="+result);
	if(result){
%>
	<script type="text/javascript">
		alert("글이 수정되었습니다");
		location.href = "news_list.do?work=move";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("수정되지 않았습니다");
		location.href = "news_list.do?work=move";
	</script>
<%
	}
%>

</body>
</html>