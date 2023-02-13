<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>에어비앤비</title>
  <link rel="shortcut icon" sizes="76x76" type="image/x-icon"
  href="https://a0.muscache.com/airbnb/static/logotype_favicon-21cc8e6c6a2cca43f061d2dcabdf6e58.ico">
  <link rel="stylesheet" href="/css/test.css">

  <link rel="stylesheet" href="/css/qna.css"/>
  <link rel="stylesheet" href="/css/animation.css">
  <link rel="stylesheet" href="/css/searchProduct.css">
  <!-- 달력  -->

  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
  <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

  <!-- price 범위 -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 300000,
      values: [ 75, 300000 ],
      slide: function( event, ui ) {
        $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
      }
    });
    $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
      " - $" + $( "#slider-range" ).slider( "values", 1 ) );
  } );
  </script>


</head>
<body>
  <header>

  </header>

  <section id="main">

  <div onclick="search()">
    <section id="search1">
      <div class="search_info">
        <div class="search_info1">
          어디든지
        </div>
        <div class="search_info1" style="padding-right: 15px;">
          언제든 일주일
        </div>
        <div class="search_info2">
          게스트 추가
        </div>
        <div>
          <img src="img/search_icon.png">
        </div>
      </div>
    </section>
  </div>


  <div onclick="searchBack()">
    <section id="search2">
      <div class="search2_info">
        <div class="destination">
          <spna class="search2_info_main">여행지</spna><br><input type="text" id="destination" placeholder="여행지 검색">
        </div>

        <div class="checkIn">
          <button id="checkIn"><spna class="search2_info_main">체크인&emsp;&emsp;&emsp;&emsp;체크아웃</spna><br></button>
            <span class="search2_info_sub">

	        <div id="calinder">
	          <div class="container_1 px-1 px-sm-5 mx-auto">
	            <form autocomplete="off">
	              <div class="flex-row d-flex justify-content-center">
	                <div class="col-xl-5 col-lg-6 col-11 px-1">
	                  <div class="input-group input-daterange">
	                    <label class=""></label>
	                    <input type="text" name="daterange" class="input" readonly />
	                  </div>
	                </div>
	              </div>
	            </form>
	          </div>
	        </div>
            </span>

        </div>

        <div class="person">
          <button id="checkOut"><spna class="search2_info_main">여행자</spna><br><span class="search2_info_sub">게스트추가</span></button>
        </div>
        <div id="searchExecution">
        <button type="submit">검색</button>
         
        </div>
      </div>
    </section>
  </div>

  <div id="container">

    <section id="filter">
       <form action="" id="filter_info">
        필터링 기준

        <div id="home_type">
          <h4>숙소 유형</h4>
          
          <input type="checkbox" name="hometype" id="hometypeasingle"value="privateRoom"><label for="hometypeasingle">개인실</label><br>
          <input type="checkbox" name="hometype" id="hometypeshared"value="publicRoom"><label for="hometypeshared">다인실</label>
        </div>  
        
        <div id="slidecontainer">
        	<h4>1박당 요금</h4>
        	<!--  
        	<input type="range" min="0" max="300,000" value="50" class="slider" id="myRange">
        	<p>value : <span id="value"></span></p>
        	-->
        	<p>
  			<label for="amount"><!-- Price range: --></label>
  			<input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
			</p>
			<div id="slider-range" style="margin-top: 18px;"></div>
        </div>
        

        <div id="home_bad">
          <h4>침실 수</h4>
          <input type="checkbox" name="homeBed" id="homeBad1"value="1"><label for="homeBad1">1</label><br>
          <input type="checkbox" name="homeBed" id="homeBad2"value="2"><label for="homeBad">2</label><br>
          <input type="checkbox" name="homeBed" id="homeBad3"value="3"><label for="homeBad">3</label><br>
          <input type="checkbox" name="homeBed" id="homeBad4"value="4"><label for="homeBad">4+</label>
        </div>  
		
        <div id="home_type2">
          <h4>건물 유형</h4>
          <input type="checkbox" name="homeType2" value="housing"> <label>단독 또는 다세대 주택</label><br>
          <input type="checkbox" name="homeType2" value="apartment"> <label>아파트</label><br>
          <input type="checkbox" name="homeType2" value="hotel"> <label>호텔</label><br>
          <input type="checkbox" name="homeType2" value="separate"> <label>게스트용 별채</label>
        </div>

        <div id="home_Facilities">
          <h4>편의 시설</h4>
          <input type="checkbox" name="homeFacilities"value="wifi,"> <label>무선 인터넷</label><br>
          <input type="checkbox" name="homeFacilities"value="kitchen,"> <label>주방</label><br>
          <input type="checkbox" name="homeFacilities"value="washer,"> <label>세탁기 및 건조기</label><br>
          <input type="checkbox" name="homeFacilities"value="free-p,"> <label>무료 주차 공간</label><br>
          <input type="checkbox" name="homeFacilities"value="a/c,"> <label>에어컨</label><br>
          <input type="checkbox" name="homeFacilities"value="pool"> <label>수영장</label><br>
        </div>

        <div id="home_grade">
	      <a href="" id="productfilter">검색</a>
       
        </div>
        
      </form>
      
    </section>


    <section id="house">

      <div class="home__box">

        
          <!--<div class="home__img"><img src="img/ex.jpg" width="330px" height="280px"></div>
          -->
          
          <!-- home시작 -->
          <div class="home">
            
          <div id="slideShow">
            <ul class="slides">
			  <li><img src="/image/ex6.jpg" alt=""></li>
              <li><img src="/image/ex7.jpg" alt=""></li>
              <li><img src="/image/ex8.jpg" alt=""></li>
              <li><img src="/image/ex6.jpg" alt=""></li>
            </ul>  
            <p class="controller">
              <!-- &lang: 왼쪽 방향 화살표
              &rang: 오른쪽 방향 화살표 -->
              <span class="prev">&lang;</span>  
              <span class="next">&rang;</span>
            </p>
          </div>


          <div class="home__info">
            <div class="info1">Busan <img src="/image/heart_before.png"></div>
            <div class="info2">해운대</div>
            <div class="info3">
              <span class="star">★★★★★</span>
              <span class="count">185</span>
              <span class="type">슈퍼호스트</span>
            </div>
            <div calss="info4" style="font-size: 15px; margin-top: 3px; font-weight: bold;">￦140,000/박</div>

          </div>
        </div>
        <!-- home 끝 -->
      </div>
      
      
    </section>

    

  </div>
</section>
<footer>


</footer>

  
  <script>
  var slider = document.getElementById("myRange");
  var output = document.getElementById("value");
  output.innerHTML = slider.value;

  slider.oninput = function() {
      output.innerHTML = this.value;
  }
  
  </script>


  <script src="/js/test.js"></script>

  <script src="/js/slideShow.js"></script>
  <script src="/js/search.js"></script>
  <script src="/js/productfilter.js"></script>
</body>
</html>