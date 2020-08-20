<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="review">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h1 class="title">후기게시판</h1>
					<div class="search-wrap clearfix">
						<div class="select-wrap">
							<select id="choice">
								<option value="id" selected="selected">작성자</option>
								<option value="busi_name">병원이름</option>
								<option value="title">제목</option>
								<option value="content">내용</option>	
								<option value="score">평점</option>
							</select>
							<div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
						</div>
						<div class="input-wrap clearfix">
							<input type="text" id="search" placeholder="검색어를 입력해주세요." value="${ searchWord }" onkeydown="enter('review')">
							<button onclick="search( 'review' )"><i class="ri-search-line"></i></button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12">
				<div class="list-wrap">
					<c:choose>
						<c:when test="${len eq 0}">
							<div class="no-result">검색 결과가 없습니다.</div>
						</c:when>
						<c:otherwise>
						<c:forEach items="${ reviewlist }" var="list" varStatus="status" begin="0" end="4">
							<a href="review?key=detail&seq=${ list.review_seq }" class="list">
								<div class="name">${ list.title }</div>
								<div class="content">${ list.content }</div>
								<div class="util-wrap">
									<div>
										<span class="grade">
											<i class="ri-star-smile-line"></i>
											${ list.score } / 5
										</span>
										<span class="view">
											<i class="ri-eye-line"></i>
											${ list.viewcount }
										</span>
									</div>
									<div class="date">${ list.wdate }</div>
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
							onclick="goPage('review',${ status.index })">${ status.index + 1 }</div> 
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	let c = "<c:out value='${ choice }' />"
	$("#choice").find("option").each(function(){
		if($(this).val() == c) $(this).attr("selected","selected");
	})
})
</script>


<script src="js/util.js"></script>
<%@ include file="module/footer.jsp"%>