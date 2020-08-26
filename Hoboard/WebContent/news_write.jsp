<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="board-detail news-detail">
	<div class="container">
		<div class="col-12">
			<div class="title-wrap">
				<div class="lang">건강정보 작성</div>
				<form action="news_file" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="admin">
					<div class="title">	
						제목 : <input type="text" name="title" size="50px">
						<div class="file-wrap">
							<div class="file">
								파일첨부 : <input type="file" name="filename" size="50px">
								<div class="content-wrap">
									<div class="content">
										<div>내용 :</div>
										<textarea rows="20" cols="50px" name="content"></textarea>
										<div class="btns">
											<input type="submit" value="글쓰기"> <input type="reset"
												value="초기화"> <input type="button" value="글목록"
												onclick="news">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="module/footer.jsp"%>