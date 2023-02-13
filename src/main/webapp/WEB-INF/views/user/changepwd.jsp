<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div>
	<input type="hidden" value="${user.id}" id="hiddenuserid">
      <p>비밀번호 변경</p>
      <input type="password" placeholder="변경할 비밀번호를 입력해 주세요" class="findinputbox" id="change-pwd">
      <p>비밀번호 확인</p>
      <input type="password" placeholder="비밀번호를 확인해 주세요" class="findinputbox" id="change-pwd-check"><br>
      <button onclick="pwdchange()" id="change-pwd-btn" class="Certiy-btn">비밀번호 변경</button>
    </div>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>