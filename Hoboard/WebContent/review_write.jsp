<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<!-- Member_Dto N_list = (Member_Dto)request.getAttribute("businame");
Reserve_Dto C_list = (Reserve_Dto)request.getAttribute("reservecate");
Reserve_Dto R_list = (Reserve_Dto)request.getAttribute("reservelist"); -->

<%-- <%
	String fname = (new Date().getTime()) + "";
System.out.println("fname:" + fname);
%> --%>


<div class="board-write news-write">
	<div class="container">
		<div class="col-12">
			<div class="title-wrap">
				<div class="lang" style="font-size: 30px;">후기 작성</div>
				<form action="file" method="post" enctype="multipart/form-data">
					<input type="hidden" name="indvd_id"
						value="${ R_list.getIndvd_id() }"> <input type="hidden"
						name="busi_id" value="${ R_list.getBusi_id() }">
					<div class="title">
						병원 카테고리 <input type="text" name="cate"
							value="${ N_list.getName() }-${ C_list.getCate() }"
							readonly="readonly">

						<div class="title">
							아이디 <input type="text" name="id" value="${R_list.getIndvd_id() }"
								readonly="readonly">

							<div class="title">
								제목 <input type="text" name="title" value="">

								<div class="file-wrap">
									<div class="file">
										파일업로드 <input type="file" name="filename"
											onchange="checkFile(this)" style="width: 400px">
										<div class="score">
											평점을 입력해주세요 : <select name="score">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
											<div class="content-wrap">
												<div class="content">
													<textarea rows="20px" cols="70px" name="content"></textarea>
													<div class="btns">
														<input type="submit" value="후기작성"> <input
															type="button"
															onclick="location='review?key=main&id=${R_list.getIndvd_id()}'"
															value="후기작성취소하기">
													</div>
												</div>
											</div>
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

<script type="text/javascript">
	function checkFile(f) {

		// files info
		var file = f.files;

		// file[0].name
		// check
		if (!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name))
			alert('gif, jpg, jpeg, png 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);

		// check true return
		else
			return;

		// check not img file reset
		f.outerHTML = f.outerHTML;
	}
</script>

