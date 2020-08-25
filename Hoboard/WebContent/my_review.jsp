<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="mypage-wrap myreview">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h2 class="page-title">후기내역</h2>
					<div class="search-wrap">
						<div class="select-wrap clearfix">
							<select id="choice">
								<c:choose>
									<c:when test="${ auth eq 1 }">
									<option value="name">병원이름</option>
									</c:when>
									<c:otherwise>
									<option value="name">환자이름</option>
									</c:otherwise>
								</c:choose>
								<option value="cate">진료과</option>
							</select>
							<div class="arrow">
								<i class="ri-arrow-down-s-line"></i>
							</div>
						</div>
						<div class="input-wrap clearfix">
							<input type="text" id="search" placeholder="검색어를 입력해주세요."
								value="${ searchWord }" onkeydown="enter('myreview')">
							<button class="btn" onclick="search('myreview')">
								<i class="ri-search-line"></i>
							</button>
						</div>
					</div>
				</div>
				<div class="util-wrap clearfix">
					<c:choose>
						<c:when test="${ auth eq 1 }">
						<div class="notice">총 "${ count }" 건의 후기를 작성하셨습니다.</div>
						</c:when>
						<c:otherwise>
						<div class="notice">총 "${ count }" 건의 후기가 작성되었습니다.</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="list-wrap">
					<c:choose>
						<c:when test="${ count eq 0 }">
							<div class="no-result">작성한 후기가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<table class="review-table">
								<thead>
									<tr>
										<th class="name">병원</th>
										<th class="cate">진료과</th>
										<th class="tit">제목</th>
										<th class="date">작성 일시</th>
										<th class="view">조회수</th>
										<c:if test="${ auth eq 1 }">
										<th class="btns">수정/삭제</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ reviewlist }" var="map" varStatus="status">
										<c:if test="${ status.first }">
											<c:forEach items="${map}" var="list">
												<tr>
													<td class="name">${ list.value }</td>
													<td class="cate">${ list.key.busi_cate }</td>
													<td class="tit" onclick="location.href='review?d=${ list.key.review_seq }'">${ list.key.title }</td>
													<td class="date">${ list.key.wdate }</td>
													<td class="view">${ list.key.viewcount }</td>
													<c:if test="${ auth eq 1 }">
													<td class="btns">
														<a href="review?d=u&seq=${ list.key.review_seq }">수정</a>
														<a class="deleteBtn" data-seq="${ list.key.review_seq }">삭제</a>
													</td>
													</c:if>
												</tr>
											</c:forEach>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="paging-wrap">
					<c:if test="${ fn:length(reviewlist) != 0 }">
						<c:forEach var="page" varStatus="status" begin="0" end="${ page }">
							<div class="page 
							<c:if test="${ param.page eq status.index || (empty param.page && status.first) }">on</c:if>"
								onclick="goPage('myreview',${ status.index })">${ status.index + 1 }</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="js/util.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	let c = "<c:out value='${ choice }' />"
	$("#choice").find("option").each(function(){
		if($(this).val() == c) $(this).attr("selected","selected");
	})
})
$(".deleteBtn").on('click', function(){
	console.log($(this).data("seq"));
	$.ajax({
		url : "review?d=d&seq="+$(this).data("seq"),
		datatype : "json",
		type : 'get',
		data : {seq : $("#seq").val()},
		success : function(data) {
			if(data.del == true) {
				alert('삭제를 완료하였습니다 !');
				location.href = "myreview";
			}
			else alert('삭제를 실패하였습니다 !');
		},
		error : function(e) {
			alert('삭제를 실패하였습니다 !');
			console.log(e);
		},
	});
})
</script>
<%@ include file="module/footer.jsp"%>