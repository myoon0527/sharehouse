const house = document.querySelector("#house");
const map = document.querySelector("#map");

function mapfind() {
  house.style.WebkitAnimation = "fadeOut 0.5s";
  house.style.animation = "fadeOut 0.5s";
  setTimeout(()=> {
    map.style.WebkitAnimation = "fadeIn 0.5s";
    map.style.animation = "fadeIn 0.5s";
    setTimeout(()=> {
      house.style.display = "none";
      map.sylte.display = "block";
    }, 23)
  }, 23)
}
