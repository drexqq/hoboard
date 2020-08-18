<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<nav class="nav">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="text-wrap">
          <div class="big">현재 위치 근처에 있는 병원들을 찾아 보세요 !</div>
          <div class="small">
            Hoboard에 등록된 병원들과 다른 병원들을 찾아볼 수 있습니다 !
          </div>
        </div>
        <div class="search-wrap">
          <div class="location">
            <select name="" id="">
              <option value="">지역</option>
            </select>
            <div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
          </div>
          <div class="category">
            <select name="" id="">
              <option value="">진료과</option>
            </select>
            <div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
          </div>
          <div class="hos-name">
            <input type="text" value="병원이름" />
          </div>
          <div class="search">
            <button>검색하기<i class="ri-search-line"></i></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>

<%@ include file="module/footer.jsp"%>
