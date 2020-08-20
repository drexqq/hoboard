<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="mypage-wrap myreview">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h2 class="page-title">후기내역</h2>
					<div class="search-wrap">
						<div class="select-wrap clearfix">
							<select id="choice">
								<option value="name">병원이름</option>
								<option value="cate">진료과</option>
							</select>
							<div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
						</div>
						<div class="input-wrap clearfix">
							<input type="text" id="search" placeholder="검색어를 입력해주세요." value="" onkeydown="enter('myreview')">
							<button class="btn" onclick="search('myreview')"><i class="ri-search-line"></i></button>
						</div>
					</div>
				</div>
				<div class="util-wrap clearfix">
					<div class="notice">총 "${ fn:length(list) }" 건의 후기를 작성하셨습니다.</div>
				</div>
				<div class="list-wrap">
					<c:choose>
						<c:when test="${ fn:length(list) eq 0 }">
							<div class="no-result">작성한 후기가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<table class="review-table">
								<thead>
									<tr>
										<th class="cate">병원-진료과</th>
										<th class="tit">제목</th>
										<th class="date">작성 일시</th>
										<th class="view">조회수</th>
										<th class="btns">수정/삭제</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ list }" var="list" varStatus="status" begin="0" end="9">
										<tr>
											<td class="cate">${ list.busi_cate }</td>
											<td class="tit" onclick = "location.href='review?key=detail&seq=${ list.review_seq }'">${ list.title }</td>
											<td class="date">${ list.wdate }</td>
											<td class="view">${ list.viewcount }</td>
											<td class="btns">
												<a href="review?key=updateview&seq=${ list.review_seq }">수정</a>
												<a href="review?key=delete&seq=${ list.review_seq }">삭제</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="paging-wrap">
					<c:if test="${ fn:length(list) != 0 }">
						<c:forEach var="page" varStatus="status" begin="0" end="${ page }">
							<div class="page 
							<c:if test="${ param.page eq status.index || (empty param.page && status.first) }">on</c:if>"
							onclick="goPage('review',${ status.index })">${ status.index + 1 }</div> 
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="js/util.js"></script>
<%@ include file="module/footer.jsp"%>