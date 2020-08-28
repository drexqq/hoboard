<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="admin-list">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h2 class="page-title">관리자 페이지</h2>
					<div class="list-wrap">
						<table class="admin-table">
							<thead>
								<tr>
									<th class="admin">관리 리스트</th>
									<th class="now">현황</th>
									<th class="abtns">바로가기</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="admin">회원 관리</td>
									<td class="now">병원 회원 (${ fn:length(hmlist) })명 /개인 회원 (${
                    fn:length(pmlist) })명
									</td>
									<td class="abtns"><input type="button" name="memBtn"
										onclick="location.href='admin?adm=adminM'" value="바로가기" /></td>
								</tr>
								<tr>
									<td class="admin">Q&A 관리</td>
									<td class="now">답변 대기 (${ fn:length(qlist_all) })건</td>
									<td class="abtns"><input type="button" nsame="qnaBtn"
										onclick="location.href='ask'" value="바로가기" /></td>
								</tr>
								<tr>
									<td class="admin">이용 후기 관리</td>
									<td class="now">작성 된 후기 (${ fn:length(rlist) })건</td>
									<td class="abtns"><input type="button" name="revBtn"
										onclick="location.href='review'" value="바로가기" /></td>
								</tr>
								<tr>
									<td class="admin">건강 정보 게시판 관리</td>
									<td class="now">건강 정보 (${ fn:length(nlist) })건</td>
									<td class="abtns"><input type="button" name="newsBtn"
										onclick="location.href='news'" value="바로가기" /></td>
								</tr>
							</tbody>
						</table>
						<br /> <br />

						<div class="board-name" style="float: left; margin-right: 20px">
							Q&A 게시판</div>
						<div class="button">
							<input type="button" name="qnaBtn" onclick="location.href='ask'"
								value="바로가기" />
						</div>
						<br />
						<table class="board-table">
							<thead>
								<tr>
									<th class="date">작성일</th>
									<th class="id">작성자</th>
									<th class="etc">제목</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ qlist }" var="qlist" varStatus="status"
									begin="0" end="4">
									<tr>
										<td class="date">${qlist.wdate}</td>
										<td class="id">${qlist.id}</td>
										<td class="etc">${qlist.title}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br /> <br />

						<div class="board-name" style="float: left; margin-right: 20px">
							후기 게시판</div>
						<div class="button">
							<input type="button" name="revBtn"
								onclick="location.href='review'" value="바로가기" />
						</div>
						<br />
						<table class="board-table">
							<thead>
								<tr>
									<th class="date">작성일</th>
									<th class="id">작성자</th>
									<th class="etc">평점</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ rlist }" var="rlist" varStatus="status"
									begin="0" end="4">
									<tr>
										<td class="date">${rlist.wdate}</td>
										<td class="id">${rlist.indvd_id}</td>
										<td class="etc"><i class="ri-star-smile-line"></i>&nbsp;${rlist.score}
											/ 5</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br /> <br />

						<div class="board-name" style="float: left; margin-right: 20px">
							정보 게시판</div>
						<div class="button">
							<input type="button" name="newsBtn"
								onclick="location.href='news'" value="바로가기" /> <a
								href="news_write.jsp"
								style="width: 90px; height: 35px; padding: 8px 15px; margin: 0; font-size: 16px; text-align: center; line-height: 30px; box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075); border: 1px solid #ccc; border-radius: 5px; transition: all 0.25s ease; background-color: #fff;">작성하기</a>
						</div>

						<br />
						<table class="board-table">
							<thead>
								<tr>
									<th class="date">작성일</th>
									<th class="id">작성자</th>
									<th class="etc">제목</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ nlist }" var="nlist" varStatus="status"
									begin="0" end="4">
									<tr>
										<td class="date">${nlist.date}</td>
										<td class="id">${nlist.id}</td>
										<td class="etc">${nlist.title}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="module/footer.jsp"%>
