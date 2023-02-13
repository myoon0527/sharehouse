<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
        <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
      <div class="messagebox">
        <h1>수신 쪽지</h1>
        <hr>
        <div class="message-search">
        <form action="/user/messagesearch" method="post">
          <select id="messagesearchsel" name="messagesearchsel">
            <option value="finduserid">아이디</option>
            <option value="findcontent">내용</option>
          </select>
          <input type="text" placeholder="쪽지 검색" id="searchtext" name="searchtext">
          <input type="hidden"value="${principal.user.id}" id="searchuser"name="searchuser">
          <button id="messagesearch-btn" type="submit">검색</button>
        </form>
        	<button><a href="/user/message">전체보기</a></button>
        </div>
        <div class="message-list">
         
          <li>
            <div class="message-list-header"></div>
            <div class="message-list-header">보낸 사람</div>
            <div class="message-list-header">내용</div>
            <div class="message-list-header">날짜</div>
          </li>
          <c:forEach var="message" items="${messages.content}">
          	<li>
            	<div></div>
            	<div>${message.sendusernick }</div>
            	<div><a href="/user/messagedetail/${message.id}" class="messagecontent">${message.contents}</a></div>
           		<div><fmt:formatDate pattern = "yy-MM-dd[HH:mm]" value="${message.sendDate}"/></div>
          	</li>
          </c:forEach>
        </div>
        <div class="pagenav">
          
            <li class="page-item"><a class="page-link" href="#"><</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">></a></li>
        
        </div>
          
      
      </div>
    </div>

  </div>
    <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
  <script src="/js/message.js"></script>

</body>
</html>