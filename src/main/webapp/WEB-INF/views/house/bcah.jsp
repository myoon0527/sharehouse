<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="/css/bcah.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23c3141aa328befb9ec62681177ed0ac&libraries=services"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
</head>
<body>

	<form onsubmit="upload(event)">
		<div class="container-fluid">
			<div class="header">
				<div class="header_content">
					<div class="logo">
						<a href="">로고</a>
					</div>
					<div class="saveAndOut">
						<button type="submit" class="btn">저장 후 나가기</button>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="content">
					<div class="content_wrrap">
						<div class="title">
							<h1>다음 중 숙소를 가장 잘 설명하는 것은 무엇인가요?</h1>
						</div>
						<div class="radio_wrrap">
							<div role="radiogroup" class="selectOne flex-direction-row">
								<div class="select">
									<button role="radio" type="button" id="housing"
										class="radio_btn category clicked" value="housing"
										data-cate="true">
										<div class="space_between">
											<div class="name">주택</div>
										</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn category"
										value="apartment">
										<div class="name">아파트</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn category"
										value="campingCar">
										<div class="name">캠핑카</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn category"
										value="farm">
										<div class="name">농장</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn category"
										value="hotel">
										<div class="name">호텔</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn category"
										value="separate">
										<div class="name">게스트용 별채</div>
									</button>
								</div>
							</div>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>게스트가 사용할 숙소 유형</h1>
						</div>
						<div class="radio_wrrap">
							<div role="radiogroup" class="selectOne flex-direction-column">
								<div class="select">
									<button role="radio" type="button"
										class="radio_btn2 type clicked" value="entire"
										data-type="true">
										<div class="name">
											<h3>공간 전체</h3>
											<p>게스트가 숙소 전체를 단독으로 사용합니다.</p>
										</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn2 type"
										value="privateRoom">
										<div class="name">
											<h3>개인실</h3>
											<p>게스트는 개인실에서 숙박하지만, 일부공간은 호스트나 다른 사람과 함께 사용할 수 있습니다.</p>
										</div>
									</button>
								</div>
								<div class="select">
									<button role="radio" type="button" class="radio_btn2 type"
										value="publicRoom">
										<div class="name">
											<h3>다인실</h3>
											<p>게스트가 개인 공간 없이 호스트나 다른 사람과 함께 스는 침실이나 공용 공간에서 숙박합니다.</p>
										</div>
									</button>
								</div>
							</div>
						</div>
					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>주소 확인</h1>
							<h3>주소는 게스트의 예약이 확정된 이후에 공개됩니다.</h3>
						</div>
						<div class="radio_wrrap">
							<div class="input_box">
								<input type="text" id="addr" placeholder="주소를 입력해 주세요."
									onclick="openAddr()">
							</div>
							<div class="input_box">
								<input type="text" id="addr2" placeholder="상세주소를 입력해 주세요."
									name="detailAddr">
							</div>
							<div class="map_site">
								<div id="map" style="width: 100%; height: 350px;"></div>
							</div>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>숙소 기본 정보를 알려주세요.</h1>
						</div>
						<div class="radio_wrrap">
							<div class="group">
								<div class="sub-group1">
									<h3>게스트</h3>
								</div>
								<div class="sub-group2">
									<input type="button" value="-" class="guest-btn"
										onclick="plusMinusBtn('m',this);"> <input type="text"
										class="vertical-m mb-0 border-0 valuee" name="guest"
										id="guest" value="1" readonly> <input type="button"
										value="+" class="guest-btn" onclick="plusMinusBtn('p',this);">
								</div>
							</div>
							<div class="group">
								<div class="sub-group1">
									<h3>침실</h3>
								</div>
								<div class="sub-group2">
									<input type="button" value="-" class="minus guest-btn"
										onclick="plusMinusBtn('m',this);"> <input type="text"
										class="vertical-m mb-0 border-0 valuee" name="bedroom"
										id="bedroom" value="1" readonly> <input type="button"
										value="+" class="plus guest-btn"
										onclick="plusMinusBtn('p',this);">
								</div>
							</div>
							<div class="group">
								<div class="sub-group1">
									<h3>침대</h3>
								</div>
								<div class="sub-group2">
									<input type="button" value="-" class="minus guest-btn"
										onclick="plusMinusBtn('m',this);"> <input type="text"
										class="vertical-m mb-0 border-0 valuee" name="bed" id="bed"
										value="1" readonly> <input type="button" value="+"
										class="plus guest-btn" onclick="plusMinusBtn('p',this);">
								</div>
							</div>
							<div class="group">
								<div class="sub-group1">
									<h3>욕실</h3>
								</div>
								<div class="sub-group2">
									<input type="button" value="-" class="minus guest-btn"
										onclick="plusMinusBtn('m',this);"> <input type="text"
										class="vertical-m mb-0 border-0 valuee" name="bathroom"
										id="bathroom" value="1" readonly> <input type="button"
										value="+" class="plus guest-btn"
										onclick="plusMinusBtn('p',this);">
								</div>
							</div>
						</div>

					</div>


					<div class="content_wrrap">
						<div class="title">
							<h1>숙소 편의시설 정보를 등록하세요.</h1>
						</div>
						<div class="radio_wrrap">
							<div class="amenities_group">
								<button role="checkbox" type="button" class="amenities_btn"
									value="wifi,">무선인터넷</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="tv,">TV</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="kitchen,">주방</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="washer,">세탁기</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="free-p,">건물 내 무료 주차</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="charged-p,">건물 내 유료 주차</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="a/c,">에어컨</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="pool,">수영장</button>
								<button role="checkbox" type="button" class="amenities_btn"
									value="grill,">바비큐 그릴</button>
							</div>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>숙소 사진 추가하기</h1>
							<h3>숙소 등록을 시작하려면 사진 5장을 제출하셔야합니다.</h3>
						</div>
						<div class="radio_wrrap">
							<div class="img_area_wrrap">
								<div class="img_area">
									<div class="img upload"
										onclick="document.getElementById('file').click()">
										<input type="file" id="file" class="d-none files"
											onchange="PreviewImage(1)"> <img
											src="/img/plus.png" alt="" id="img1" name="file"
											class="user_img">
									</div>
									<div class="img upload"
										onclick="document.getElementById('file2').click()">
										<input type="file" id="file2" class="d-none files"
											onchange="PreviewImage(2)"> <img
											src="/img/plus.png" alt="" id="img2" name="file"
											class="user_img">
									</div>
									<div class="img upload"
										onclick="document.getElementById('file3').click()">
										<input type="file" id="file3" class="d-none files"
											onchange="PreviewImage(3)"> <img
											src="/img/plus.png" alt="" id="img3" name="file"
											class="user_img">
									</div>
									<div class="img upload"
										onclick="document.getElementById('file4').click()">
										<input type="file" id="file4" class="d-none files"
											onchange="PreviewImage(4)"> <img
											src="/img/plus.png" alt="" id="img4" name="file"
											class="user_img">
									</div>
									<div class="img upload"
										onclick="document.getElementById('file5').click()">
										<input type="file" id="file5" class="d-none files"
											onchange="PreviewImage(5)"> <img
											src="/img/plus.png" alt="" id="img5" name="file"
											class="user_img">
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>숙소의 이름을 지어주세요</h1>
						</div>
						<div class="radio_wrrap">
							<div class="text_area">
								<textarea name="" id="title" cols="30" rows="10"
									style="resize: none;" placeholder="숙소의 이름은 짧을수록 효과적입니다."></textarea>
							</div>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>숙소 설명 작성하기</h1>
						</div>
						<div class="radio_wrrap">
							<div class="text_area">
								<textarea name="" id="explain" cols="30" rows="10"
									style="resize: none;" placeholder="숙소의 특징과 장점을 알려주세요."></textarea>
							</div>
						</div>

					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>요금 설정하기</h1>
						</div>
						<div class="radio_wrrap">
							<div class="fare_area">
								<div class="fare">
									<input type="button" value="-" class="adult-minus fare-btn"
										onclick="plusMinusBtn2('m', this);"> <input
										type="text" class="vertical-m mb-0" id="fare" name="fare"
										numberOnlyMinComma="true" KoreanCurrency="true"
										value="20,000원"> <input type="button" value="+"
										class="adult-plus fare-btn"
										onclick="plusMinusBtn2('p', this);">
								</div>
								<p class="text-center" style="margin: 0;">/박</p>
							</div>
						</div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>

					<div class="content_wrrap">
						<div class="title">
							<h1>숙소 검토하기</h1>
							<h3>게스트에게 표시되는 정보는 다음과 같습니다. 모든 정보가 정확한지 확인하세요.</h3>
						</div>
						<div class="radio_wrrap2">
							<div class="preview_area"></div>
							<div class="next_step"></div>
						</div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>

				</div>
			</div>
	</form>

	<div class="footer">
		<div class="progressBar">
			<div style="width: 100%; transition: all 2s;">
				<progress max="100" value="25" id="progress1"></progress>
				<progress max="100" value="0" id="progress2"></progress>
				<progress max="100" value="0" id="progress3"></progress>
			</div>
		</div>
		<div class="footer_content">
			<div class="back_btn">
				<button type="button" class="pageMove_btn" onclick="scrollUp()">뒤로</button>
			</div>
			<div class="next_btn">
				<button type="button" class="pageMove_btn" onclick="scrollDown()">다음</button>
			</div>
		</div>
	</div>
	</div>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		let click = document.querySelectorAll('.radio_btn');
		click.forEach(function(item) {
			item.addEventListener('click', function() {
				click.forEach(function(e) {
					e.classList.remove('clicked');
				});
				item.classList.add('clicked');
			});
		});

		let click2 = document.querySelectorAll('.radio_btn2');
		click2.forEach(function(item) {
			item.addEventListener('click', function() {
				click2.forEach(function(e) {
					e.classList.remove('clicked');
				});
				item.classList.add('clicked');
			});
		});

		function scrollDown() {
			window.scrollBy(0, 980);
			if (document.getElementById('progress1').value < 100) {
				document.getElementById('progress1').value += 25;
			} else if (document.getElementById('progress2').value < 100) {
				document.getElementById('progress2').value += 25;
			} else {
				document.getElementById('progress3').value += 50;
			}
		}

		function scrollUp() {
			window.scrollBy(0, -980);
			if (document.getElementById('progress3').value >= 50) {
				document.getElementById('progress3').value -= 50;
			} else if (document.getElementById('progress2').value >= 25) {
				document.getElementById('progress2').value -= 25;
			} else if (document.getElementById('progress1').value > 25) {
				document.getElementById('progress1').value -= 25;
			}
		}

		function openAddr() {
			new daum.Postcode({
				oncomplete : function(data) { //선택시 입력값 세팅
					document.getElementById("addr").value = data.address; // 주소 넣기
					//console.log(data.address);
					//console.log(document.getElementById("addr").value);

					document.querySelector("input[name=detailAddr]").focus(); //상세입력 포커싱

					mapspon(data.address);
				}
			}).open();
		}

		let click3 = document.querySelectorAll('.amenities_btn');
		click3.forEach(function(item) {
			item.addEventListener('click', function() {
				if (item.classList.contains('clicked')) {
					item.classList.remove('clicked');
				} else {
					item.classList.add('clicked');
					console.log(item.value);
				}
			});
		});
	</script>
	<script>
		function PreviewImage(ths) {
			// 파일리더 생성 
			var preview = new FileReader();
			var preview2 = new FileReader();
			var preview3 = new FileReader();
			var preview4 = new FileReader();
			var preview5 = new FileReader();

			if (ths == 1) {
				preview.onload = function(e) {
					// img id 값 
					let input = $("#img1");
					input.attr("src", e.target.result);

					//document.querySelector(".user_img").src = e.target.result;

				};
				preview.readAsDataURL(document.getElementById("file").files[0]);
			} else if (ths == 2) {
				preview2.onload = function(e) {
					// img id 값 
					let input2 = $("#img2");

					input2.attr("src", e.target.result);

				};
				preview2
						.readAsDataURL(document.getElementById("file2").files[0]);
			} else if (ths == 3) {
				preview3.onload = function(e) {
					// img id 값 
					let input3 = $("#img3");

					input3.attr("src", e.target.result);

				};
				preview3
						.readAsDataURL(document.getElementById("file3").files[0]);
			} else if (ths == 4) {
				preview4.onload = function(e) {
					// img id 값 
					let input4 = $("#img4");

					input4.attr("src", e.target.result);

				};
				preview4
						.readAsDataURL(document.getElementById("file4").files[0]);
			} else {
				preview5.onload = function(e) {
					// img id 값 
					let input5 = $("#img5");

					input5.attr("src", e.target.result);

				};
				preview5
						.readAsDataURL(document.getElementById("file5").files[0]);
			}

			document.querySelector('.img_area').classList.add('border-0');
		};
	</script>
	<script>
		window.onload = mapspon('경기 수원시 팔달구 매산로 1가 11-12');

		function mapspon(addr2) {
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				level : 3
			// 지도의 확대 레벨
			};

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption);

			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(addr2,
					function(result, status) {

						// 정상적으로 검색이 완료됐으면 
						if (status === kakao.maps.services.Status.OK) {

							var coords = new kakao.maps.LatLng(result[0].y,
									result[0].x);

							// 결과값으로 받은 위치를 마커로 표시합니다
							var marker = new kakao.maps.Marker({
								map : map,
								position : coords
							});

							// 인포윈도우로 장소에 대한 설명을 표시합니다
							//var infowindow = new kakao.maps.InfoWindow({
							//content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
							//});
							//infowindow.open(map, marker);

							// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
							map.setCenter(coords);
						}
					});

		}
	</script>
	<script type="text/javascript" src="/js/bcah.js"></script>
</body>
</html>