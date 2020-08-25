<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="board-detail review-detail">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<c:forEach items="${ reviewDto }" var="item">
					<div class="title-wrap">
						<div class="cate-name">[${ item.key }] - [${ item.value.busi_cate }]</div>
						<div class="title">${ item.value.title }</div>
						<div class="util-wrap clearfix">
							<div class="author">${ item.value.indvd_id }</div>
							<div class="date">${ item.value.wdate }</div>
							<div class="view"><i class="ri-eye-line"></i>${ item.value.viewcount }</div>
							<div class="rating"><i class="ri-star-smile-line"></i>${ item.value.score } / 5</div>
						</div>
					</div>
					<div class="content-wrap">
						${ item.value.content }
					</div>
				</c:forEach>
				<div class="goList">
					<a href="review">글 목록으로</a>
				</div>
				<div class="reply-wrap">
					<div class="reply-title">댓글 <span>${ fn:length(commList) }</span></div>
					<c:choose>
						<c:when test="${ sessionScope.sessionID == null || sessionScope.sessionID eq '' }">
							<div class="reply nologin-disabled">
								<a href="login.jsp">로그인 후 이용하실 수 있습니다 !</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply clearfix">
								<form action="reviewComment" method="post">
									<input type="hidden" name="no" id="no" value="${ param.d }" />
									<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
										<textarea placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
									<input class="submit" type="submit" value="댓글등록">
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="reply-items">
						<c:forEach items="${ commList }" var="list" varStatus="status" begin="0" end="10">
							<div class="item">
								<div class="upper clearfix">
									<div class="id"><i class="ri-user-line"></i>${list.id}</div>
									<div class="date"><i class="ri-calendar-line"></i>${list.date}</div>
								</div>
								<div class="down">${list.content}</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="module/footer.jsp"%>