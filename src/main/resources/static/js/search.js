const search1 = document.querySelector("#search1");
const search2 = document.querySelector("#search2");

function search() {
  search1.style.WebkitAnimation = "fadeOut 0.5s";
  search1.style.animation = "fadeOut 0.5s";
  setTimeout(()=>{
    search2.style.WebkitAnimation = "fadeIn 0.5s";
    search2.style.animation = "fadeIn 0.5s";
    setTimeout(()=>{
      search1.style.display = "none";
      search2.style.display = "block";
    }, 23)
  }, 23)
}



// 1
// 1000

// 0.1
// 10000