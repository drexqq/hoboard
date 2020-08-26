<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="mypage-wrap reserve">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="title-wrap clearfix">
          <h2 class="page-title">예약내역</h2>
          <div class="search-wrap">
            <div class="select-wrap clearfix">
              <select id="choice">
                <option value="name">병원이름</option>
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
                value=""
                onkeydown="enter('myreserve')"
              />
              <button class="btn" onclick="search('myreserve')">
                <i class="ri-search-line"></i>
              </button>
            </div>
          </div>
        </div>
        <div class="util-wrap clearfix">
          <div class="notice">총 "${ count }" 건의 예약을 하셨습니다.</div>
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
<script src="js/util.js"></script>
<script>
  function getEventList() {
	  var events = [];
    $.ajax({
      url: "myreserve",
      method: "POST",
      dataType: "json",
      success: function (data) {
    	  console.log(data);
    	  
        if (data.length != 0) {
          $.each(data, function (k, v) {
        	  let obj = {
        	              title: k,
        	              id: v.reserve_seq,
        	              start: v.reserve_date,
        	              end: v.reserve_date,
        	  }
          events.push(data);
          });
        }
      },
    });
    return events;
  }
  
  document.addEventListener("DOMContentLoaded", function () {
	console.log(getEventList());
    var calendarEl = document.getElementById("calendar");
    var a = [
      {
        title: "asdf",
        start: "2020-08-27",
        end: "2020-08-28",
      },
      {
        title: "asdf",
        start: "2020-08-28",
        end: "2020-08-29",
      },
      {
        title: "asdf",
        start: "2020-08-30",
        end: "2020-08-31",
      },
    ];
    console.log(a)
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: "prev,next today",
        center: "title",
        right: "dayGridMonth,timeGridWeek,timeGridDay",
      },
      events: a,
      dateClick: function (info) {
        console.log(info);
      },
      select: function (info) {},
    });

    calendar.render();
  });
</script>
<%@ include file="module/footer.jsp"%>
