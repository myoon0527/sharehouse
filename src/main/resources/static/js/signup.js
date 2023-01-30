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
    }
    else{
        $("#phonenumtext").text("");
    }
    if($("#checklist").is(":checked")==false){
        alert("약관에 동의해 주세요")
    }
    return true;
}
$(".idcheck-btn").click(()=>{
    $("#rid").val($("#id").val())/**id 검사한 값 넣어주기 */
    $("#idtext").text("");

})
$("#passwordck").keyup(()=>{
    if($("#passwordck").val()!==$("#password").val()){
        $("#pwdtext").text("비밀번호가 일치하지 않습니다.");
    }else{
        $("#pwdtext").text("비밀번호가 일치합니다.");
        setTimeout(() => {
            $("#pwdtext").text("");
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
        $("#emailtext").text("이메일형식에 일치합니다");
        setTimeout(()=>{
            $("#emailtext").text("");
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