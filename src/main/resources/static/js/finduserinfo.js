let code;
let userid;
let userpk;

//이름 이메일로 유저정보 가져오기
$('#mail-Check-Btn').click(function() {
		var email = $('#useremail').val(); // 이메일 주소값 얻어오기!
		var usernick =$("#usernick").val();
		
		$.ajax({
			type:"POST",
			url:"/api/finduseremail/?usernick="+usernick+"&email="+email,
		}).done(function(resp){
			if(resp.data !=""){
				sendmail(resp.data);
				userid= resp.data.username;	
			}
		}).fail(function(error){
			alert("이름 또는 이메일이 일치하지 않습니다.")
		})
	}); 
	
// 가져온 유저정보로 인증 메일 보내기 
function sendmail(data){
	let email = data.email
	console.log(email);
	$.ajax({
			type : 'get',
			url : "/auth/mailCheck?email="+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
				success : function (data) {
				code = data; 
				console.log(code);
				
				alert('인증번호가 전송되었습니다.');
			}			

		})
}
// 인증메일의 인증내용을 확인하고 id뿌려주기
$('#mail-Check-Certify-btn').click(function () {
		const inputCode = $("#Certification-Number").val();
		
		
		if(inputCode === code){
			alert("확인되었습니다.");
			$("#findidshow").text("찾으시는 아이디는 : "+userid);
			
		}else{
			alert("인증번호가 다릅니다.")
		}
	});


// 비밀번호 바꾸기 위해 id랑 email 정보로 유저 정보 가져오기
$('#pwd-mail-Check-Btn').click(function() {
		var email2 = $('#useremail2').val(); // 이메일 주소값 얻어오기!
		var userid =$("#userid").val();
		
		 
		
		$.ajax({
			type : 'POST',
			url : "/api/pwdmailCheck?userid="+userid+"&email="+email2, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			
			}).done(function(resp){
			
				if(resp.data !=""){
				sendmail(resp.data);
				userpk = resp.data.id;
				}
			}).fail(function(error){
			alert("아이디 또는 이메일이 일치하지 않습니다.")
			});		
		}); 
		
// 인증메일의 인증내용을 확인하고 pwd 변경 페이지로 이동
$('#mail-Check-Certify2-btn').click(function () {
		const inputCode = $("#Certification-Number2").val();
		let page = document.querySelector(".changepwd-box");
		
		if(inputCode === code){
			alert("확인되었습니다.");
			
			$.get("/auth/pwdchange/"+userpk,function(resp){
				page.innerHTML=resp;
			})
		}else{
			alert("인증번호가 다릅니다.")
		}
	});


function pwdchange(){
	
	if($("#change-pwd").val()!==$("#change-pwd-check").val()){
		alert("비밀번호가 일치하지 않습니다.")
		return false;
	}else{
		let data ={
			id:$("#hiddenuserid").val(),
			password:$("#change-pwd").val()
		}
		$.ajax({
			url:"/auth/changepwdProc",
			type:"POST",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
			
		}).done(function(resp){
			alert("변경된 비밀번호로 로그인 해주세요")
			window.close();
		}).fail(function(error){
			alert("실패")
			console.log(JSON.stringify(error))
			
		})
	}
}
	
