<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  
  <link rel="stylesheet" href="/css/css.css">
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
  
  
  <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
</head>
<body>
  <div class="container" id="wrrap">
    <div class="title-part">
      <div class="title"><h2>${house.title}</h2></div>
      <div class="sub">
        <div>
       	  <c:set var="houseAddr" value="${house.addr}" />
      	  <c:set var="si" value="${fn:split(houseAddr,' ')[0]}" />
      	  <c:set var="gu" value="${fn:split(houseAddr,' ')[1]}" />
      	  <span id="houseId" hidden>${house.id}</span>
      	  <span id="userId" hidden>${principal.user.id}</span>
      	  
          <p style="float: left;">*4.86. <a href="#review" class="scroll-move">후기 ${house.reviewcount}개</a>. <a href="#location" class="scroll-move">${si},${gu}</a></p>
          <p style="float: right;"><a href="">공유하기</a></p>
          <p style="float: right;"><a href="">저장</a></p>
        </div>
      </div>
    </div>

    <div class="img-part">
        <div><img src="/auth/img?imgPath=${house.img1}" alt="" class="img3"></div>
        <div><img src="/auth/img?imgPath=${house.img2}" alt="" class="img3"></div>
        <div><img src="/auth/img?imgPath=${house.img3}" alt="" class="img3"></div>
        <div><img src="/auth/img?imgPath=${house.img4}" alt="" class="img3"></div>
        <div><img src="/auth/img?imgPath=${house.img5}" alt="" class="img3"></div>
    </div>

    <div class="content1">
      <div class="sub-title">
      	<c:set var="category" value="${house.category}"/>
      	<c:set var="upper1" value="${fn:toUpperCase(category)}" />
      	<c:set var="type" value="${house.type}"/>
      	<c:set var="upper2" value="${fn:toUpperCase(type)}" />
        <div class="title">${house.users.nickname} 님이 호스팅하는 ${upper1}의 ${upper2}</div>
        <div class="img">
          <img src="${house.users.profileimage}" alt="프로필사진" class="img-fluid">
        </div>
      </div>
      <hr>
  
      <div>
        <p>숙소 특징 설명 공간</p>
        <hr>
      </div>

      <div class="exp">
        <p>${house.explain}</p>
        <a href="#ex2" rel="modal:open">더 보기</a>
        <div id="ex2" class="modal">
          <p>호스트의 숙소 설명 내용</p>
        </div>
        <hr>
      </div>
  
      <div>
        <h3>숙소 편의시설</h3>
        <c:set var="amenity" value="${house.amenities}"/>
        <c:set var="amenity_split" value="${fn:split(amenity,',')}"/>
        
        <div class="amenities_area">
        	<c:forEach var="am" items="${amenity_split}">
        		<c:choose>
					<c:when test="${am eq 'wifi'}">
						<div class="amenities_box">
							무선 인터넷
						</div>
					</c:when>
					<c:when test="${am eq 'tv'}">
						<div class="amenities_box">
							TV
						</div>
					</c:when>
					<c:when test="${am eq 'kitchen'}">
						<div class="amenities_box">
							주방
						</div>
					</c:when>
					<c:when test="${am eq 'washer'}">
						<div class="amenities_box">
							세탁기
						</div>
					</c:when>
					<c:when test="${am eq 'free-p'}">
						<div class="amenities_box">
							건물 내 무료 주차
						</div>
					</c:when>
					<c:when test="${am eq 'charged-p'}">
						<div class="amenities_box">
							건물 내 유료 주차
						</div>
					</c:when>
					<c:when test="${am eq 'a/c'}">
						<div class="amenities_box">
							에어컨
						</div>
					</c:when>
					<c:when test="${am eq 'pool'}">
						<div class="amenities_box">
							수영장
						</div>
					</c:when>
					<c:otherwise>
						<div class="amenities_box">
							바비큐 그릴
						</div>
					</c:otherwise>
				</c:choose>		
        	</c:forEach>
        </div>
      	<hr>
      </div>
  
      <div>
      	
        <h3>${gu}에서 <b id="schedule"></b>박</h3>
        <div>
        	<p id="schedule2"></p>
        </div>
        
      </div>
    </div>
    
    <div class="content2">
      <div class="nav">
        <div>
          <p class="float-left"><b>₩${house.fare}</b>/박</p>
          <p class="float-right ">*4.86 . <a href="#review" class="scroll-move">후기 ${house.reviewcount}개</a></p>
        </div>
        <div>
          <form autocomplete="off">
            <div class="flex-row d-flex justify-content-center">
              <div class="col-xl-5 col-lg-6 col-11 px-1">
                <div class="input-group input-daterange">
                  <label class="">Select Date Range :</label>
                  <input type="text" name="daterange" id="daterange" class="input"/>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="text-center">
          <p class="mb-0">인원 수</p>
          <input type="text" class="guest" id="total-num" value="1" readonly>
        </div>
        <div class="guest-window" id="guest-window" style="display: none;">
          <div class="group">
            <div class="sub-group1">
              <p>성인</p>
            </div>
            <div class="sub-group2">
              <input type="button" value="-" class="adult-minus guest-btn">
              <input type="text" class="vertical-m mb-0 border-0" id="adult-num" value="1" readonly>
              <input type="button" value="+" class="adult-plus guest-btn">
            </div>
          </div>
          <div class="group">
            <div class="sub-group1">
              <p>어린이</p>
            </div>
            <div class="sub-group2">
              <input type="button" value="-" class="child-minus guest-btn">
              <input type="text" class="vertical-m mb-0 border-0 child-num" value="0" readonly>
              <input type="button" value="+" class="child-plus guest-btn">
            </div>
          </div>
          <div class="group">
            <div class="sub-group1">
              <p>유아</p>
            </div>
            <div class="sub-group2">
              <input type="button" value="-" class="minus">
              <p class="vertical-m mb-0">0</p>
              <input type="button" value="+" class="plus">
            </div>
          </div>
          <div class="group">
            <div class="sub-group1">
              <p>반려동물</p>
            </div>
            <div class="sub-group2">
              <input type="button" value="-" class="minus">
              <p class="vertical-m mb-0">0</p>
              <input type="button" value="+" class="plus">
            </div>
          </div>
          <div>
            <a class="float-right exit">닫기</a>
          </div>
        </div>
        <div class="pt-16">
          <button class="reservBtn" id="reservBtn">예약하기</button>
        </div>

        <div class="inline">
          <input type="hidden" value="${house.fare}" class="houseFare">
          <p class="float-left">₩${house.fare} x <b class="schedule"></b>박</p>
          <p class="float-right">₩<b class="total_fare"></b></p>
        </div>
        <div class="">
          <p class="float-left">서비스 수수료</p>
          <p class="float-right ">₩<b class="serviceFee"></b></p>
        </div>

        <div class="totla">
          <hr>
          <p class="float-left bold">총 합계</p>
          <p class="float-right bold">₩<b class="paymentFee"></b></p>
        </div>
      </div>
    </div>

    <div class="review" id="review">
      <hr>
      <h3>$point . 후기 ${house.reviewcount}개</h3>
      <c:choose>
       	<c:when test="${empty principal}">
       		<a href="/detail/${house.id}">
       			<div class="review_input"> 
	            	<input type="text" class="" id="review-content" placeholder="댓글을 입력해주세요">
	            	<button class="btn" id="btn-review-save">등록</button>
             		<hr>
             	</div>
       		</a>
       	</c:when>
       	<c:otherwise>
       		<div class="review_input"> 
	            <input type="text" class="" id="review-content" placeholder="댓글을 입력해주세요">
	            <button class="btn float-right" id="btn-review-save">등록</button>
          		<hr>
          	</div>
       	</c:otherwise>    	
       </c:choose>
      <div class="review-content">
      	<c:forEach var="review" items="${house.review}">
      		<div class="review-box">
	          <img src="${review.users.profileimage}" alt="유저프로필사진" class="profile float-left">
	          <p class="bold mb-0">${review.users.nickname}</p>
	          <p><fmt:formatDate value="${review.createDate}" pattern="yyyy-MM-dd" /></p>
	          <p>${review.content}</p>
	        </div>
      	</c:forEach> 
      </div>
      <button>리뷰 전체 보기</button>
    </div>

    <div id="location">
      <input type="hidden" value="${house.addr}" class="useraddr">
      <hr>
      <h3>호스팅 지역</h3>
      지도영역
      <div id="map" class="map"></div>
    </div>
  </div>

  <a href="#wrrap" class="btn btn-primary back-to-top scroll-move" style="display: inline; text-decoration: none;">top</a>
  <script src="/js/detail.js"></script>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23c3141aa328befb9ec62681177ed0ac&libraries=services"></script>
  <script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};
	
		var map = new kakao.maps.Map(container, options);
  </script>
  <script>
	//지도
	let addr = document.querySelector('.useraddr').value;
	window.onload = mapspon(addr);
	
	function mapspon(addr) {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	      mapOption = {
	          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	          level: 3 // 지도의 확대 레벨
	      };  
	
	  // 지도를 생성합니다    
	  var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	  // 주소-좌표 변환 객체를 생성합니다
	  var geocoder = new kakao.maps.services.Geocoder();
	
	  // 주소로 좌표를 검색합니다
	  geocoder.addressSearch(addr, function(result, status) {
	
	      // 정상적으로 검색이 완료됐으면 
	       if (status === kakao.maps.services.Status.OK) {
	
	          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	          // 결과값으로 받은 위치를 마커로 표시합니다
	          var marker = new kakao.maps.Marker({
	              map: map,
	              position: coords
	          });
	
	          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	          map.setCenter(coords);
	      } 
	  });    
	}
	  
  </script>
  

  
</body>
</html>