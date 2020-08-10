<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bisi_Join</title>
</head>
<body>
<form action="BUSI_Join.do" method="post">
병원 이름 	<input type="text" name="name">
<br><br>
아이디 <input type="text" name="id"><button type="button" id="id_Check">중복확인</button>
<br><br>
비밀 번호 <input type="password" name="pw">
<br><br>
비밀 번호 확인 <input type="password" name="pw_Check">
<br><br>
이메일 <input type="text" name="email">
<br><br>
전화 번호 <input type="text" name="tel">
<br><br>
주소 	<input type="text" name="post_Num">
<br>
 	<input type="text" name="address" readonly>
<br> 	
  	<input type="text" name="d_Address">
<br><br>

병원정보
<br><br>
병원로고 <input type="text" name="logo">
<button type="button" id="dayBtn" >파일첨부</button>
<br><br>
홈페이지  <input type="text" name="homepage">
<br><br>
진료시간<br>
<!-- <button type="button" id="mon" >월</button>
<button type="button" id="tue" >화</button>
<button type="button" id="wed" >수</button>
<button type="button" id="thu" >목</button>
<button type="button" id="fri" >금</button>
<button type="button" id="sat" >토</button>
<button type="button" id="sun" >일</button> -->

<input type='checkbox' id = "time" name="time" value="mon"/>월
<input type='checkbox' name="tue" value="tue" />화
<input type='checkbox' name="wed" value="wed" />수
<input type='checkbox' name="thu" value="thu" />목
<input type='checkbox' name="fri" value="fri" />금
<input type='checkbox' name="sat" value="sat" />토
<input type='checkbox' name="sun" value="sun" />일


<br>
<textarea name="r_Time"></textarea>
<br><br>
<input type='checkbox' name="lunch" value="lunch" />
점심시간	<input type="text" name="lunch">
<br><br>
<input type='checkbox' name="holiday" value="holiday" />공휴일
<input type='checkbox' name="night" value="night" />야간
<input type='checkbox' name="emer" value="emer" />응급실
<br><br>
진료 분야
<br><br>
<input type='checkbox' name="internal" value="internal" />내과
<input type='checkbox' name="anpn" value="anpn" />마취통층학과
<input type='checkbox' name="mtrnt" value="mtrnt" />산부인과
<input type='checkbox' name="rdtrc" value="rdtrc" />소아청소년과
<br>
<input type='checkbox' name="nrlgy" value="nrlgy" />신경과
<input type='checkbox' name="nrsrg" value="nrsrg" />신경외과
<input type='checkbox' name="crdlg" value="crdlg" />심장내과
<input type='checkbox' name="xray" value="xray" />영상의학과
<br>
<input type='checkbox' name="gs" value="gs" />외과
<input type='checkbox' name="dprtm" value="dprtm" />응급의학과
<input type='checkbox' name="os" value="os" />정형외과
<input type='checkbox' name="rhblt" value="rhblt" />재활의학과
<br>
<input type='checkbox' name="thrcc" value="thrcc" />흉부심장혈관학과
<input type='checkbox' name="skin_Uro" value="skin_Uro" />피부비뇨기과
<input type='checkbox' name="dent" value="dent" />치과
<input type='checkbox' name="ophth" value="ophth" />안과
<br><br>
편의 사항
<br><br>
<input type='checkbox' name="parking" value="parking" />주차장
<input type='checkbox' name="conv" value="conv" />편의점
<input type='checkbox' name="bank" value="bank" />ATM,은행
<input type='checkbox' name="drug" value="drug" />약국
<input type='checkbox' name="bmw" value="bmw" />대중 교통 이용 가능
<br><br>

호보드 이용 약관<br>
<textarea name="per_Info"></textarea>
<br><br>
병원 정보 취급 방침<br>
<textarea name="bisi_Info"></textarea>
<br><br>
<input type='checkbox' name="s_idBtn" value="s_id" />약관에 동의	
<br><br>
<input type="submit" value="작성 완료">
</form>
</body>
</html>