var bigPic = document.querySelector("#big");
var smallPics = document.querySelectorAll(".small");

for(var i = 0; i < smallPics.length; i++) {
    smallPics[i].addEventListener("click", changepic);
}

function changepic() {
    var smallPicAttribute = this.getAttribute("src");
    bigPic.setAttribute("src", smallPicAttribute);
}