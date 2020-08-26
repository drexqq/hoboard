<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="review">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h1 class="title">요즘 많이 찾는 건강 정보</h1>
					<!-- <h4>건강 정보 총 ??? 건이 등록 돼 있습니다.</h4> -->
					<div class="search-wrap clearfix">
						<div class="select-wrap">
							<select id="choice">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
						</div>
						<div class="input-wrap clearfix">
							<input onkeydown="enter('news')" type="text" id="search" placeholder="검색어를 입력해주세요" value="">
							<button onclick="search( 'news' )" class="btn" value=""><i class="ri-search-line"></i></button>
						</div>
					</div>
				</div>
				<a href = "news_write.jsp">글쓰기</a>
			<form action="news_file" enctype="multipart/form-data" method="post">
			</form> 
				</div>
			<div class="col-12">
				<div class="list-wrap">
					<c:choose>
						<c:when test="${ len eq 0 }">
							<div class="no-result">검색 결과가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ list }" var="list" varStatus="status" begin="0" end="4">
							<a href="news?work=detail&seq=${ list.news_seq }" class="list">
								<div class="name">${ list.title }</div>
								<div class="content">${ list.content }</div>
								<div class="util-wrap">
									<div>
									<%-- <span>댓글 수 (${ fn:length(comm)})</span> --%>
										<span class="view">
											<i class="ri-eye-line"></i>
											${ list.viewcount }
										</span>
									</div>
									<div class="date">${ list.date }</div>
								</div>
							</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="paging-wrap">
					<c:if test="${ len != 0 }">
						<c:forEach var="page" varStatus="status" begin="0" end="${ page }">
							<div class="page 
							<c:if test="${ param.page eq status.index || (empty param.page && status.first) }">on</c:if>"
							onclick="goPage('news',${ status.index })">${ status.index + 1 }</div> 
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="js/util.js"></script>
<%@ include file="module/footer.jsp"%>