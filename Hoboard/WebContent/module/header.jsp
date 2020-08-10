<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hoboard</title>
    <!-- Bootstrap CDN -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />
    <!-- font / NanumSquare -->
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css"
    />
    <!-- remix-icon CDN -->
    <link
      href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
      rel="stylesheet"
    />
    <!-- reset.css -->
    <link rel="stylesheet" href="css/reset.css" />
    <!-- style.css -->
    <link rel="stylesheet" href="css/module.css" />
    <link rel="stylesheet" href="css/style.css" />
    <!-- layout.css -->
    <link rel="stylesheet" href="css/comp.css" />
  </head>
  <body>
  <!-- header -->
    <header>
      <div class="container">
        <div class="row">
          <div class="col-12">
            <!-- header -->
            <div class="header clearfix">
              <!-- logo -->
              <div class="logo-wrap">
                <a href="/" class="logo">
                  <img src="" alt="Logo" />
                </a>
              </div>
              <!-- //logo -->

              <!-- menu -->
              <div class="menu-wrap">
                <ul class="menu clearfix">
                  <li class="menu-item"><a href="map.jsp">근처 병원 찾기</a></li>
                  <li class="menu-item"><a href="res_list.jsp">예약하기</a></li>
                  <li class="menu-item"><a href="review.jsp">후기보기</a></li>
                  <li class="menu-item"><a href="news.jsp">건강정보</a></li>
                </ul>
              </div>
              <!-- //menu -->

              <!-- util -->
              <div class="util-wrap">
                <!-- before login -->
                <ul class="util before-login clearfix">
                  <li class="util-item login"><a href="login.jsp">로그인</a></li>
                  <li class="util-item join"><a href="join.jsp">회원가입</a></li>
                </ul>
                <!-- after login -->
                <ul class="util after-login clearfix">
                  <li class="util-item mypage"><a href="mypage.jsp">마이페이지</a></li>
                  <li class="util-item logout"><a href="">로그아웃</a></li>
                </ul>
              </div>
              <!-- //util -->
            </div>
          </div>
        </div>
      </div>
    </header>