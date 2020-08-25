<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="board-write review-write">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<c:forEach items="${ reviewDto }" var="item">
					<form action="review" method="post">
						<input type="hidden" id="seq" name="seq" value="${ param.seq }">
						<div class="title-wrap">
							<div class="cate-name">[${ item.key }] - [${ item.value.busi_cate }]</div>
							<div class="title">
								<input id="title" name="title" type="text"
									value="${ item.value.title }" placeholder="제목을 입력해주세요">
							</div>
							<div class="util-wrap clearfix">
								<div class="author">작성자 : ${ item.value.indvd_id }</div>
							</div>
						</div>
						<div class="content-wrap">
							<textarea id="content" name="content" rows="" cols=""
								placeholder="내용을 입력해주세요 !">${ item.value.content }</textarea>
						</div>
					</form>
				</c:forEach>
				<div class="btn-wrap">
					<a class="go-list" href="myreview">글 목록으로</a>
					<a id="updateBtn" class="update-done">수정완료</a>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('#updateBtn').on('click', function() {
		$.ajax({
			url : "review",
			datatype : "json",
			type : 'post',
			data : $("form").serialize(),
			success : function(data) {
				if(data.update == true) {
					alert('수정을 완료하였습니다 !');
					location.href = "myreview";
				}
				else alert('수정을 실패하였습니다 !');
			},
			error : function(e) {
				alert('수정을 실패하였습니다 !');
				console.log(e);
			},
		});
	})
</script>
<%@ include file="module/footer.jsp"%>