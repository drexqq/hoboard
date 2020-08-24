<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<div class="reservedetail">
	<div class="cntainer">
		<div class="row">
			<div class="col-12">
				<div class="list-wrap clearfix">
					<div align="right">
						<button class="btn btn-primary btn-lg" data-toggle="modal"
							data-target="#myModal">예약하기</button>
					</div>
					
					
					
					<div class="MAP" align="right">MAP</div>
					<c:forEach items="${ memberlist }" var="m_list" varStatus="status"
						begin="0" end="1">
						
						<div class="name" >병원이름 :${ m_list.name }</div>
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

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">예약하기 팝업창</h4>
			</div>
			<div class="modal-body">
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
				<div>
				 <script type="text/javascript">
				$(document).ready(function(){ 
				
					calendar();
					
				$('.today,.workday,.satday,.sunday').click(function(){	
					
					var nowDate = new Date();
					 var nYear = nowDate.getFullYear();      
					 var nMonth = nowDate.getMonth() ;       
					 var nDate = nowDate.getDate();           
					 var nNumday = nowDate.getDay();
					 var clickday = $("#selData").val();
					
					 var nowday = nYear + nMonth + nDate + nNumday;
					 
					alert("오늘의요일" + nowday + "/" +  "클릭한요일" + clickday);
					
					if( nowday  ==  clickday || nowday  >  clickday ){
						
						alert("당일 예약 및 지난 일짜는 선택 하실 수 없습니다.");
						
						 document.getElementById('selDate').value = "당일 예약 및 지난 일짜는 선택 하실 수 없습니다.";  
						
					}else{
						
						
						$.ajax({
							url:"reserve?key=select",
							type:"GET",
							data:{date:$("#selDate").val(), id:$("#id").val()},
							success:function( json ){
								
								
								if(lunch.equals("휴무일입니다.")){
								
									
								}
								
							},
							error:function(){
								alert("error");
							}
						});
					}
				});
			});
				
				function calendar(tYear,tMonth){ //달력 함수  
					  
				    var nowDate = new Date();               //오늘 날짜 객체 선언  
				    var nYear = nowDate.getFullYear();      //오늘의 년도  
				    var nMonth = nowDate.getMonth() ;       //오늘의 월 ※ 0월부터 시작  
				    var nDate = nowDate.getDate();           //오늘의 날  
				    var nNumday = nowDate.getDay();         //오늘의 요일 0=일요일...6=토요일  
				    var endDay = new Array(31,28,31,30,31,30,31,31,30,31,30,31);      //각달의 마지막 날짜  
				    var dayName = new Array("일", "월", "화", "수", "목", "금", "토"); // 숫자 요일을 문자 요일 바꿀 함수  
				    var col=0;  //나중에 앞뒤 빈 날짜칸 계산   
				  
				    if (tYear==null)   //null 일경우, 처음 페이지가 로드 될때의 년도는   
				        {tYear=nYear;} // 현재 년도를 가져오고  
				  
				    if (tMonth==null)   //null 일경우, 처음 페이지가 로드 될때의 월은  
				        {tMonth=nMonth;}//현재 월을 가져오고  
				  
				        eDate= new Date();       // 변경된 날짜 객체 선언  
				        eDate.setFullYear(tYear);// 변경된 년도 세팅  
				        eDate.setMonth(tMonth);  // 변경된 월 세팅  
				        eDate.setDate(1);        // 날짜는 1일로 설정해서  
				        var fNumday=eDate.getDay();    // 첫번째 날짜 1일의 숫자 요일  
				        var lastDay=endDay[eDate.getMonth()]; //변경된 월의 마지막 날짜  
				  
				        if ((eDate.getMonth()==1)&&(((eDate.getYear()%4==0)&&(eDate.getYear() %100 !=0))||eDate.getYear() % 400 ==0 ))  
				            {lastDay=29;} // 0월 부터 시작하므로 1는 2월임. 윤달 계산 4년마다 29일 , 100년는 28일, 400년 째는 29일  
				  
				        calendarStr  = "<TABLE>"  
				        calendarStr += "<TR align=center><TD valign=middle>"  
				        calendarStr += "<a href=javascript:calendar("+tYear+","+(tMonth-1)+") class=preNext>◀</a>" //월을 넘길때 빼기 -1을 해서 넘긴다(년도는 자동 계산됨)  
				        calendarStr += "</TD><TD colspan=5>"  
				        calendarStr += "<font size=3 color=black><b>"+eDate.getFullYear()+"년 "+(eDate.getMonth()+1)+"월</b></font> "// 해당하는 년도와 월 표시  
				        calendarStr += "</TD><TD valign=middle>"  
				        calendarStr += "<a href=javascript:calendar("+tYear+","+(tMonth+1)+") class=preNext>▶</a>" //월을 넘길때 더하기 +1을 해서 넘긴다(년도는 자동 계산됨)  
				        calendarStr += "</TD></TR><TR>"  
				        for (i=0;i<dayName.length;i++){            
				            calendarStr +="<TD class=week>"+dayName[i] + "</TD>" // 숫자 요일을 날짜 요일로 입력  
				        }  
				  
				        calendarStr +="</TR><TR align=center>"  
				  
				        for (i=0;i<fNumday;i++){          // 첫번째 날짜의 숫자 요일을 구해서 그전까지는 빈칸 처리  
				            calendarStr +="<TD>&nbsp;</TD>"   
				            col++;                       
				        }  
				  
				        for ( i=1; i<=lastDay; i++){       // 해당 월의 달력   
				            if(eDate.getFullYear()==nYear&&eDate.getMonth()==nMonth&&i==nDate){//오늘이면 today 스타일로 표시  
				                calendarStr +="<TD class=today onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"   
				            }else{  
				  
				                if(col==0){              //일요일이면  
				                    //calendarStr +="<TD class=sunday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"  
				                    calendarStr +="<TD class=sunday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"  
				                }else if(1<=col&&col<=5){//그외 평범한 날이면  
				                    //calendarStr +="<TD class=workday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"   
				                    calendarStr +="<TD class=workday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"   
				                }else if(col==6){        //토요일이면  
				                    //calendarStr +="<TD class=satday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"   
				                    calendarStr +="<TD class=satday onClick=datePicker("+tYear+","+tMonth+","+i+",'"+dayName[col]+"')>"+i+"</TD>"   
				                }  
				  
				            }             
				            col++;  
				  
				            if(col==7){     //7칸을 만들면 줄 바꾸어 새 줄을 만들고 다시 첫 칸부터 시작  
				                calendarStr +="</TR><TR align=center>"  
				                col=0;  
				            }  
				        }     
				  
				        for (i=col;i<dayName.length;i++){        //마지막 날에서 남은 요일의 빈 칸 만들기  
				            calendarStr +="<TD>&nbsp;</TD>"  
				        }  
				  
				  		
				        
				        calendarStr += "</TR><TR align=center><TD colspan=7><input id=selDate class=selDate type=text readonly>"  
				        calendarStr += "<input id=selData class=selData type=hidden>" 
				        calendarStr +="<input id=id value=${ busi_id } type=hidden>"
				        
				        //여기가 숫자를 누르면 재생성되는곳,,
				        calendarStr += "<TD><SELECT><OPTION>안됨</OPTION><OPTION>안된다고</OPTION></SELECT></TD></TR></TABLE>"  
				        document.getElementById('calendarView').innerHTML = calendarStr  
				}
			
				
				function datePicker(tYear,tMonth,tDay,tYoil){ // 텍스트박스에 날짜 넣기 위해 만든 함수
					
				    picDate = new Date(tYear,tMonth,tDay);      // 변경된 날짜 객체 선언후 날짜셋팅 
				    document.getElementById('selDate').value = picDate.getFullYear()+"년 "+(picDate.getMonth()+1)+"월 "+picDate.getDate()+"일"+"("+tYoil+"요일)"   
				    document.getElementById('selData').value = picDate.getFullYear()+(picDate.getMonth()+1)+picDate.getDate() 
				    //document.getElementById('id').value = ${ busi_id } + "";
				}  


				</script>
				</div>
				<div id="calendarView"></div>
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
</script>
	<%@ include file="module/footer.jsp"%>