
const xValue = document.getElementById("xValue");
const yValue = document.getElementById("yValue");
const resultInput = document.getElementById("result");


//no button , we might create it later on
//listen to keypress eventon X value and y value

xValue.value = "";
yValue.value = "";
//xValue.value = "Enter a number";
xValue.addEventListener("keypress", handleKeyPress);
yValue.addEventListener("keypress", handleKeyPress);

let ws = new WebSocket("ws://localhost:8080");
let wsOpen = false;
ws.onopen = function (){
  wsOpen = true;
}

function handleKeyPress(e) {
  if (event.code == "Enter" || event.code=="click") {
    let x = parseFloat(xValue.value);
    let y = parseFloat(yValue.value);
    if (isNaN(x)) {
      alert("enter a number");
      xValue.value = "Enter a number";
      xValue.select();
      return;
    }
    if (isNaN(y)) {
      alert("enter a number");
      yValue.value = "Enter a number";
      yValue.select();
      return;
    }
    ws.onmessage = function (e){
      resultInput.value = e.data;

    }

    resultInput.value = (y + x);


    if (wsOpen){
      ws.send(x + " " + y);
    }

    else{
      resultInput.value = "could not open the web socket";

    }



  }
}

//
// function handleError(){
//   resultInput.value = "problem connecting";
// }
//
// function handleAJAX(){
//   resultInput.value = this.responseText;
// }





//using AJAX-------------------------------------------------------------------------
// let xhr = new XMLHttpRequest();
// xhr.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y);
// //xhr.onerror = handleError;
// xhr.addEventListener("load", handleError);
// xhr .onload = handleAJAX;
// xhr.send();

//using WEB SOCKETS-------------------------------------------------------------------
