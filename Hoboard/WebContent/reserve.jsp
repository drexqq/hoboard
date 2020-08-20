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
						<select name="" id="loc">
							<option value="서울" selected="selected">서울</option>
							<option value="경기">경기</option>
							<option value="인천">인천</option>
							<option value="부산">부산</option>
							<option value="대전">대전</option>
							<option value="대구">대구</option>
							<option value="울산">울산</option>
							<option value="세종">세종</option>
							<option value="광주">광주</option>
							<option value="강원">강원</option>
							<option value="충북">충북</option>
							<option value="충남">충남</option>
							<option value="경북">경북</option>
							<option value="경남">경남</option>
							<option value="전북">전북</option>
							<option value="전남">전남</option>
							<option value="제주">제주</option>
						</select>
						<div class="arrow">
							<i class="ri-arrow-down-s-line"></i>
						</div>
					</div>
					<div class="category">
						<select name="" id="amt">
							<option value="INTERNAL" selected="selected">내과</option>
							<option value="ANPN">마취통증학과</option>
							<option value="MTRNT">산부인과</option>
							<option value="PDTRC">소아청소년과</option>
							<option value="NRLGY">신경과</option>
							<option value="NRSRG">신경외과</option>
							<option value="CRDLG">심장내과</option>
							<option value="XRAY">영상의학과</option>
							<option value="GS">외과</option>
							<option value="DPRTM">응급의학과</option>
							<option value="OS">정형외과</option>
							<option value="RHBLT">재활의학과</option>
							<option value="THRCC">흉부심장혈관과</option>
							<option value="SKIN_URO">피부비뇨기과</option>
							<option value="DENT">안과</option>
							<option value="OPHTH">치과</option>
						</select>
						<div class="arrow">
							<i class="ri-arrow-down-s-line"></i>
						</div>
					</div>
					<div class="hos-name">
						<input type="text" id="searchWord" placeholder="검색어를 입력해주세요."
							value="${ searchWord }" onkeydown="enter('reserve')"/>
					</div>
					<div class="search">
						<button onclick="R_search( 'reserve' )">검색하기<i class="ri-search-line"></i></button>
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
	<c:when test="${ res_search_list ne null }">
		<c:forEach items="${ res_search_list }" var="s_list"
			varStatus="status" begin="0" end="5">
			<div class="name">이름 : ${ s_list.name }</div>
			<div class="tel">전화번호 : ${ s_list.tel }</div>
			<div class="email">이메일 : ${ s_list.email }</div>
			<div class="address">주소 : ${ s_list.address }</div>
			<div class="d_address">상세주소 : ${ s_list.d_Address }</div>
			<div class="button">
				<button type="button" onclick="reservebtn('${ s_list.id }')">예약하기</button>
			</div>
			<hr>
		</c:forEach>
	</c:when>
	<c:when test="${ res_search_list eq null }">
		<div class="null"></div>
	</c:when>
</c:choose>

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


<div class="paging-wrap">
	<c:if test="${ len != 0 }">
		<c:forEach var="page" varStatus="status" begin="0" end="${ page }">
			<div
				class="page 
							<c:if test="${ param.page eq status.index || (empty param.page && status.first) }">on</c:if>"
				onclick="goRPage('reserve',${ status.index })">${ status.index + 1 }</div>
		</c:forEach>
	</c:if>
</div>



<script type="text/javascript">
$(document).ready(function(e) {
	let c = "<c:out value='${ loc }' />"
	$("#loc").find("option").each(function(){
		if($(this).val() == c) $(this).attr("selected","selected");
	})
	
	let d = "<c:out value='${ amt }' />"
	$("#amt").find("option").each(function(){
		if($(this).val() == d) $(this).attr("selected","selected");
	})
})

function reservebtn( id ){
	location.href = "reserve?key=detail&id=" + id;
}
</script>
<script src="js/util.js"></script>
<%@ include file="module/footer.jsp"%>
