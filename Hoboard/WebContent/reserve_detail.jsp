<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="reserve-detail">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="detail-wrap clearfix">
          <div class="left">
            <div class="name">
              <i class="ri-hashtag"></i> ${ map['memDto'].name }
            </div>
            <div class="tel">
              <i class="ri-phone-line"></i> ${ map['memDto'].tel }
            </div>
            <div class="homepage">
              <i class="ri-home-wifi-line"></i> ${ map['bmemDto'].homepage }
            </div>
            <div class="address">
              <i class="ri-home-line"></i> ${ map['memDto'].address } ${
              map['memDto'].d_Address }
            </div>
            <div class="grade">
              <i class="ri-star-smile-line"></i>
              ${ map['reviewDto'].score } / 5
            </div>
            <div class="reserve-btn">
              <button type="button" id="reserveBtn">
                예약하기
              </button>
            </div>
          </div>
          <div class="right">
            <div id="map">MAP</div>
          </div>
        </div>
        <div class="info-wrap clearfix">
          <div class="time-wrap">
            <div class="title">진료시간 및 점심시간</div>
            <div class="badge-wrap clearfix">
              <c:if test="${ map['timeDto'].holiday eq 1 }">
                <div class="badge holiday">공휴일진료</div>
              </c:if>
              <c:if test="${ map['timeDto'].night eq 1 }">
                <div class="badge night">야간진료</div>
              </c:if>
              <c:if test="${ map['timeDto'].emer eq 1 }">
                <div class="badge emer">응급실진료</div>
              </c:if>
            </div>
            <div class="week">
              <div class="mon">월요일 : ${ map['timeDto'].mon }</div>
              <div class="tue">화요일 : ${ map['timeDto'].tue }</div>
              <div class="wed">수요일 : ${ map['timeDto'].wed }</div>
              <div class="thu">목요일 : ${ map['timeDto'].thu }</div>
              <div class="fri">금요일 : ${ map['timeDto'].fri }</div>
              <div class="sat">토요일 : ${ map['timeDto'].sat }</div>
              <div class="sun">일요일 : ${ map['timeDto'].sun }</div>
              <div class="lunch">점심시간 : ${ map['timeDto'].lunch }</div>
            </div>
          </div>
          <div class="cate-wrap">
            <div class="title">진료과목</div>
            <div class="badge-wrap clearfix">
              <c:forEach items="${ map['cateList'] }" var="item" varStatus="i">
                <c:if test="${ item eq 1 }">
                  <div class="badge">${ cate_k[i.index] }</div>
                </c:if>
              </c:forEach>
            </div>
          </div>
          <div class="amenity-wrap">
            <div class="title">편의시설</div>
            <div class="badge-wrap clearfix">
              <c:forEach
                items="${ map['amenityList'] }"
                var="item"
                varStatus="i"
              >
                <c:if test="${ item eq 1 }">
                  <div class="badge">${ amenity_k[i.index] }</div>
                </c:if>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- <div id="reserveModal">
  <div class="modal-content">
    <h4 class="modal-title">예약하기</h4>

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
    <input type="hidden" id="busi_id" value="${busi_id}" />
    <div id="calendar"></div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default closeBtn">CANCLE</button>
      <button type="button" class="btn btn-primary nextBtn">NEXT</button>
    </div>
  </div>
</div> -->
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21f8fdc98dc408e8aa74e8b850e26f9e&libraries=services"
></script>
<script type="text/javascript">
  $("#reserveBtn").on("click", function () {
    //id : $("#busi_id").val()
    $("#reserveModal").addClass("on").fadeIn();
    var calendarEl = document.getElementById("calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
      selectable: true,
      headerToolbar: {
        left: "prev,next today",
        center: "title",
        right: "dayGridMonth,timeGridWeek,timeGridDay",
      },
      dateClick: function (info) {
        alert("clicked " + info.dateStr);
      },
      select: function (info) {
        alert("selected " + info.startStr + " to " + info.endStr);
      },
    });
    calendar.render();
  }),
    $(".closeBtn").on("click", function () {
      $("#reserveModal").removeClass("on").fadeOut();
    });
</script>
<%@ include file="module/footer.jsp"%>
