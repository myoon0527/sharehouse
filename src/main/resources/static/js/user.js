let index ={
	init: function(){
		$("#signup-btn").on("click",()=>{
			if(checkvalidation()){
				
			this.save();
			}
		});
		$("#update-btn").on("click",()=>{
			if(updatecheckvalidation()){
				this.update();
			}
			
		});
		$("#userout-btn").on("click",()=>{
			if($("#outcheck").is(":checked")){
				this.out();
			}else{
				alert("탈퇴하시려면 약관에 동의해 주세요")
			}
		});
		$("#imgchange-btn").on("click",()=>{
			this.imgchange();
		})
		
	},
	save:function(){
		let data={
			username:$("#rid").val(),
			nickname:$("#usernick").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			phonenumber:$("#phonenumber").val()
		}
		$.ajax({
			url:"/api/signup",
			type:"POST",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(){
			alert("회원등록 완료")
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
			console.log(error)
		})
	},
	update:function(){
		let id = $("#userid").val()
		let date={
			username:$("#updateusername").val(),
			password:$("#pwd").val(),
			email:$("#email").val(),
			phonenumber:$("#phone").val()
		}
		$.ajax({
			url:"/api/update/"+id,
			type:"PUT",
			data:JSON.stringify(date),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(){
			alert("회원정보 변경완료")
			location.href="/user/mypage";
		}).fail(function(error){
			alert(JSON.stringify(error));
			console.log(error)
		})
	},
	out:function(){
		let id = $("#hiddenuserid").val()
		$.ajax({
			url:"/api/out/"+id,
			type:"DELETE",
			
			dataType:"json"
		}).done(function(){
			alert("회원 탈퇴 완료")
			location.href="/logout";
		}).fail(function(error){
			alert(JSON.stringify(error));
			console.log(error)
		})
	},
	imgchange:function(){
		let id =$("#hiddenid").val()
		var form = $("#img-form")[0]
		var formdata = new FormData(form);
		formdata.append("profileimage",$("#profileimage").val());
		$.ajax({
			url:'/api/imgchange/'+id,
			type:'POST',
			data: formdata,
			datetype:'json',
			contentType:false,
			processData:false
		}).done(function(){
			alert("이미지 변경 완료");
			alert("다시 로그인 해주세요");
			location.href="/logout";
		}).fail(function(error){
			alert(JSON.stringify(error));
			console.log(error);
		})
	}
	
}
index.init();