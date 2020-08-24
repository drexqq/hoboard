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
					<a class="go-list" href="review">글 목록으로</a>
					<a id="updateBtn" class="update-done">수정완료</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- 
<form action="review" method="post">
<input type="hidden" name="key" value="update">
<input type="hidden" name="seq" value="<%=dto.getReview_seq() %>">
<table style="width:600" border="1" align="center">
<col width="300"><col width="500">
<tr>
	<th>병원 카테고리</th>
		<td><input type="text" name="busi_cate" readonly="readonly" size="50" value="<%=dto.getBusi_cate()%>"><td>
</tr>
<tr>
	<th>제목</th>
		<td><input type="text" name="title"  size="50" value="<%=dto.getTitle()%>"><td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="id" readonly="readonly" size="50" value="<%=dto.getIndvd_id()%>"><td> 		
</tr>
<tr>
	<th>파일다운로드</th>
		<td><input type="button" name="btndown" value="파일다운로드" onclick="location.href='filedown?filename=<%=dto.getFilename() %>&seq=<%=dto.getReview_seq() %>'"></td>
</tr>
<tr>
	<th>작성일</th>
		<td><input type="text" name="wdate" readonly="readonly" size="50" value="<%=dto.getWdate()%>"><td>
</tr>
<tr>
	<th>조회수</th>
		<td><input type="text" name="viewcount" readonly="readonly" size="50" value="<%=dto.getViewcount()%>"></td>
</tr>
<tr>
	<td>
	<textarea rows="20px" cols="70px" name="content"><%=dto.getContent()%></textarea>
	</td>
</tr>
</table>
<div align="center">
	<input type="submit" value="글수정">
</div>
</form> --%>
<script type="text/javascript">
	$('#updateBtn').on('click', function() {
		console.log("updateBtn");
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