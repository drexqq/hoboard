<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="board-write news-write">
	<div class="container">
		<div class="col-12">
			<div class="title-wrap">
				<div class="lang" style=font-size:30px;>건강정보 작성</div>
				<form action="news_file" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${ sessionScope.sessionID }">
					<div class="title">	
						제목<input type="text" name="title" size="50px">
						<br>
						<div class="file-wrap">
							<div class="file">
								파일첨부 <input type="file" name="filename" size="50px">
								<div class="content-wrap">
									<div class="content">
										<div class="content-title">내용</div>
										<textarea rows="200px" cols="200px" name="content"></textarea>
										<div class="btns-wrap">
											<div><input class="s_write" type="submit" value="글쓰기"></div> 
											<div><input class="s_del" type="reset" value="초기화"></div>
											<div><input class="s_list" type="button" value="글목록" onclick="location.href='news'"></div>  
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