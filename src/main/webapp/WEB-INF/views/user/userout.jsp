<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
  <link rel="stylesheet" href="/css/mypage.css">
</head>
<body>
  <div class="mypage">
    <div class="mypage-nav">
      <%@ include file="mypagenav.jsp" %>
    </div>
    
    
    
    <div class="mypage-container">
		<div class="userout">
          <h1>회원탈퇴 </h1>
          <hr>
          <br><br><br><br> 
          <h4>회원 탈퇴 안내</h4>
          <div class="outmessagebox">
            <div class="outmessagebox_text">
              내용
            </div>
            
            <div class="outcheck">
              <input type="checkbox" id="outcheck"><span>안내사항을 모두 확인하였으며, 이에 동의합니다.</span><br>
              <input type="hidden" value="${principal.user.id }" id="hiddenuserid">
              <button id="userout-btn">확인</button>
              <button type="reset">취소</button>
            </div>
          </div>
      </div>
    </div>

  </div>
    <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
  <script src="/js/user.js">  </script>
  <script src="/js/signup.js">  </script>
 
</body>
</html>