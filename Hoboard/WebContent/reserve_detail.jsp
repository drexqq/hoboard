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
              <i class="ri-star-smile-line"></i> ${ map['reviewDto'].score } / 5
            </div>
            <div class="reserve-btn">
              <button type="button" id="reserveBtn">예약하기</button>
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
<div id="reserveModal">
  <form id="reserveForm">
    <input type="hidden" id="busi_id" value="${ busi_id }" />
    <div class="modal-content">
      <div class="step step01">
        <h4 class="modal-title">예약하기</h4>
        <div class="inner-wrap">
          <div class="cate-wrap clearfix">
            <div class="select-title">진료과목</div>
            <div class="select-wrap clearfix">
              <select name="cate" id="cate">
                <c:forEach
                  items="${ map['cateList'] }"
                  var="item"
                  varStatus="i"
                >
                  <c:if test="${ item eq 1 }">
                    <option value="${ cate_k[i.index] }">
                      ${ cate_k[i.index] }
                    </option>
                  </c:if>
                </c:forEach>
              </select>
              <div class="arrow">
                <i class="ri-arrow-down-s-line"></i>
              </div>
            </div>
          </div>
          <div class="time-wrap clearfix">
            <div class="time-title">진료시간</div>
            <div class="select-wrap clearfix">
              <select name="time" id="time">
                <option value="09:00">09:00</option>
                <option value="10:00">10:00</option>
                <option value="11:00">11:00</option>
                <option value="12:00">12:00</option>
                <option value="13:00">13:00</option>
                <option value="14:00">14:00</option>
                <option value="15:00">15:00</option>
                <option value="16:00">16:00</option>
                <option value="17:00">17:00</option>
                <option value="18:00">18:00</option>
              </select>
              <div class="arrow">
                <i class="ri-arrow-down-s-line"></i>
              </div>
            </div>
          </div>
          <div id="calendar"></div>
          <div class="modal-footer">
            <button type="button" class="btn closeBtn">예약취소</button>
            <button type="button" class="btn nextBtn">다음단계</button>
          </div>
        </div>
      </div>
      <div class="step step02">
        <h4 class="modal-title">예약 확인</h4>
        <div class="inner-wrap">
          <div class="check-wrap">
            <div class="reserveDate">
              예약 날짜 - <span></span
              ><input type="hidden" name="reserveDate" />
            </div>
            <div class="reserveTime">
              예약 시간 - <span></span
              ><input type="hidden" name="reserveTime" />
            </div>
            <div class="reserveCate">
              예약 진료과 - <span></span
              ><input type="hidden" name="reserveCate" />
            </div>
          </div>
          <div class="text-wrap">
            <div class="text-title">간단한 증상</div>
            <textarea
              name="symptom"
              id="symptom"
              cols="30"
              rows="10"
            ></textarea>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn prevBtn">이전단계</button>
            <button type="button" id="doneBtn" class="btn doneBtn">
              예약하기
            </button>
          </div>
        </div>
      </div>
      <div class="step step03">
        <div class="inner-wrap"></div>
      </div>
    </div>
  </form>
</div>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21f8fdc98dc408e8aa74e8b850e26f9e&libraries=services"
></script>
<script>
  var dbLoad = false;
  var events = [];
  var selectedDate;
  var selectedTime;
  var selectedCate;
  // fullcalendar check date
  function dateCheck(info) {
    selectedDate = "";
    $("body *").removeClass("dateChecked");
    console.log(info.dateStr);
    var exit = false;
    $.each(info.dayEl.classList, function (i, v) {
      if (v == "fc-day-past") {
        alert("오늘 날짜보다 지난 날짜는 선택할 수 없습니다 !");
        exit = true;
        return false;
      }
    });
    if (exit) {
      return false;
    } else {
      selectedDate = info.dateStr;
      info.dayEl.classList.add("dateChecked");
    }
  }
  // fullcalenar render
  function calendarRender() {
    var calendarEl = document.getElementById("calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: "prev,next today",
        center: "title",
        right: "dayGridMonth,timeGridWeek,timeGridDay",
      },
      events: events,
      dateClick: function (info) {
        dateCheck(info);
      },
      select: function (info) {},
      locale: "ko",
    });
    calendar.render();
  }
  // ajax get event list from DB
  function getEventList() {
    return $.ajax({
      url: "reserve",
      method: "POST",
      dataType: "json",
      data: {
        hidden: "event",
        id: "<c:out value='${ busi_id }' />",
      },
      success: function (data) {
        if (data.length != 0) {
          $.each(data, function (k, v) {
            var eventData = {
              title: v.cate + "예약",
              id: v.reserve_seq,
              start: v.reserve_time,
              end: v.reserve_time,
            };
            events.push(eventData);
          });
        }
      },
      error: function () {
        console.log("error");
      },
    });
  }
  // document ready start----------
  $(document).ready(function () {
    var address = "<c:out value="${ map['memDto'].address }"/>";
    var name = "<c:out value="${ map['memDto'].name }"/>";
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        disableDoubleClickZoom: true,
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
    //지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();
    //주소로 좌표를 검색합니다
    geocoder.addressSearch(address, function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div style="width:150px;text-align:center;padding:6px 0;">' +
            name +
            "</div>",
        });
        infowindow.open(map, marker);
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        map.setDraggable(false);
        map.setZoomable(false);
      }
    });
  });
  // document ready done----------
  // modal show
  $("#reserveBtn").on("click", function () {
    var login = "${ login }";
    if (login == "" || login == null) {
      alert("로그인후 이용가능 합니다 !");
      location.href = "login.jsp";
    }
    $("#reserveModal").fadeIn();
    if (!dbLoad) {
      $.when(getEventList())
        .done(function () {
          calendarRender();
        })
        .then(function () {
          dbLoad = true;
        });
    }
  });
  // modal close
  $("#reserveModal .closeBtn").on("click", function () {
    $("#reserveModal").removeClass("on").fadeOut();
    dbLoad = false;
    events = [];
  });
  // modal next step
  $("#reserveModal .nextBtn").on("click", function () {
    selectedTime = $("#time option:selected").val();
    selectedCate = $("#cate option:selected").val();
    var selectedCateKo = $("#cate option:selected").text();
    $("input[name='reserveDate']").val(selectedDate);
    $("input[name='reserveTime']").val(selectedTime);
    $("input[name='reserveCate']").val(selectedCate);
    $(".reserveDate span").text(selectedDate);
    $(".reserveTime span").text(selectedTime);
    $(".reserveCate span").text(selectedCateKo);
    if (selectedTime != null && selectedCate != null && selectedDate != null) {
      $("#reserveModal .step01").hide();
      $("#reserveModal .step02").show();
      $("#symptom").focus();
    } else {
      alert("진료과목, 진료시간 진료 날짜를 선택해주세요 !");
    }
  });
  // modal prev step
  $("#reserveModal .prevBtn").on("click", function () {
    $("input[name='reserveDate']").val("");
    $("#reserveModal .step01").show();
    $("#reserveModal .step02").hide();
  });
  // modal send
  $("#doneBtn").on("click", function () {
    var formData = $("#reserveForm").serialize();
    $.ajax({
      url: "reserve",
      method: "POST",
      dataType: "json",
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
      data: {
        hidden: "reserve",
        data: formData,
        id: "<c:out value='${ busi_id }' />",
      },
      success: function (data) {
        alert("예약이 완료되었습니다 !");
        if (data.done) location.href = "myreserve";
      },
      error: function () {
        alert("예약에 실패하였습니다.");
        console.log("error");
      },
    });
  });
</script>
<%@ include file="module/footer.jsp"%>
