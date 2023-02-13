/**
 * 
 */
 function guestclick(){
	if(confirm("HOST권한이 없습니다. 인증하시겠습니까?")){
		location.href="/user/update"
	}

}