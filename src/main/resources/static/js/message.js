/**
 * 
 */
let msn ={
	init : function(){
		$("#msnsendbtn").on("click",()=>{
			this.send();
		});
		$("#messagedel-btn").on("click",()=>{
			if(confirm("쪽지를 삭제 하시겠습니까?")){
				this.delete();
				
			}
		});
		$("#sendmessagedel-btn").on("click",()=>{
			if(confirm("쪽지를 삭제 하시겠습니까?")){
			 	this.delete2();
				
			}
		});
		$("#reply-btn").on("click",()=>{
			this.sendreply();
		});
		
	},
	send:function(){
		let data ={
			senduserid:$("#hiddensenduserid").val(),
			receiveuserid:$("#reciveuserid").val(),
			contents:$("#messagecontents").val()
		}
		$.ajax({
			url:"/api/sendMessage",
			type:"POST",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(){
			alert("쪽지를 발송했습니다.")
			location.href="/user/mypage";
		}).fail(function(error){
			alert("발송실패");
			console.log(JSON.stringify(error));
		})
	},
	delete:function(){
		let id = $("#messageid").val();
	$.ajax({
		type:"DELETE",
		url:"/api/deleteMessage/"+id,
		dataType:"json"
	}).done(function(resp){
		alert("메시지 삭제 완료");
		location.href="/user/message" //수신메시지로
	}).fail(function(error){
		alert("메시지 삭제 실패");
		console.log(JSON.stringify(error));
	});
	},
	delete2:function(){
		let id = $("#messageid").val();
	$.ajax({
		type:"DELETE",
		url:"/api/deleteMessagesend/"+id,
		dataType:"json"
	}).done(function(resp){
		alert("메시지 삭제 완료");
		location.href="/user/sendmessage" //발신메시지로
	}).fail(function(error){
		alert("메시지 삭제 실패");
		console.log(JSON.stringify(error));
	});
	},
	sendreply:function(){
		let data={
			senduserid:$("#receiveuserid").val(),
			receiveuserid:$("#senduserid").val(),
			contents:$("#replycontents").val()
		};
		$.ajax({
			url:"/api/sendMessage",
			type:"POST",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(){
			alert("답장을 보냈습니다.");
			location.href="/user/message";
		}).fail(function(error){
			alert("답장보내기에 실패했습니다.");
			console.log(JSON.stringify(error));
		})
	},
	
	
	
}
msn.init();

//답장 모달
const modal = document.getElementById("modal")
const btnModal = document.getElementById("modal-btn")
btnModal.addEventListener("click", () => {
    modal.style.display = "flex"
})
const closeBtn = modal.querySelector(".closeicon")
closeBtn.addEventListener("click", () => {
    modal.style.display = "none"
})
