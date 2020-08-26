<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="board-detail news-detail">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap">
					<div class="title">${ dto.title }</div>
					<div class="util-wrap clearfix">
						<div class="author">${ dto.id }</div>
						<div class="date">${ dto.date }</div>
						<div class="view">
							<i class="ri-eye-line"></i>${ dto.viewcount }</div>
					</div>
				</div>
				<%-- <div><img alt="이미지 없음" src="/WebContent/upload/"+${dto.news_file}></div> --%>
				<div class="content-wrap"> ${dto.content} </div>
				<br>
				<div class="btns" align="center">
					<input type="button" class="updateBtn" name="updateBtn" value="수정"
							onclick="location.href='news?work=update&seq=${dto.news_seq}'">
					<input type="button" class="delBtn" name="delBtn" value="삭제"
							onclick="location.href='news?work=del&seq=${dto.news_seq}'">
				</div>
				<div class="goList">
				<br><br>	
					<a href="news">글 목록으로</a>
				</div>

				<div class="reply-wrap">
					<div class="reply-title">
						댓글 <span>${ fn:length(comm) }</span>
					</div>
					<c:choose>
						<c:when test="${ sessionScope.sessionID == null }">
							<div class="reply nologin-disabled">
								<a href="login.jsp">로그인 후 이용하실 수 있습니다 !</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply clearfix">
								<form action="news?work2=c_write" method="post">
									<input type="hidden" name="b_seq" id="b_seq"
										value="${dto.news_seq}"> <input type="hidden"
										name="c_content" id="c_content" value="${dto.content}">
									<input type="hidden" name="id" id="id"
										value="${ sessionScope.sessionID }">
									<textarea placeholder="댓글 작성" name="reply_content"
										id="reply_content"></textarea>
									<input class="submit" type="submit" value="댓글등록">
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="reply-items">
						<div class="reply-items">
							<c:forEach items="${ comm }" var="comm" varStatus="status"
								begin="0" end="10">
								<div class="item">
									<div class="upper clearfix">
										<div class="id">
											<i class="ri-user-line"></i>${comm.id}</div>
										<div class="date">
											<i class="ri-calendar-line"></i>${comm.wdate}</div>
									</div>
									<div class="down">${comm.content}</div>
									<br> <br>
									<c:if test="${comm.id == sessionScope.sessionID }">
										<div class="btns">
										<button class="updateBtn"
											onclick="window.open('news?work=c_update&c_seq=${comm.c_seq}&b_seq=${comm.b_seq}&content=${comm.content}'
											,'댓글 수정하기','width=1000, height=300 ,location=no,status=no,scrollbars=no');">댓글 수정</button>
										<input type="button" class="replydelBtn" name="delBtn" value="댓글 삭제"
											onclick="location.href='news?work=c_del&c_seq=${comm.c_seq}&b_seq=${comm.b_seq}'">
										</div>
									</c:if>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="module/footer.jsp"%>