<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<!-- card-wrap -->
<div class="card-wrap">
  <div class="container">
    <div class="row">
    <c:forEach items="${ busiCate }" var="cate" varStatus="status">
	    <div class="col-3">
	        <a href="" class="card">
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
          <a href="news.jsp"
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
          <a href="" class="list">
            <div class="name">
              [분당재생병원 - 내과]
              <span class="grade"><i class="ri-star-smile-line"></i>5</span>
            </div>
            <div class="content">
              여기는 후기에 대한 내용을 미리볼수 있는 공간임둥여기는 후기에 대한
              내용을 미리볼수 있는 공간임둥여기는 후기에 대한 내용을 미리볼수
              있는 공간임둥여기는 후기에 대한 내용을 미리볼수 있는
              공간임둥여기는 후기에 대한 내용을 미리볼수 있는 공간임둥
            </div>
            <div class="date">2020.xx.xx</div>
          </a>
          <a href="" class="list">
            <div class="name">
              [분당재생병원 - 내과]
              <span class="grade"><i class="ri-star-smile-line"></i>5</span>
            </div>
            <div class="content">
              여기는 후기에 대한 내용을 미리볼수 있는 공간임둥여기는 후기에 대한
              내용을 미리볼수 있는 공간임둥여기는 후기에 대한 내용을 미리볼수
              있는 공간임둥여기는 후기에 대한 내용을 미리볼수 있는
              공간임둥여기는 후기에 대한 내용을 미리볼수 있는 공간임둥
            </div>
            <div class="date">2020.xx.xx</div>
          </a>
          <a href="" class="list">
            <div class="name">
              [분당재생병원 - 내과]
              <span class="grade"><i class="ri-star-smile-line"></i>5</span>
            </div>
            <div class="content">
              여기는 후기에 대한 내용을 미리볼수 있는 공간임둥여기는 후기에 대한
              내용을 미리볼수 있는 공간임둥여기는 후기에 대한 내용을 미리볼수
              있는 공간임둥여기는 후기에 대한 내용을 미리볼수 있는
              공간임둥여기는 후기에 대한 내용을 미리볼수 있는 공간임둥
            </div>
            <div class="date">2020.xx.xx</div>
          </a>
          <a href="" class="list">
            <div class="name">
              [분당재생병원 - 내과]
              <span class="grade"><i class="ri-star-smile-line"></i>5</span>
            </div>
            <div class="content">
              여기는 후기에 대한 내용을 미리볼수 있는 공간임둥여기는 후기에 대한
              내용을 미리볼수 있는 공간임둥여기는 후기에 대한 내용을 미리볼수
              있는 공간임둥여기는 후기에 대한 내용을 미리볼수 있는
              공간임둥여기는 후기에 대한 내용을 미리볼수 있는 공간임둥
            </div>
            <div class="date">2020.xx.xx</div>
          </a>
        </div>
        <div class="go-review">
          <a href="#">더 보기</a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- //review-wrap -->

<%@ include file="module/footer.jsp"%>
