<%@page import="review.Review_Dao"%>
<%@page import="Reserve.Reserve_Dto"%>
<%@page import="member.Member_Dto"%>
<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%@ include file="module/header.jsp"%>
<h1>리뷰리스트</h1>
<div>
<input type="text" id="search" value=""><button onclick="searchBbs()">검색</button>
<select id="choice">
	<option value="id" selected="selected">작성자</option>
	<option value="busi_name">병원이름</option>
	<option value="title">제목</option>
	<option value="content">내용</option>	
	<option value="score">평점</option>
</select>
</div>

<br><br>
<c:choose>
 
    <c:when test="${len eq 0}">
       <div>검색 결과가 없습니다.</div>
    </c:when>
 
    <c:otherwise>
    
    <c:forEach items="${ reviewlist }" var="list" varStatus="status" begin="0" end="4">
		<div>
		<hr>
			<div>제목 : <a href="review?key=detail&seq=${ list.review_seq }">${ list.title }</a></div>
			<div>내용 : ${ list.content }</div>
			<div>뷰카운트 : ${ list.viewcount }</div>
			<div>평점 : ${ list.score }</div>
			<div>작성일 : ${ list.wdate }</div>
		</div>
		<br>
		<br>
		</c:forEach>
        <c:forEach items="${ reviewlist }" var="page" varStatus="status" begin="0" end="${ page }">
			<%-- <c:set var="${ pageNumber }" value="${ status.index }">
				<c:when test="${ pageNumber eq status.index }">
					<a onclick="goPage(${ status.index })">${ status.index + 1 }</a>
				</c:when>
				<c:when test="${ pageNumber ne status.index }">
					<a onclick="goPage(${ status.index })">[${ status.index + 1 }]</a>
				</c:when>
			</c:set> --%>
			<%-- <c:if test="${ pageNumber eq status.index }">
				<a onclick="goPage(${ status.index })">${ status.index + 1 }</a>
			</c:if> --%>
			<a onclick="goPage(${ status.index })">[${ status.index + 1 }]</a> 
		</c:forEach>
    </c:otherwise>
 
</c:choose>

<!-- 검색버튼  -->
<script type="text/javascript">
function searchBbs() {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;

    if(word == ""){
		document.getElementById("search").value = "";
	}
    
	location.href = "review?key=main&searchWord=" + word + "&choice=" + choice;
}

function goPage( pageNum ) {	
	
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "review?key=main&page=" + pageNum;
}

</script>

<br>
<br>


<% //TODO after scriptlet session ID add change %>
<button type="button" id="getId" onclick="writeBtn()" value="<%="fff"%>">글쓰기</button>
<!-- after -> reserve button  -->
<script type="text/javascript">
function writeBtn(){
	let ID = document.getElementById("getId").value;
	//TODO dummy
	let BUSI_ID = "hanbang";
	let seq = "7"; 
	
	//reserve status="3" select button "show" else
	//after id , busi_id , seq must
	location.href="review?key=writeview&id=" + ID + "&busi=" + BUSI_ID + "&seq=" + seq;
}
</script>


<%@ include file="module/footer.jsp"%>