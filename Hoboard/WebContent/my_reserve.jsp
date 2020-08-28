<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="mypage-wrap reserve">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title-wrap clearfix">
          <div class="tip-wrap clearfix">
            <div class="ready"><span class="circle"></span> 접수완료</div>
            <div class="cancle"><span class="circle"></span> 접수취소</div>
            <div class="reviewOk">
              <span class="circle"></span> 후기작성대기
            </div>
            <div class="reviewDone">
              <span class="circle"></span> 후기작성완료
            </div>
          </div>
          <h2 class="page-title">예약내역</h2>
          <div class="search-wrap">
            <div class="select-wrap clearfix">
              <select id="choice">
                <option value="name">
                  <c:choose>
                    <c:when test="${ auth eq 1 }">병원이름</c:when>
                    <c:otherwise>환자이름</c:otherwise>
                  </c:choose>
                </option>
                <option value="cate">진료과</option>
              </select>
              <div class="arrow">
                <i class="ri-arrow-down-s-line"></i>
              </div>
            </div>
            <div class="input-wrap clearfix">
              <input
                type="text"
                id="search"
                placeholder="검색어를 입력해주세요."
                value="${ searchWord }"
                onkeydown="enter('myreserve')"
              />
              <button class="btn" onclick="search('myreserve')">
                <i class="ri-search-line"></i>
              </button>
            </div>
          </div>
        </div>
        <div class="util-wrap clearfix">
          <div class="notice">총 "${ count }" 건의 예약이 있습니다.</div>
          <div class="write">
            <a href="reserve">예약하기</a>
          </div>
        </div>
        <div class="calendar-wrap">
          <div id="calendar"></div>
        </div>
        <div class="paging-wrap"></div>
      </div>
    </div>
  </div>
</div>
<div id="statusModal">
  <form id="stausForm">
    <div class="modal-content">
      <div class="modal-title">진료를 완료하셨습니까 ?</div>
      <div class="modal-footer">
        <button type="button" class="btn closeBtn">취소하기</button>
        <button type="button" id="doneBtn" class="btn doneBtn">진료완료</button>
      </div>
    </div>
  </form>
</div>
<script src="js/util.js"></script>
<script>
  $(document).ready(function () {
    let c = "<c:out value='${ choice }' />";
    $("#choice")
      .find("option")
      .each(function () {
        if ($(this).val() == c) $(this).attr("selected", "selected");
      });
  });
  var events = [];
  $.when(getEventList()).done(function () {
    calendarRender();
  });

  var thisSeq = "";
  $("#doneBtn").on("click", function () {
    $.ajax({
      url: "myreserve",
      method: "POST",
      dataType: "json",
      data: {
        hidden: "status",
        seq: thisSeq,
      },
      success: function (done) {
        alert("정보가 수정되었습니다 !");
        $.when(getEventList()).done(function () {
          calendarRender();
        });
        $("#statusModal").hide();
      },
      error: function () {
        console.log("error");
      },
    });
  });
  // status change modal
  function stautsModal() {
    $("#statusModal").fadeIn();
    $("#statusModal .closeBtn").on("click", function () {
      $("#statusModal").hide();
    });
  }
  // event click
  function eventClickUser(event) {
    var exit = false;
    var time = event.el.childNodes[1].innerText;
    var cont = event.el.childNodes[2].innerText;
    thisSeq = event.event._def.publicId;
    $.each(event.el.classList, function (i, v) {
      if (v == "ready") {
        if ("${ auth }" == "2") {
          stautsModal();
        }
      } else if (v == "cancle") {
        alert("취소된 예약입니다.");
      } else if (v == "reviewOk") {
        if ("${ auth }" == "1") {
          alert("후기작성으로 이동합니다.");
          location.href = "review?d=w&seq=" + thisSeq;
        } else {
          alert("후기가 작성되지 않았습니다.");
        }
      } else if (v == "reviewDone") {
        if ("${ auth }" == "1") {
          alert("후기를 이미 작성하셨습니다.");
        }
      }
    });
  }
  // fullcalendar event status check
  function eventStatus(status) {
    if (status == 0) return "ready";
    else if (status == 1) return "cancle";
    else if (status == 2) return "reviewOk";
    else if (status == 3) return "reviewDone";
    else return "";
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
      eventClick: function (info) {
        eventClickUser(info);
      },
      dateClick: function (info) {},
      select: function (info) {},
      locale: "ko",
    });
    calendar.render();
  }
  // ajax get event list from DB
  function getEventList() {
	  events = [];
    return $.ajax({
      url: "myreserve",
      method: "POST",
      dataType: "json",
      data: {
        hidden: "event",
        choice: "<c:out value='${ choice }' />",
        searchWord: $("#search").val(),
      },
      success: function (data) {
        if (data.length != 0) {
          $.each(data, function (k, v) {
            var color = coloring(v.status);
            function coloring(color) {
              if (color == 0) return "#ff8b34";
              else if (color == 1) return "#ff5151";
              else if (color == 2) return "#3da0d7";
              else if (color == 3) return "#424242";
            }
            var eventData = {
              id: v.reserve_seq,
              title: k.substring(0, k.length - 13)+", "+v.cate,
              className: eventStatus(v.status),
              start: v.reserve_time,
              end: v.reserve_time,
              status: v.status,
              backgroundColor: color,
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
</script>
<%@ include file="module/footer.jsp"%>
