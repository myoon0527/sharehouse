let randomNum;

function sendsms(){
		let tel = $("#userphonenumber").val();
		$.ajax({
			url:"/auth/sendmessage/?tel="+tel,
			type:"POST"
			
		}).done(function(resp){
			randomNum=resp.data;
			alert("인증 메시지가 발송되었습니다.");
		})
	}
	




function numbercheck(){
	let inputnumber = $("#authchecking-input").val();
	console.log(inputnumber)
	console.log(randomNum)
	if(randomNum == inputnumber){
		alert("인증되었습니다.");
		authupdate();
	}
}
function authupdate(){
	let id = $("#userid").val();
	$.ajax({
		url:"/auth/roleupdate/"+id,
		type:"POST"
	}).done(function(resp){
		alert("다시 로그인 해주세요");
		location.href="/logout";
	}).fail(function(error){
		alert(JSON.stringify(error));
	})
}