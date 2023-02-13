$(document).ready(function(){
  $(function() {
    let today = new Date();
    $('input[name="daterange"]').daterangepicker({
      "startDate": today,
      "endDate": today,
      opens: 'center',
      locale: {
        format: 'YYYY/MM/DD'
      }
    });
  });
});

let input = document.querySelector(".input");
input.addEventListener("click", function() {
  document.getElementById("del").style.display = 'block';
});

// input.onblur = function(e) {
//   document.getElementById("del").style.display = 'none';
// }