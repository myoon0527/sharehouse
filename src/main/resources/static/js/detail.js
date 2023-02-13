//일정선택 기본 날짜 보이기
$(document).ready(function(){
  $(function() {
    let today = new Date();
    $('input[name="daterange"]').daterangepicker({
      "startDate": today,
      "endDate": today,
      opens: 'center',
      locale: {
        format: 'YYYY.MM.DD'
      }
    });
  });
});

//스크롤 애니메이션
$(document).ready(function($) {
  $(".scroll-move").click(function(event){
    event.preventDefault();
    $('html,body').animate({scrollTop:$(this.hash).offset().top},500)
  });
});

//일수 계산
let input = document.querySelector(".input");

input.onchange = function(e) {
  const ddd = document.getElementById('daterange').value;
  const date1 = new Date(ddd.slice(0,10));
  const date2 = new Date(ddd.slice(13,23));

  const diffDate = Math.abs((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));

  console.log(date1);
  console.log(date2);
  console.log(diffDate+"일");
  console.log(document.querySelector('.houseFare').value);
 
  let x = document.getElementById('schedule');
  let y = document.querySelector('.schedule');
  x.innerText = diffDate;
  y.innerText = diffDate;
  
  let fare = document.querySelector('.houseFare').value;
  let total = document.querySelector('.total_fare');
  total.innerText = fare*diffDate;
 	
	
  let z = document.getElementById('schedule2')
  z.innerText = date1.getFullYear() + "년 " + 
  	parseInt(date1.getMonth()+1) + "월 " + 
  	date1.getDate() + "일 " +
  	"~ " + date2.getFullYear() + "년 " + 
  	parseInt(date2.getMonth()+1) + "월 " + 
  	date2.getDate() + "일";
}

//인원 수
let number = document.querySelector(".guest");
number.onblur = function(e) {
  const aaa = document.getElementById('total-num').value;
}

//인원 수 수정 창 열고 닫기 
let n = document.querySelector(".guest");
n.onclick = function(e) {
  document.getElementById("guest-window").style.display = 'grid';
}
let q = document.querySelector(".exit");
q.onclick = function(e) {
  document.getElementById("guest-window").style.display = 'none';
}

//성인 숫자 증감
let adult_plus = document.querySelector(".adult-plus");
let def = document.getElementById("adult-num").value;
let intDef = parseInt(def);
let total = 1;
adult_plus.onclick = function(e) {
  intDef += 1;
  total += 1;
  document.getElementById("adult-num").value = intDef;
  document.getElementById("total-num").value = total;
  console.log(total);
  console.log(typeof(total));
}

let adult_minus = document.querySelector(".adult-minus");
adult_minus.onclick = function(e) {
  if(intDef > 0) {
    intDef -= 1;
    total -= 1;
  }
  document.getElementById("adult-num").value = intDef;
  document.getElementById("total-num").value = total;
}

//어린이 숫자 증감
let child_plus = document.querySelector('.child-plus');
let child_def = document.querySelector('.child-num').value;
let int_child_def = parseInt(child_def);
child_plus.onclick = function(e) {
  int_child_def += 1;
  total += 1;
  document.querySelector('.child-num').value = int_child_def;
  document.getElementById('total-num').value = total;
}

let child_minus = document.querySelector('.child-minus');
child_minus.onclick = function(e) {
  if(int_child_def > 0) {
    int_child_def -= 1;
    total -= 1;
  }
  document.querySelector('.child-num').value = int_child_def;
  document.getElementById('total-num').value = total;
}


