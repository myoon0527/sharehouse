      <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
  <sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />	
</sec:authorize>
      
      <div class="userinfo">
        <a href="/user/updateprofileimg">
      
        <c:choose>
        	
        	<c:when test="${principal.user.profileimage eq null }">
        			
				        <img src="/img/user-profile.png"alt="프로필사진">
        			
        	</c:when>
        	
        	<c:otherwise>
 	 					 <img src="${principal.user.profileimage}" alt="프로필사진">
        	</c:otherwise>
        </c:choose>
        </a>
        <p>유저아이디:${principal.user.username}</p>
        <c:if test="${principal.user.grade <10 }">
	        <p>유저 등급: Beginner</p>
        	
        </c:if>
          <c:if test="${principal.user.grade >=10 && principal.user.grade <50 }">
	        <p>유저 등급: Traveler</p>
        	
        </c:if>
          <c:if test="${principal.user.grade >=50}">
	        <p>유저 등급: Veteran</p>
        	
        </c:if>

      </div>
      <div class="userservice">
        <div class="msnmenu userservice-menu"><b>메시지/알림</b><br>
          <li><a href="/user/message">수신 쪽지</a></li>
          <li><a href="/user/sendmessage">발신 쪽지</a></li>
          <li><a href="/user/msntest">발신 쪽지test</a></li>
        </div>
        <div class="guestmenu userservice-menu"><b>게스트</b><br>
          <li><a href="">예약확인</a></li>
          <li><a href="">찜리스트</a></li>

        </div>
        <div class="hostmenu userservice-menu"><b>호스트</b><br>
          <li><a href="">호스팅 관리</a></li>
          <li><a href="">예약 현황</a></li>
          <li><a href="">거래 내역</a></li>
          
          
        </div>
        <div class="usermenu userservice-menu"><b>개인정보</b><br>
        	
          <li><a href="/user/update">정보변경</a></li>
          
        </div>
        <div class="mypage-nav-footer">
          <li><a href="/logout">로그아웃</a></li>
          <li><a href="">고객센터</a></li>
          <li><a href="/user/out">회원 탈퇴</a></li>
        </div>
      </div>
