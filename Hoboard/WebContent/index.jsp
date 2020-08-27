<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<!-- card-wrap -->
<div class="card-wrap">
  <div class="container">
    <div class="row">
    <c:forEach items="${ busiCate }" var="cate" varStatus="status">
	    <div class="col-3">
	        <a href="reserve?amt=${ cate.key }" class="card">
	          <div class="icon">
	            <img src="img/cate${ status.index+1 }.png" alt="${ cate.value }" />
	          </div>
	          <div class="text">${ cate.value }</div>
	        </a>
	      </div>
      </c:forEach>
    </div>
  </div>
</div>
<!-- //card-wrap -->
<!-- health-wrap -->
<div class="health-wrap">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title">건강에 대한 다양한 정보를 알아보세요 !</div>
        <div class="desc">
          <a href="news_list.do"
            >건강정보 보러가기 <i class="ri-arrow-right-line"></i
          ></a>
        </div>
        <div class="img"></div>
      </div>
    </div>
  </div>
</div>
<!-- //health-wrap -->
<!-- review-wrap -->
<div class="review-wrap">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title">후기게시판</div>
        <div class="list-wrap">
			<c:forEach items="${ reviewList }" var="map" varStatus="status">
				<c:if test="${status.first}">
					<c:forEach items="${ map }" var="list">
						<a href="review?d=${ list.key.review_seq }" class="list">
							<div class="cate">[${ list.value }] - [${ list.key.busi_cate }]</div>
							<div class="name">${ list.key.title }</div>
							<div class="content">${ list.key.content }</div>
							<div class="util-wrap">
								<div>
									<span class="grade"> <i class="ri-star-smile-line"></i>
										${ list.key.score } / 5
									</span> <span class="view"> <i class="ri-eye-line"></i> ${ list.key.viewcount }
									</span>
								</div>
								<div class="date">${ list.key.wdate }</div>
							</div>
						</a>
					</c:forEach>
				</c:if>
			</c:forEach>
        </div>
        <div class="go-review">
          <a href="review">더 보기</a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- //review-wrap -->

<%@ include file="module/footer.jsp"%>
