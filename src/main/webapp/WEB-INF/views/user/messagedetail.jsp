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
  <link rel="stylesheet" href="/css/replymessage.css">

</head>
<body>
  <div class="mypage">
    <div class="mypage-nav">
      <%@ include file="mypagenav.jsp" %>
    </div>
    
    
    
    <div class="mypage-container">
	 <div class="message-container">
        <div class="messageheader">
          <button id="messagedel-btn">삭제</button>
          <button id="modal-btn">답장</button>
          <a href="/user/message">목록</a>
        </div>
        <div class="messageinfo">
          <table>
              <tr>
                <th>보낸 사람</th>
                <td>${message.sendusernick}<input type="hidden" id="senduserid" value="${message.senduserid }"></td>
              </tr>
              <tr>
                <th>받은 시간</th>
                <td><fmt:formatDate pattern = "yyyy년 MM월 dd일 [HH:mm:ss]" value="${message.sendDate}"/></td>
              </tr>
          </table>
        </div>
        <div class="messagecontents">
          <p>내용</p>
          <p>${message.contents}</p>
          <input type="hidden" value="${message.id }" id="messageid">
        </div>
      </div>
    </div>
	
  <div class="modal-overlay" id="modal">
    <div class="modal-window">
      <div class="title"><h2>답장 보내기</h2></div>
      <div class="closeicon"><span>X</span></div>
      <div class="content">
		<div>
			<span>받는 사람 : </span><span>${message.sendusernick}</span>
			<input type="hidden" value="${message.receiveuserid }" id="receiveuserid">
			<hr>
			<textarea rows="10" cols="50" style="resize: none;" id="replycontents">
			</textarea>
			<button id="reply-btn">전송</button>
		</div>
       <hr>
      </div>
    </div>
  </div>
  </div>
  <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	<script src="/js/message.js"></script>
</body>
</html>