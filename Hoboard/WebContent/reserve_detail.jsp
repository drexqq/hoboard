<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="module/header.jsp"%>



<div class="reservedetail">
	<div class="cntainer">
		<div class="row">
			<div class="col-12">
				<div class="title-wrap clearfix">
					<h3 class="title">병원 이름</h3>
					<span class="grade"> 
						<i class="ri-star-smile-line"></i> ${ score }/ 5
					</span>
					<div class="list-wrap clearfix">
						<c:forEach items="${ detaillist }" var="d_list" varStatus="status"
							begin="0" end="1">

							<div class="homepage">홈페이지 : ${ d_list.homepage }</div>
							<div class="tel">전화번호 : ${ d_list.tel }</div>
						
						
						</c:forEach>
						<!-- 여기는 월 화 수 목 금 토 일 -->
						<c:forEach items="${ timellist }" var="t_list" varStatus="status"
							begin="0" end="1">
							<div class="busi_tiem">${ t_list.mon }</div>
							<div class="mon">${ t_list.mon }</div>
							<div class="tue">${ t_list.tue }</div>
							<div class="wed">${ t_list.wed }</div>
							<div class="thu">${ t_list.thu }</div>
							<div class="fri">${ t_list.fri }</div>
							<div class="sat">${ t_list.sat }</div>
							<div class="sun">${ t_list.sun }</div>
							<div class="lunch">${ t_list.lunch }</div>
							<!-- 필요할 경우 사용  -->
							<div class="holy">${ t_list.holiday }</div>
							<div class="night">${ t_list.night }</div>
							<div class="emer">${ t_list.emer }</div>

							<div class="address" align="right">주소</div>
						</c:forEach>

						<c:forEach items="${ catelist }" var="c_list" varStatus="status"
							begin="0" end="1">
							<div class="busi_cate">진료 분야 :</div>
							<div class="busi_internal">${ c_list.internal }</div>
							<div class="busi_anpn">${ c_list.anpn }</div>
							<div class="busi_mtrnt">${ c_list.mtrnt }</div>
							<div class="busi_pdtrc">${ c_list.pdtrc }</div>
							<div class="busi_nrlgy">${ c_list.nrlgy }</div>
							<div class="busi_nrsrg">${ c_list.nrsrg }</div>
							<div class="busi_crdlg">${ c_list.crdlg }</div>
							<div class="busi_xray">${ c_list.xray }</div>
							<div class="busi_gs">${ c_list.gs }</div>
							<div class="busi_dprtm">${ c_list.dprtm }</div>
							<div class="busi_os">${ c_list.os }</div>
							<div class="busi_rhblt">${ c_list.rhblt }</div>
							<div class="busi_thrcc">${ c_list.thrcc }</div>
							<div class="busi_skin_uro">${ c_list.skin_uro }</div>
							<div class="busi_dent">${ c_list.dent }</div>
						</c:forEach>

						<c:forEach items="${ amenitylist }" var="a_list"
							varStatus="status" begin="0" end="1">
							<div class="busi_amenity">편의시설:</div>
							<div class="busi_parking">${a_list.parking }</div>
							<div class="busi_conv">${a_list.conv }</div>
							<div class="busi_bank">${a_list.bank }</div>
							<div class="busi_drug">${a_list.drug }</div>
							<div class="busi_bmw">${a_list.bmw }</div>
						</c:forEach>

						<div class="picture" align="right">그림</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>






<%@ include file="module/footer.jsp"%>
