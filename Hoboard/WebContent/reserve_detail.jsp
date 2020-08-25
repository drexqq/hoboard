<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- FULLCALENDAR  -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.3.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.3.0/main.min.js'></script>


<div class="reservedetail">
	<div class="cntainer">
		<div class="row">
			<div class="col-12">
				<div class="list-wrap clearfix">
					<div align="right">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target=".bs-example-modal-lg">예약하기</button>
					</div>
					<div class="MAP" align="right">MAP</div>
					<c:forEach items="${ memberlist }" var="m_list" varStatus="status"
						begin="0" end="1">

						<div class="name">병원이름 :${ m_list.name }</div>
						<div class="tel">전화번호 : ${ m_list.tel }</div>
						<div class="address">주소: ${ m_list.address }</div>
					</c:forEach>
					<span class="grade"> <i class="ri-star-smile-line"></i> ${ score }/5
					</span>
					<div class="homepage">홈페이지 : ${ homepage }</div>

					<!-- 여기는 월 화 수 목 금 토 일 -->
					<c:forEach items="${ timelist }" var="t_list" varStatus="status"
						begin="0" end="10">
						<div class="mon">월 : ${ t_list.mon }</div>
						<div class="tue">화 : ${ t_list.tue }</div>
						<div class="wed">수 : ${ t_list.wed }</div>
						<div class="thu">목 : ${ t_list.thu }</div>
						<div class="fri">금 : ${ t_list.fri }</div>
						<div class="sat">토 : ${ t_list.sat }</div>
						<div class="sun">일 : ${ t_list.sun }</div>

						<div class="lunch">점심시간 : ${ t_list.lunch }</div>
					</c:forEach>

					<p>---------------진료과목 리스트---------------</p>
					<c:forEach items="${ catelist }" var="c_list" varStatus="status">
						<c:if test="${ c_list.value eq 1}">
							<li class="busi_cate">${ c_list.key }</li>
						</c:if>
					</c:forEach>


					<p>---------------편의시설 리스트---------------</p>
					<c:forEach items="${ amenitylist }" var="a_list" varStatus="status">
						<c:if test="${ a_list.value eq 1}">
							<li class="busi_amenity">${ a_list.key }</li>
						</c:if>
					</c:forEach>

					<div class="picture" align="right">그림</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Large modal 1PAGE -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div id="calendar"></div>
			<h4 class="modal-title" id="myModalLabel">예약하기</h4>
			<div>달력</div>
			<p>병원이름 예약하기</p>
			<p>진료과목 :</p>
			<select>
				<c:forEach items="${ catelist }" var="c_list" varStatus="status">
					<c:if test="${ c_list.value eq 1}">
						<option>${ c_list.key }</option>
					</c:if>
				</c:forEach>
			</select>
			<p>진료일</p>
			<div id='calendar'></div>
			<div>간단한 증상</div>
			<div><textarea id=cont></textarea></div> 
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">CANCLE</button>
				<button type="button" class="btn btn-primary">NEXT</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			selectable : true,
			headerToolbar : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			dateClick : function(info) {
				alert('clicked ' + info.dateStr);
			},
			select : function(info) {
				alert('selected ' + info.startStr + ' to ' + info.endStr);
			}
		});

		calendar.render();
	});
</script>
<script>
	
</script>
<%@ include file="module/footer.jsp"%>