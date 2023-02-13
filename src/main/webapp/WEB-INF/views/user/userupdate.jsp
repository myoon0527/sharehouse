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
      <div class="userupdateform">
        <h1>회원정보 변경</h1>
        <hr>
        <div class="userupdate-table">
        	<h4>회원 정보 변경</h4>
          <form>
          <input type="hidden" value="${principal.user.id }" id="userid">
            <table>
              <tr>
                <th>아이디</th>
                <td><input type="text" readonly value="${principal.user.username}" id="updateusername"></td>
              </tr>
              
        	
   
        	<c:choose>
        	
        	<c:when test="${principal.user.social eq null}">
        			
				<tr>
                <th>비밀번호</th>
                <td><input type="password" placeholder="변경할 비밀번호를 입력해주세요"  id="pwd" title="비밀번호"></td>
              </tr>
        	</c:when>
        	
        	<c:otherwise>
 	 		
               <input type=hidden  id="pwd" value="123">
              
        	</c:otherwise>
        </c:choose>
              <tr>
                <th>이메일</th>
                <td><input type="text" value="${principal.user.email}" id="email"></td>
              </tr>
          	 <tr>
                <th>전화번호</th>
                <td><input type="text" value="${principal.user.phonenumber}" id="phone"></td>
              </tr>
            </table>
          </form>
          <button class="update-btn" id="update-btn" >정보변경</button>
        </div>
        
        
        <c:choose>
        	
        	<c:when test="${principal.user.social eq null}">
        	<div class="auth-box-form">
				<h4>본인 인증</h4>
				<div class="auth-box-table">
				<table >
					<tr>
						<th>휴대폰
							<div class="tooltip">
								<img src="/img/question-mark.png" id="questionmark" >
								<span class="tooltiptext tooltip-top">반드시 본인 명의의 휴대폰으로 인증하세요!</span>
							</div>
							
						</th>
						<td><span class="" title="정보변경은 변경칸에서 하실수 있습니다.">${principal.user.phonenumber}</span>
							<input type="hidden" id="userphonenumber" value="${principal.user.phonenumber}">
						
						
							<c:if test="${principal.user.userauth == 'auth' }">
								<p>인증완료</p>
							</c:if>
							<c:if test="${principal.user.userauth == 'notauth' }">
								<button>인증하기</button>
							</c:if>
						</td>
					</tr>
				</table>
				</div>
			</div>
				
        	</c:when
        	>
        	<c:when test="${principal.user.social == 'naver'}">
        	<div class="auth-box-form">
				<h4>본인 인증</h4>
				<div class="auth-box-table">
				<table>
					<tr>
						<th>휴대폰
							<div class="tooltip">
								<img src="/img/question-mark.png" id="questionmark" >
								<span class="tooltiptext tooltip-top">반드시 본인 명의의 휴대폰으로 인증하세요!</span>
							</div>
							
						</th>
						<td>
							<span class="" title="정보변경은 변경칸에서 하실수 있습니다.">${principal.user.phonenumber }</span>
							<input type="hidden" id="userphonenumber" value="${principal.user.phonenumber}">
						
							<c:if test="${principal.user.userauth == 'auth' }">
								<p>인증완료</p>
							</c:if>
							<c:if test="${principal.user.userauth == 'notauth' }">
								<button>인증하기</button>
							</c:if>
						</td>
					</tr>
				</table>
				</div>
			</div>
				
        	</c:when>
        	
        	<c:otherwise>
 	 		
              
              <p>아무것도 아님</p>
        	</c:otherwise>
        </c:choose>
       
      </div>
    </div>

  </div>
  <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
  <script src="/js/user.js">  </script>
  <script src="/js/signup.js">  </script>
   
</body>
</html>