
function update_par(){
  const mainParagraph = document.getElementById("main_par");

  mainParagraph.style.fontSize="24px";
  mainParagraph.style.color="rgb(100, 100, 0)";
  mainParagraph.style.background="rgb(200, 0, 0)";

}

//option 1
const btn1 = document.getElementById("“IncBTN”");
btn1.onclick=update_par();
