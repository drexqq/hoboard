<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<meta charset="UTF-8">
<div class="board-detail news-detail">
	<div class="reply-wrap">
		<div class="reply">
			<form align="center">
				<div style=font-size:20px;>댓글 수정이 완료되었습니다..!</div> 
				<br>
				<input type='button' class="replyupdate"
					onclick='CKBtn()' value='확인' />
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function CKBtn() {
		window.opener.location.reload();
		window.close();
	}
</script>
