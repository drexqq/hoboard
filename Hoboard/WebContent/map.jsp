<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="module/header.jsp"%>
<div class="map-wrap clearfix">
  <aside class="side-list">
    <div class="title">등록된 병원</div>
    <div class="enroll-wrap">
      <div class="list-wrap">
      	<c:forEach items="${ busiMembers }" var="mem">
	      		<a href="${ mem[4] }" class="list">
		          <div class="list-upper clearfix">
		            <div class="tit">${ mem[0] }</div>
		            <div class="tel"><i class="ri-phone-line"></i>${ mem[1] }</div>
		          </div>
		          <div class="list-down clearfix">
		            <div class="address">
		              ${ mem[2] } ${ mem[3] }
		            </div>
		          </div>
		        </a>
      	</c:forEach>
      </div>
      <div class="paging-wrap">
      </div>
    </div>
    <div class="title">근처 병원들</div>
    <div class="api-wrap" id="menu_wrap">
      <div class="list-wrap" id="placesList">
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div> </a
        ><a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div> </a
        ><a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
        <a href="#" class="list">
          <div class="list-title">this is title</div>
          <div class="list-desc">this is desc</div>
        </a>
      </div>
      <div class="paging-wrap" id="pagination">
      </div>
    </div>
  </aside>
  <article class="map" id="map"></article>
</div>
<script src="js/map.js"></script>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21f8fdc98dc408e8aa74e8b850e26f9e&libraries=services"
></script>
<script>
  // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.4923615, 127.02928809999999), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

  // 지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

  // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
  if (navigator.geolocation) {
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function (position) {
      var lat = position.coords.latitude, // 위도
        lon = position.coords.longitude; // 경도

      var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        message = '<div style="padding:5px;">현재위치입니다!</div>'; // 인포윈도우에 표시될 내용입니다

      // 마커와 인포윈도우를 표시합니다
      displayMarker(locPosition, message);
    });
  } else {
    // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
      message = "geolocation을 사용할수 없어요..";

    displayMarker(locPosition, message);
  }

  // 지도에 마커와 인포윈도우를 표시하는 함수입니다
  function displayMarker(locPosition, message) {
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map,
      position: locPosition,
    });

    var iwContent = message, // 인포윈도우에 표시할 내용
      iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
      content: iwContent,
      removable: iwRemoveable,
    });

    // 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);

    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
  }

  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places(map);

  // 카테고리로 은행을 검색합니다
  ps.categorySearch("HP8", placesSearchCB, { useMapBounds: true });

  // 마커배열
  var markers = [];

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      // 정상적으로 검색이 완료됐으면
      // 검색 목록과 마커를 표출합니다
      displayPlaces(data);

      // 페이지 번호를 표출합니다
      displayPagination(pagination);
    }
  }
  function displayPlaces(places) {
    var listEl = document.getElementById("placesList"),
      menuEl = document.getElementById("menu_wrap"),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds(),
      listStr = "";

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    var markerPosition = new kakao.maps.LatLng(37.4923615, 127.02928809999999);
    // 마커를 생성합니다
    for (var i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시합니다
      var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시합니다
      // mouseout 했을 때는 인포윈도우를 닫습니다
      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
          infowindow.close();
        };
      })(marker, places[i].place_name);

      fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    // map.setBounds(bounds);
  }

  // 검색결과 항목을 Element로 반환하는 함수입니다
  function getListItem(index, places) {
    var el = document.createElement("a"),
      itemStr =
        '<div class="list-upper clearfix">' +
        '<div class="tit">' +
        '<span>'+(index+1)+'</span>' + 
        places.place_name +
        "</div>" +
        '<div class="tel"><i class="ri-phone-line"></i>' +
        places.phone +
        "</div>" +
        "</div>" +
        '<div class="list-down">' +
        '<div class="address">' +
        places.road_address_name +
        places.address_name +
        "</div>" +
        "</div>";

    el.innerHTML = itemStr;
    el.className = "list";
    el.href = "#";

    return el;
  }

  // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
  function addMarker(position, idx, title) {
    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  }

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  }

  // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
  function displayPagination(pagination) {
    var paginationEl = document.getElementById("pagination"),
      fragment = document.createDocumentFragment(),
      i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
      paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      var el = document.createElement("a");
      el.className = "page";
      el.href = "#";
      el.innerHTML = i;

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  }

  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
  // 인포윈도우에 장소명을 표시합니다
  function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
  }

  // 검색결과 목록의 자식 Element를 제거하는 함수입니다
  function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
      el.removeChild(el.lastChild);
    }
  }

  function panTo() {
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(33.450580, 126.574942);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);            
}  
</script>
<%@ include file="module/footer.jsp"%>
