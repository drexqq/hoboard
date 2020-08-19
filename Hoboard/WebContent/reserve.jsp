<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<nav class="nav">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="text-wrap">
					<div class="big">현재 위치 근처에 있는 병원들을 찾아 보세요 !</div>
					<div class="small">Hoboard에 등록된 병원들과 다른 병원들을 찾아볼 수 있습니다 !</div>
				</div>
				<div class="search-wrap">
					<div class="location">
						<select name="loc" id="">
							<option value="1" selected="selected">지역</option>
							<option value="2">서울</option>
							<option value="3">경기</option>
							<option value="4">인천</option>
							<option value="5">부산</option>
							<option value="6">대전</option>
							<option value="7">대구</option>
							<option value="8">울산</option>
							<option value="9">세종</option>
							<option value="10">광주</option>
							<option value="11">강원</option>
							<option value="12">충북</option>
							<option value="13">충남</option>
							<option value="14">경북</option>
							<option value="15">경남</option>
							<option value="16">전북</option>
							<option value="17">전남</option>
							<option value="18">제주</option>
						</select>
						<div class="arrow">
							<i class="ri-arrow-down-s-line"></i>
						</div>
					</div>
					<div class="category">
						<select name="amt" id="">
							<option value="1" selected="selected">진료과</option>
							<option value="2">내과</option>			
							<option value="3">마취통증학과</option>			
							<option value="4">산부인과</option>			
							<option value="5">소아청소년과</option>			
							<option value="6">신경과</option>			
							<option value="7">신경외과</option>			
							<option value="8">심장내과</option>			
							<option value="9">영상의학과</option>			
							<option value="10">외과</option>			
							<option value="11">응급의학과</option>			
							<option value="12">정형외과</option>			
							<option value="13">재활의학과</option>			
							<option value="14">흉부심장혈관과</option>			
							<option value="15">피부비뇨기과</option>			
							<option value="16">안과</option>			
							<option value="17">치과</option>		
						</select>
						<div class="arrow">
							<i class="ri-arrow-down-s-line"></i>
						</div>
					</div>
					<div class="hos-name">
						<input type="text"  name="searchword" placeholder="병원이름"   value="" />
					</div>
					<div class="search">
						<button onclick="searchBtn()">
							검색하기<i class="ri-search-line"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
<br>
<br>	
<br>

<c:choose>
	<c:when test="${ reslist ne null }">
		<c:forEach items="${ reslist }" var="list" varStatus="status"
			begin="0" end="5">
			<div class="name">이름 : ${ list.name }</div>
			<div class="tel">전화번호 : ${ list.tel }</div>
			<div class="email">이메일 : ${ list.email }</div>
			<div class="address">주소 : ${ list.address }</div>
			<div class="d_address">상세주소 : ${ list.d_Address }</div>
			<hr>
		</c:forEach>
	</c:when>
	<c:when test="${ reslist eq null }">
		<div class="null"></div>
	</c:when>
</c:choose>

<script type="text/javascript">
function searchBtn(){
	let loc = document.getElementByName("loc").value;
	let amt = document.getElementByName("amt").value;
	let searchword = document.getElementByName("searchword").value; 
	
}


</script>


<%@ include file="module/footer.jsp"%>
