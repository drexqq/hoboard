<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="board-detail news-detail">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="reply-wrap">
					<div class="reply-title">
						<span>게시글 " ${ dto.title } " 관련 댓글을 수정합니다.</span>
					</div>
					<c:choose>
						<c:when test="${ sessionScope.sessionID == null }">
							<div class="reply nologin-disabled">
								<a href="login.jsp">로그인 후 이용하실 수 있습니다 !</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply clearfix">
								<form action="news?work2=c_updateAf" method="post">
									<input type="hidden" name="b_seq" id="b_seq"
										value="${dto2.b_seq}"> <input type="hidden"
										name="c_seq" id="c_seq" value="${dto2.c_seq}"> <input
										type="hidden" name="c_content" id="c_content"
										value="${dto.content}"> <input type="hidden" name="id"
										id="id" value="${ sessionScope.sessionID }">
									<div class="reply-items">
										<div class="reply-items">
											<div class="item">
												<div class="upper clearfix">
													<div class="id">
														<i class="ri-user-line"></i>${dto2.id}</div>
													<div class="date">
														<i class="ri-calendar-line"></i>${dto2.wdate}</div>
												</div>
												<div class="down">
													<input type="text" value="${dto2.content}" name="content"
														id="content" /> <input class="submit" type="submit"
														value="수정 완료">
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="goList">
						<input type='button' onclick='backBtn()' value='뒤로 가기'/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function backBtn() {
	alert('댓글이 수정되지 않았습니다.');
window.opener.location.reload();
window.close();  
}

</script>