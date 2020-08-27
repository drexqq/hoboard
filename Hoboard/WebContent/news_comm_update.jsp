<%@page import="news.News_COMM_Dto"%>
<%@page import="news.News_COMM_Dao"%>
<%@page import="news.News_Dao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
 <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hoboard</title>
    <!-- Bootstrap CDN -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />
    <!-- font / NanumSquare -->
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css"
    />
    <!-- remix-icon CDN -->
    <link
      href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
      rel="stylesheet"
    />
    <!-- reset.css -->
    <link rel="stylesheet" href="css/reset.css" />
    <!-- style.css -->
    <link rel="stylesheet" href="css/module.css" />
    <link rel="stylesheet" href="css/style.css" />
    <!-- layout.css -->
    <link rel="stylesheet" href="css/comp.css" />
    <!-- jqeuryCDN -->
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <!-- fullcander js/css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.0/main.css">
    <!-- fullcalendar js -->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.3.0/main.min.js"></script>
  </head>
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
														id="content" />
													<input class="replyupdate" type="submit" 
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
						<input type='button' class="back" onclick='backBtn()' value='뒤로 가기'/>
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