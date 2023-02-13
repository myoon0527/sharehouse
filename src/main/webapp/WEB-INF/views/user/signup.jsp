<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/signup.css">
  <title>Document</title>
</head>
<body>
  <div class="signup-container">
    <div class="signup-form">
      <h1>회원 가입</h1>
      <hr>
      <div>
        <input class="signupbox idinput" type="text" id="id" placeholder="*아이디" required><button type="button" class="idcheck-btn">중복확인</button>
        <input type="hidden" type="text" id="rid"required>
        <p id="idtext" class="red"></p>
        <p id="idtext2" class="blue"></p>
        <input class="signupbox" type="text" placeholder="사용자 이름" id="usernick" required>
        <input class="signupbox" type="password" id="password" placeholder="*비밀번호(영문,숫자,특수문자)" required>
        <input class="signupbox" type="password" id="passwordck" placeholder="*비밀번호 확인" required>
        <p id="pwdtext" class="red"></p>
        <p id="pwdtext2" class="blue"></p>
        <input class="signupbox" type="text" id="email"placeholder="*이메일" required>
        <p id="emailtext" class="red"></p>
        <p id="emailtext2" class="blue"></p>
        <input class="signupbox" type="text" id="phonenumber" placeholder="*전화번호" required>
        <p id="phonenumtext" class="red"></p>
        <p id="phonenumtext2" class="blue"></p>
      
      <hr class="margin-hr">
      <div class="terms">
        <label><input type="checkbox"id="checklist">약관동의(필수)</label><span id="modal-btn">약관보기</span>
        <div class="modal-overlay" id="modal">
          <div class="modal-window">
            <div class="title"><h2>이용 약관</h2></div>
            <div class="closeicon"><span>X</span></div>
            <div class="content">
              <p>약관 1</p>
              <p>약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2약관 2</p>

            </div>
          </div>
        </div>
      </div>
      
      <button id="signup-btn">가입하기</button>
    </div>
    </div>

  </div>
  <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="/js/signup.js"></script>
<script src="/js/user.js"></script>
</body>
</html>