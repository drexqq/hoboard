<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="board-detail review-detail">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap">
					<div class="cate-name">${detaillist.getBusi_cate()}</div>
					<div class="title">${detaillist.getTitle()}</div>
					<div class="util-wrap clearfix">
						<div class="author">${detaillist.getIndvd_id()}</div>
						<div class="date">${detaillist.getWdate()}</div>
						<div class="view"><i class="ri-eye-line"></i>${detaillist.getViewcount()}</div>
					</div>
				</div>
				<div class="content-wrap">
					${detaillist.getContent()}
				</div>
				<div class="goList">
					<a href="review">글 목록으로</a>
				</div>
				<div class="reply-wrap">
					<div class="reply-title">댓글 <span>${ fn:length(commentlist) }</span></div>
					<c:choose>
						<c:when test="${ sessionScope.sessionID == null }">
							<div class="reply nologin-disabled">
								<a href="login.jsp">로그인 후 이용하실 수 있습니다 !</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply clearfix">
								<form action="COMM" method="post">
									<input type="hidden" name="seq" id="seq" value="${detaillist.getReview_seq()}"> 
									<input type="hidden" name="no" id="no" value="${detaillist.getReview_seq()}"> 
									<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
										<textarea placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
									<input class="submit" type="submit" value="댓글등록">
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="reply-items">
						<c:forEach items="${commentlist}" var="list" varStatus="status" begin="0" end="10">
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