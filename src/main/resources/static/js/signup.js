/*약관 동의 modal */
const modal = document.getElementById("modal")
const btnModal = document.getElementById("modal-btn")
btnModal.addEventListener("click", () => {
    modal.style.display = "flex"
})
const closeBtn = modal.querySelector(".closeicon")
closeBtn.addEventListener("click", () => {
    modal.style.display = "none"
})

/**회원 가입 유효성 검사 */
function checkvalidation(){
    if($("#rid").val() == "" && $("#id").val()==""){
        $("#idtext").text("id값이 비어있음");
        return false;
    }else if($("#rid").val() == ""){
        $("#idtext").text("id값 중복확인해주세요");
        return false;
    }else if($("#rid").val()!=$("#id").val()){
	$("#idtext").text("id값 중복확인해주세요");
	return false;
}
    if($("#password").val()==""||$("#passwordck").val()==""){
        $("#pwdtext").text("비밀번호를 입력해주세요");
        return false;
    }else{
        $("#pwdtext").text("");
    }
    if($("#email").val()==""){
        $("#emailtext").text("이메일을 입력해주세요");
        return false;
    }
    if($("#phonenumber").val()==""){
        $("#phonenumtext").text("전화번호를 입력해주세요");
        return false;
    }else if(numform.test($("#phonenumber").val())==false){
        $("#phonenumtext").text("숫자만 입력가능합니다.");
        return false;
    }
    else{
        $("#phonenumtext").text("");
    }
    if($("#checklist").is(":checked")==false){
        alert("약관에 동의해 주세요")
        return false;
    }
    return true;
}
$(".idcheck-btn").click(()=>{
	let id = $("#id").val();
	$.ajax({
		url:"/api/check/"+id,
		type:"POST",
		dateType:"json"
	}).done(function(res){
		if(res.data==0){
			 $("#idtext").text("");
		 $("#idtext2").text("등록가능아이디 입니다.");
	     $("#rid").val($("#id").val())/**id 검사한 값 넣어주기 */
		}else{
			$("#idtext").text("등록이 불가능한 아이디입니다.");
			
		}
		 
	}).fail(function(error){
		alert(JSON.stringify(error));
	})
	setTimeout(() => {
    $("#idtext2").text("");

        }, 1000);

})
$("#passwordck").keyup(()=>{
    if($("#passwordck").val()!==$("#password").val()){
	        $("#pwdtext2").text("");
        $("#pwdtext").text("비밀번호가 일치하지 않습니다.");
    }else{
		$("#pwdtext").text("");
        $("#pwdtext2").text("비밀번호가 일치합니다.");
        setTimeout(() => {
            $("#pwdtext2").text("");
        }, 1000);
    }
})
var emailform =  /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
var numform = /^[0-9]+$/;
var emailtest = emailform.test($("#email").val());

$("#email").keyup(()=>{
    if(emailform.test($("#email").val())==false){
        $("#emailtext").text("이메일형식이 올바르지 않습니다.");
    }else{
		$("#emailtext").text("");
        $("#emailtext2").text("이메일형식에 일치합니다");
        setTimeout(()=>{
            $("#emailtext2").text("");
        },1000);
    }
})
$("#phonenumber").keyup(()=>{
    if(numform.test($("#phonenumber").val())==false){
        $("#phonenumtext").text("숫자만 입력해주세요.");
    }else{
        $("#phonenumtext").text("");
    }
})


/**회원 정보변경 유효성 검사 */
function updatecheckvalidation(){
    
	var emailform =  /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	var numform = /^[0-9]+$/;
	
    if($("#pwd").val()==""){
     	alert("비밀번호를 입력해주세요")
        return false;
    }
    if($("#email").val()==""||emailform.test($("#email").val())==false){
        alert("이메일을 정확히 입력해주세요")
        return false;
    }
    if($("#phone").val()==""){
       alert("전화번호를 입력해주세요")
        return false;
    }else if(numform.test($("#phone").val())==false){
       alert("번호는 숫자만입력 가능합니다.")
       return false
    }
    
    
    return true;
}


