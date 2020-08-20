<<<<<<< HEAD
<%@page import="ask.Ask_Dto"%>
<%@page import="java.util.List"%>
<%@page import="member.Member_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	
<%
//Ask_Dao dao = Ask_Dao.getInstance();
List<Ask_Dto> list = (List<Ask_Dto>)request.getAttribute("list");

int len = (int)request.getAttribute("len");
String searchWord = (String)request.getAttribute("searchWord");
String choice = (String)request.getAttribute("choice");
int pageNumber = (Integer)request.getAttribute("pageNumber");

	//System.out.println("Asklist = "+list.toString());
	System.out.println("Asklen = "+len+" s"+searchWord+" c="+choice+" page "+pageNumber );

%>

<% 	
	System.out.println("AskpageNumber:"+pageNumber);
%>	

<%
//목록 리스트를 검색한것만 가져옴
//10개씩 넘김 
	int AskPage = len/10;
	if(len % 10 > 0){	
		AskPage = AskPage + 1;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my_ask.jsp</title>

=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<div class="mypage-wrap ask">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h2 class="page-title">Q&A</h2>
					<div class="search-wrap">
						<div class="select-wrap clearfix">
							<select id="choice">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<div class="arrow"><i class="ri-arrow-down-s-line"></i></div>
						</div>
						<div class="input-wrap clearfix">
						<input type="text" id="search" placeholder="검색어를 입력해주세요." value="${ searchWord }" onkeydown="enter('review')">
							<button class="btn" onclick="search('ask')"><i class="ri-search-line"></i></button>
						</div>
					</div>
				</div>
				<div class="util-wrap clearfix">
					<div class="notice">고객님의 문의내용 "${len}" 건이 등록 되어있습니다.</div>
					<div class="write"><a href="ask_write.jsp">문의하기</a></div>
				</div>
				<div class="list-wrap">
					<c:choose>
						<c:when test="${len eq 0}">
							<div class="no-result">검색 결과가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<table class="ask-table">
								<thead>
									<tr>
										<th class="num">문의번호</th>
										<th class="tit">제목</th>
										<th class="cont">문의 내용</th>
										<th class="date">문의 일시</th>
										<th class="yn">답변여부</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ askList }" var="list" varStatus="status" begin="0" end="9">
										<tr>
											<td class="num">${ list.seq }</td>
											<td class="tit">${ list.title }</td>
											<td class="cont">${ list.content }</td>
											<td class="date">${ list.wdate }</td>
											<td class="yn">
												<c:if test="${ list.comm eq 1 }">답변대기</c:if>
												<c:if test="${ list.comm eq 0 }"><a class="done" href="ask?one=detail&seq=${ list.seq }">답변완료</a></c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="paging-wrap">
					<c:if test="${ len != 0 }">
						<c:forEach var="page" varStatus="status" begin="0" end="${ page }">
							<div class="page 
							<c:if test="${ param.page eq status.index || (empty param.page && status.first) }">on</c:if>"
							onclick="goPage('ask', ${ status.index })">${ status.index + 1 }</div> 
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c
<script type="text/javascript">
$(document).ready(function() {
	let c = "<c:out value='${ choice }' />"
	$("#choice").find("option").each(function(){
		if($(this).val() == c) $(this).attr("selected","selected");
	})
})
</script>

<script src="js/util.js"></script>
<%@ include file="module/footer.jsp"%>