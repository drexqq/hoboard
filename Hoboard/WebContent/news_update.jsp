<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<!--  news-write -->
<div class="board-write news-write">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form action="news?work=update" method="get">
					<input type="hidden" name="work" value="updateAf"> <input
						type="hidden" name="seq" value="${ dto.getNews_seq() }>">
					<div class="title-wrap">
						<h1>건강 정보 수정 페이지</h1>
						<div class="title">
							<input type="text" id="title" name="title" size="50px"
								value="${ dto.getTitle() }" placeholder="제목을 입력해주세요">
						</div>
						<div class="util-wrap clearfix">
							<div class="author">작성자 : ${ dto.id }</div>
						</div>
					</div>
					<div class="content-wrap">
						<textarea id="content" name="content" rows="" cols=""
							placeholder="내용을 입력해주세요 !">${ dto.getContent() }</textarea>
					</div>
					<div class="btn-wrap">
						<input type="button" onclick="location.href='news'" value="글 목록">
						<input id="submit" type="submit" value="수정 완료">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="module/footer.jsp"%>