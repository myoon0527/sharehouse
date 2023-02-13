      <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
  <sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />	
</sec:authorize>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
		<th>받을사람id 
			<input type="hidden" id="hiddensenduserid" value="${principal.user.id}">
		</th>
		<td><input type="text" id="reciveuserid">
		</td>
	</tr>
	<tr>
		<th>내용
		</th>
		<td><input type="text" id="messagecontents">
		</td>
	</tr>
	</table>
	
	<button id="msnsendbtn">보내기</button>
	 <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	<script src="/js/message.js"></script>
</body>
</html>