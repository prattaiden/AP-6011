window.onload = function (){

  document.writeln("<h1 id = \"h\"> HELLO</h1>");

  document.writeln("<p>welcome to the webpage</p>");

  document.writeln("<p id = \"aiden\">my name is Aiden Pratt</p>");

  document.writeln("<p>here is a link to my strava: <a href=https://www.strava.com/athletes/32689106> STRAVA </a> </p>")

  document.writeln("<button id = “IncBTN”> update font </button>")


  let elem = document.body;
  let h1 = document.getElementById("h");

  elem.style.background="rgb(114,121,114)";

  h1.style.background="rgb(0, 0, 200)";

  elem.style.textAlign = "center";

}

function update(){
  const mainParagraph = document.getElementById("aiden");

  mainParagraph.style.fontSize="24px";
  mainParagraph.style.color="rgb(100, 100, 0)";
  mainParagraph.style.background="rgb(200, 0, 0)";


}

//option 1
const btn1 = document.getElementById("IncBTN");

btn1.onclick = update();


//option 2
// const btn2 - document.getElementById("resetBTN");
// btn2.addEventListener("click",)
