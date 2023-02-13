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
      <div class="profileimg-changebox">
        <h1>프로필 사진 변경</h1>
        <hr>
        <div class="profileimg-change"> 
          <form id="img-form" enctype="multipart/form-data">
          
            <table>
              <tr>
                <td>프로필 사진</td>
                <td>
                  <div class="preprofile-img">
                 	  <c:choose>
        	
        				<c:when test="${principal.user.profileimage eq null }">
        			
				        	<img src="/img/user-profile.png"alt="기존 이미지">
        			
        				</c:when>
        	
        				<c:otherwise>
 	 					 	<img src="${principal.user.profileimage }"alt="기존 이미지">
        				</c:otherwise>
        			  </c:choose>
                  </div>
                  <div class="change-img">
                    <input type="file" name="profileimage" id="profileimage" accept="image/*" onchange="setThumbnail(event);">
                    <input type="hidden" id="hiddenid" value="${principal.user.id }">
                    <span></span>
                  </div>
                </td>
              </tr>
            </table>
          </form>
          <div class="profileimg-preview-box">
            <p>이미지 미리보기</p>
            <div class="profileimg-preview">
	            <img src="/img/user-profile.png"  id="previewimg">
            </div>
          </div>
          <div>
            <button id="imgchange-btn">변경 하기</button>
            
          </div>
          

      </div>
      </div>
    </div>

  </div>
   <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="/js/imgfile.js"></script>
<script src="/js/user.js"></script>
</body>
</html>