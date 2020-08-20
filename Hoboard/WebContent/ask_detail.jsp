<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="mypage-wrap ask-detail">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title-wrap clearfix">
          <h2 class="page-title">${ dto.title } 문의에 대한 답변</h2>
          <div class="info">
            <span class="num">문의 번호 : ${ dto.seq }</span>
            <span class="date">문의 날짜 : ${ dto.wdate }</span>
          </div>
        </div>
        <div class="ask-wrap">
          <div class="ask-head">상세 내용</div>
          <div class="ask-body">${ dto.content }</div>
        </div>
        <div class="comm-wrap">
          <div class="comm-head">
            <i class="ri-customer-service-2-line"></i>답변 내용
          </div>
          <div class="comm-body">
            <c:choose>
              <c:when test="${ dto.comm eq 0 }">
                <div class="result">여기는 문의 답변 완료</div>
              </c:when>
              <c:otherwise>
                <div class="no-result">
                  죄송합니다. 아직 문의에 대한 답변이 없습니다.
                </div>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="btn-wrap">
          <a href="ask">문의 목록</a>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="module/footer.jsp"%>
