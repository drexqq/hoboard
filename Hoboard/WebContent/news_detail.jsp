<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>

<div class="board-detail news-detail">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap">
					<div class="title">${ dto.title }</div>
					<div class="util-wrap clearfix">
						<div class="author">${ dto.id }</div>
						<div class="date">${ dto.date }</div>
						<div class="view">
							<i class="ri-eye-line"></i>${ dto.viewcount }</div>
					</div>
				</div>
				<div class="content-wrap">${dto.content}</div>
				<div class="goList">
					<a href="news">글 목록으로</a>
				</div>

				<div class="reply-wrap">
					<div class="reply-title">
						댓글 <span>${ fn:length(comm) }</span>
					</div>
					<c:choose>
						<c:when test="${ sessionScope.sessionID == null }">
							<div class="reply nologin-disabled">
								<a href="login.jsp">로그인 후 이용하실 수 있습니다 !</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply clearfix">
								<form action="news?work2=c_write" method="post">
									<!-- <input type="hidden" name="work2" id="seq" value="c_write">  -->
									<input type="hidden" name="b_seq" id="b_seq" value="${dto.news_seq}">
										<input type="hidden" name="c_content" id="c_content" value="${dto.content}"> 
										<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
									<textarea placeholder="댓글 작성" name="reply_content"
										id="reply_content"></textarea>
									<input class="submit" type="submit" value="댓글등록">
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="reply-items">
						<div class="reply-items">
							<c:forEach items="${ comm }" var="comm" varStatus="status"
								begin="0" end="10">
								<div class="item">
									<div class="upper clearfix">
										<div class="id">
											<i class="ri-user-line"></i>${comm.id}</div>
										<div class="date">
											<i class="ri-calendar-line"></i>${comm.wdate}</div>
									</div>
									<div class="down">${comm.content}</div>
									<br><br>
									<a href = "news?work=c_del&c_seq="${comm.c_seq}>댓글 삭제</a>
										<input type="hidden" name="b_seq" id="b_seq" value="${comm.b_seq}">
										<input type="hidden" name="c_seq" id="c_seq" value="${comm.c_seq}">
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!--TODO Connect session ID -->
<c:choose>
	<c:when test="${ sessionScope.sessionID == {dto.id} }">
		<div align="center">
			<button type="button"
				onclick="update(${dto.seq})">수정</button>
			<button type="button"
				onclick="del(${dto.seq})">삭제</button>
		</div>
	</c:when>
	<c:when test="${ sessionScope.sessionID != dto.id }">
		<div></div>
	</c:when>
</c:choose>







<%-- <form action="news?detail" method="get">
	<input type="hidden" name="seq" id="seq" value="${comm.c_seq}"> 
	<input type="hidden" name="content" id="content value="${comm.content}"> 
	<input type="hidden" name="id" id="id" value="${ sessionScope.sessionID }">
		<textarea placeholder="댓글 작성" name="reply_content" id="reply_content"></textarea>
			<input class="submit" type="submit" value="댓글등록">
</form> 
	
<div class="comm-body">
  <c:choose>
    <c:when test="${ dto.comm eq 0 }">
     <div class="result">첫번째 댓글의 주인공이 돼 보세요!</div>
        </c:when>
           <c:otherwise>
             <div class="no-result"> 
             ㅎㅎㅎㅎ
             </div>
           </c:otherwise>
       </c:choose>
      </div>    
   <h2>dd</h2>

</div> --%>




	<%-- 
<%
String cseq = request.getParameter("c_seq");
int c_seq = Integer.parseInt(cseq);
System.out.println("c_seq="+c_seq);
%>

<%
/* Member_Dto mem = (Member_Dto)session.getAttribute("sessionID"); */

List<news_comm_dto> clist = (List<news_comm_dto>)request.getAttribute("clist");
System.out.println("clist = "+clist);

news_comm_dao dao2 = news_comm_dao.getInstance();
//dao.readcount(seq);
news_comm_dto dto2 = (news_comm_dto)request.getAttribute("dto2");
System.out.println("dto2 abc= "+dto2);

%>

<% 
news_comm_dao dao2 = news_comm_dao.getInstance();
news_comm_dto dto2 = (news_comm_dto)request.getAttribute("dto2");

List<news_comm_dto> clist = (List<news_comm_dto>)request.getAttribute("clist");

%>

<%
System.out.println(clist);


System.out.println("news comm dto" +dto2);
%>

<%
String ccseq = request.getParameter("c_seq");
int c_seq = Integer.parseInt(ccseq);
System.out.println("c_seq="+c_seq);
%>


<%
if(clist.size() == 0){	// 하나도 없다
	%>
	<tr bgcolor="#f6f6f6">
		<td colspan="3" align="center">고객 리스트가 존재하지 않습니다</td>
	</tr>
	<tr>
		<td height="2" bgcolor="#0000ff" colspan="3"></td>
	</tr>
	<%
}else{	// 있다
	for(int i = 0;i < clist.size(); i++){
		dto2 = clist.get(i);
		%>
		<tr bgcolor="#f6f6f6">
			<td align="center" bgcolor="yellow">
				<input type="checkbox" name="delck" value="<%=dto2.getId() %>">
			</td>		
			<td>
				<%=dto2.getId() %>
			</td>
			<td>
					<%=dto2.getContent()%>
			</td>
		</tr>
		<tr>
			<td height="2" bgcolor="#0000ff" colspan="3"></td>
		</tr>		
		<%
	}
}
%>

<form action="news?work=detail" method="get">
	<input type="hidden" name="b_seq" value="2"/>
	 <input type="hidden" name="c_seq" value="<%=dto2.getC_seq()%>"/> 
		
		<td><input type="text" name="c_content"/>
			<input type="submit" value="댓글 작성"/>
		</td>

</form>
 --%>
	<%@ include file="module/footer.jsp"%>