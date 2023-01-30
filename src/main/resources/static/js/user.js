let index ={
	init: function(){
		$("#signup-btn").on("click",()=>{
			if(checkvalidation()){
				
			this.save();
			}
		});
	},
	save:function(){
		let date={
			username:$("#rid").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			phonenumber:$("#phonenumber").val()
		}
		$.ajax({
			url:"/api/signup",
			type:"POST",
			data:JSON.stringify(date),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(){
			alert("회원등록 완료")
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
			console.log(error)
		})
	}
	
	
}
index.init();