<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="board-write review-write">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<c:forEach items="${ reserveDto }" var="item">
					<form action="review" method="post">
						<input type="hidden" name="busi_id"
							value="${ item.value.busi_id }"> <input type="hidden"
							name="cate" value="${ item.value.cate }">
						<div class="title-wrap">
							<div class="cate-name">[${ item.key }] - [${ item.value.cate }]</div>
							<div class="title">
								<input id="title" name="title" type="text" value=""
									placeholder="제목을 입력해주세요">
							</div>
							<div class="util-wrap clearfix">
								<div class="author">작성자 : ${ item.value.indvd_id }</div>
								<div class="score clearfix">
									<div class="score-title">평점</div>
									<select name="score" id="score">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
							</div>
						</div>
						<div class="content-wrap">
							<textarea id="content" name="content" rows="" cols=""
								placeholder="내용을 입력해주세요 !"></textarea>
						</div>
					</form>
				</c:forEach>
				<div class="btn-wrap">
					<a class="go-list" href="myreserve">예약내역</a> <a id="writeBtn"
						class="write-done">작성완료</a>
				</div>

				<script type="text/javascript">
					$('#writeBtn').on('click', function() {
						$.ajax({
							url : "review",
							datatype : "json",
							type : 'post',
							data : {
								hidden : "write",
								data : $("form").serialize()
							},
							success : function(data) {
								alert("후기 작성이 완료되었습니다 !");
								if (data.done)
									location.href = "myreview";
							},
							error : function(e) {
								alert('후기 작성을 실패하였습니다 !');
								console.log(e);
							},
						});
					})
				</script>
				<%@ include file="module/footer.jsp"%>