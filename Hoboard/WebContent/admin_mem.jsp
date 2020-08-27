<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>


<<<<<<< HEAD
<div>병원 회원</div>
<c:forEach items="${ hmlist }" var="hm" varStatus="status" begin="0" end="9">
<div>${hm.id }${hm.name }${hm.tel }${hm.email }
<input type="button" name="hmdBtn" onclick="location.href='admin?adm=adminBD'" value="상세보기"/>
<input type="button" name="hmdelBtn" onclick="location.href='admin?adm=adminBDel&id=${hm.id}'" value="삭제"/>
</div>
</c:forEach>

<br><br>


<div>개인 회원</div>
<c:forEach items="${ pmlist }" var="pm" varStatus="status" begin="0" end="9">
<div>${pm.id}${pm.name}${pm.tel}${pm.email}
<button class="pmdBtn" onclick="window.open('admin?adm=adminPD&id=${pm.id}','상세 보기',
'width=1000, height=300 ,location=no,status=no,scrollbars=no');">상세 보기</button>
<input type="button" name="pmdelBtn" onclick="location.href='admin?adm=adminIDel&id=${pm.id}'" value="삭제"/>
</div>
</c:forEach>

=======
<div class="admin-mem member">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h2 class="page-title">병원 회원</h2>
					<div class="list-wrap">
						<table class="m-table">
							<thead>
								<tr>
									<th class="id">아이디</th>
									<th class="name">이름</th>
									<th class="tel">전화번호</th>
									<th class="email">이메일</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ hmlist }" var="hm" varStatus="status"
									begin="0" end="9">
									<tr>
										<td class="id">${ hm.id }</td>
										<td class="name">${ hm.name }</td>
										<td class="tel">${ hm.tel }</td>
										<td class="email">${ hm.email }</td>
										<td><input type="button" id="hmdBtn" name="hmdBtn"
											onclick="location.href='admin?adm=adminBD'" value="상세보기" /></td>
										<td><input type="button" id="hmdelBtn" name="hmdelBtn"
											onclick="location.href='#'" value="탈퇴" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<br>
						<div class="title-wrap clearfix">
							<h2 class="page-title">개인 회원</h2>
							<div class="list-wrap">
								<table class="m-table">
									<thead>
										<tr>
											<th class="id">아이디</th>
											<th class="name">이름</th>
											<th class="tel">전화번호</th>
											<th class="email">이메일</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ pmlist }" var="pm" varStatus="status"
											begin="0" end="9">
											<tr>
												<td class="id">${ pm.id }</td>
												<td class="name">${ pm.name }</td>
												<td class="tel">${ pm.tel }</td>
												<td class="email">${ pm.email }</td>
												<td><input type="button" id="hmdBtn" name="pmdBtn"
													onclick="location.href='admin?adm=adminPD'" value="상세보기" />
												</td>
												<td><input type="button" id="hmdelBtn" name="pmdelBtn"
													onclick="location.href='#'" value="탈퇴" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
>>>>>>> reserve_f
<%@ include file="module/footer.jsp"%>