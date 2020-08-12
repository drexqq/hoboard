<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="review.Review_Dto"%>
<%@page import="java.util.List"%>

<%@ include file="module/header.jsp"%>
<%-- //���Ƿα���
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){
	%>
	<script type="text/javascript">
	alert("�α��� �� �ֽʽÿ�");
	location.href = "login.jsp";
	</script>	
	<%
}

mem = (MemberDto)ologin;
%> --%>


<%
List<Review_Dto> list = (List<Review_Dto>)request.getAttribute("reviewlist");

System.out.println(list);

%>



<%-- //�˻�
<%

String searchWord = request.getParameter("searchWord");
String choice = request.getParameter("choice");

if(choice == null || choice.equals("")){
	choice = "sel";
}
// �˻�� �������� �ʰ� Choice�� �Ѿ� ���� ��
if(choice.equals("sel")){
	searchWord = "";
}
if(searchWord == null){
	searchWord = "";
	choice = "sel";
}

%> --%>

<input type="text" name="text"><button>�˻�</button> 
&nbsp;&nbsp;&nbsp;
<select id="sel">
	<option value="1" selected="selected">����</option>
	<option value="2">����</option>
	<option value="3">�����̸�</option>
	<option value="4">�ۼ���</option>
	<option value="5">����</option>	
	<option value="6">����</option>
</select>
<br><br>

����Ʈ Ȯ��
<table border="1">
<%-- <%
for(int i=0; i<5; i++){
%>
<table border="1">
<col width="70"><col width="70"><col width="70">
<tr>
	<th>[=busi_name%>�����̸�]</th><th>TITLE</th><td><a href="review_detail.jsp">�������ѳ�����</a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th>name</th><td>ȫ��..</td>
	<!-- W_DATE     --> <td>17:00</td>
	<!-- VIEW COUNT --> <td>5ȸ</td>
	<!-- SCORE      --> <td>4.5����</td>
</tr>	

<%
}
%> --%>

<%-- <%
if(list.size() == 0){
	%>
	<tr>
		<td colspan = "3" align="center">�ۼ��� �ıⰡ �������� �ʽ��ϴ�.</td>
	</tr>
<%
}else{
	for(int i=0; i < list.size(); i++){
		Review_Dto rev = list.get(i);
	%>

<tr>
	<th><%=rev.getBusi_id()%></th><th><%=rev.getTitle()%></th><td><a href="review_detail.jsp"><%=rev.getContent()%></a></td>
</tr>
<tr>	
	<!-- INDVD_NAME --> <th><%=rev.getIndvd_id()%></th>
	<!-- W_DATE     --> <td><%=rev.getW_date() %></td>
	<!-- VIEW COUNT --> <td><%=rev.getViewcount() %></td>
	<!-- SCORE      --> <td><%=rev.getScore() %></td>
</tr>

	<%
	}
}
%> --%>

</table>

<!-- TODO -->
<button type="button" onclick="writeBbs(<%-- <%=dao.getSeq() %> --%>)">�۾���</button>

<script type="text/javascript">
window.onload = function() {
	location.href = "REVIEW";
}

</script>

<%@ include file="module/footer.jsp"%>