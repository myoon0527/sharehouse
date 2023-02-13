<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/find.css">
</head>
<body>
<div class="finduserid-container">
  <h1>아이디/비밀번호 </h1>
  
	<p>인증된 이메일만 정보찾기가 가능합니다.
    <hr>
	<div class="finduserid-fromemail">
    <input type="radio" name="find" onclick="find1()" id="findid" checked="checked">
		<label for="findid">아이디 찾기</label>
    <input type="radio" name="find" onclick="find2()" id="changepwd">
    <label for="changepwd">비밀번호 변경하기</label>
		<div class="findidbynameemail-box">
     <p>이름</p>
     <input type="text" placeholder="이름을 입력해주세요" id="usernick" class="findinputbox">
     <p>이메일</p>
     <input type="text" placeholder="이메일을 입력해주세요" id="useremail" class="findinputbox">
      <button id="mail-Check-Btn" class="Certiy-btn">인증번호 받기</button>
      <input type="text" placeholder="인증번호" id="Certification-Number" class="findinputbox">
      <button id="mail-Check-Certify-btn" class="Certiy-btn">인증번호 확인</button>
      <p id="findidshow"></p>

    </div>

  <div class="changepwd-box" style="display:none;">
    <p>아이디</p>
    <input type="text" placeholder="아이디를 입력해 주세요" id="userid" class="findinputbox">
    <p>이메일</p>
    <input type="text" placeholder="이메일을 입력해 주세요" id="useremail2" class="findinputbox">
    <button id="pwd-mail-Check-Btn" class="Certiy-btn">인증번호 받기</button>
    <input type="text" placeholder="인증번호" id="Certification-Number2" class="findinputbox">
    <button id="mail-Check-Certify2-btn" class="Certiy-btn">인증번호 확인</button>
    <p id="findidshow"></p>
  </div>
</div>
	</div>
 <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	<script src="/js/finduserinfo.js"></script>
<script>
let box1 = document.querySelector(".findidbynameemail-box");
let box2 = document.querySelector(".changepwd-box");
function find1(){
  box1.style.display="block";
  box2.style.display="none"
}
function find2(){
  box1.style.display="none";
  box2.style.display="block"
}
</script>
</body>
</html>