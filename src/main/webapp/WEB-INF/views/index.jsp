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
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/footer.css">
    <title>title</title>
    
  </head>
  <body>
    <div class="index_header">
      <div class="logo">로고</div>
      <div class="right">
        <div class="nav"><a href="/product/main">서비스 이용</a></div>
        <div class="nav"><a href="">이용안내</a></div>
        
        <c:choose>
        	
        	<c:when test="${principal ne null}">
        			
				        <div class="nav"><a href="/user/mypage">마이페이지</a></div>
        				<div class="nav"><a href="/logout">로그아웃</a></div>
        			
        	</c:when>
        	
        	<c:otherwise>
 	 			 <div class="nav"><a href="/auth/loginForm">로그인</a></div>
        	</c:otherwise>
        </c:choose>
        
      </div>
    </div>
    
    <div class="index-wrapper">
      <div class="index_page">
        <p class="index_pagetext">새로운 환경<br>&nbsp&nbsp새로운 시작
        
        </p>
        
        
      </div>
      <div class="index_page">
        <p class="index_pagetext">새로운 환경<br>&nbsp&nbsp새로운 시작</p>
      </div>
      <div class="index_page">
        <p class="index_pagetext">새로운 환경<br>&nbsp&nbsp새로운 시작</p>
      </div>
      <div class="index_page">
        <p class="index_pagetext">새로운 환경<br>&nbsp&nbsp새로운 시작</p>
      </div>
      <div class="index_page">
        <div class="index_page_btn">
          <div class="index_page_btn_a"><a href="">상품보러가기</a></div>
          <div class="index_page_btn_a"><a href="">상품등록하기</a></div>
          <div class="index_page_btn_a"><a href="">이용안내</a></div>

        </div>
      </div>
    </div>
    <%@ include file="layout/footer.jsp" %>
    <script>
      let opt = new IntersectionObserver((e)=>{
        e.forEach((박스)=>{
          if(박스.isIntersecting){
             박스.target.style.opacity=1;
             박스.target.style.animation='up 1s';
          }else{
            박스.target.style.opacity=0;
            박스.target.style.animation='none';
          }
        })
      })
      let p = document.querySelectorAll(".index_pagetext");
      opt.observe(p[0])
      opt.observe(p[1])
      opt.observe(p[2])
      opt.observe(p[3])
    </script>
  </body>
</html>
