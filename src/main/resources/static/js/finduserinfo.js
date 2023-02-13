let code;
let userid;



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
			alert("아이디 또는 이메일이 일치하지 않습니다.")
		})
		
	 // end ajax
	}); // end send eamil
function sendmail(data){
	let email = data.email
	console.log(email);
	$.ajax({
			type : 'get',
			url : "/auth/mailCheck?email="+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
				success : function (data) {
				code =data;
				console.log(code);
				
				alert('인증번호가 전송되었습니다.');
			}			

		})
}
$('#mail-Check-Certify-btn').click(function () {
		const inputCode = $("#Certification-Number").val();
		
		
		if(inputCode === code){
			alert("확인");
			$("findidshow").text(userid);
			
		}else{
			alert("인증번호가 다릅니다.")
		}
	});
	
$('#pwd-mail-Check-Btn').click(function() {
		var eamil = $('#useremail2').val(); // 이메일 주소값 얻어오기!
		var userid =$("#userid").val();
		
		 
		
		$.ajax({
			type : 'get',
			url : "/auth/pwdmailCheck?usernick="+usernick+"&email="+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			success : function (data) {
		
				code =data;
				console.log(data);
				alert('인증번호가 전송되었습니다.')
			}			
		}); // end ajax
	}); // end send eamil
