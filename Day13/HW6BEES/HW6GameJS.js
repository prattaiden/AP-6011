
//let canvas = document.getElementsByTagName("canvas")[0];
let canvas = document.getElementById("canvasDrawing");
let context = canvas.getContext("2d");
let canvasW = canvas.width;
let canvasH = canvas.height;

let RunGuy= new Image();
RunGuy.src = "img/baby2.png";

// let Bear = new Image();
// Bear.src = "img/bear2.png";

let bearArray = [];
for (let i = 0; i < 3; i++){
  let bearObj = {}
  bearObj.img = new Image();
  bearObj.img.src = "img/bear2.png";
  bearObj.xPos = Math.floor(Math.random() * 1000)
  bearObj.yPos = Math.floor(Math.random() * 600)
  bearArray.push(bearObj);
}

let killed = false;



function animateImage() {

  eraseOld();

  context.drawImage(RunGuy, RunGuy.xPos - 25, RunGuy.yPos - 25, 50, 50);

  for (let i = 0; i < bearArray.length; i++) {
    context.drawImage(bearArray[i].img, bearArray[i].xPos, bearArray[i].yPos, 150, 100);

    if (RunGuy.xPos > bearArray[i].xPos) {
      bearArray[i].xPos += 5;
    } else if (RunGuy.xPos < bearArray[i].xPos) {
      bearArray[i].xPos -= 5;
    }

    if (RunGuy.yPos > bearArray[i].yPos) {
      bearArray[i].yPos += 5;
    } else if (RunGuy.yPos < bearArray[i].yPos) {
      bearArray[i].yPos -= 5;
    }

    if (bearArray[i].xPos > canvasW) {
      bearArray[i].xPos -= 100;
    } else if (bearArray[i].xPos < 0) {
      bearArray[i].xPos += 100;
    }

    //if guy is touched
    if (Math.abs(bearArray[i].xPos - RunGuy.xPos) < 5
      && Math.abs(bearArray[i].yPos - RunGuy.yPos) < 5) {
      console.log("kill");
      killed = true;
    }

    for (let j = 0; j < bearArray.length; j++) {
      if (bearArray[i] !== bearArray[j]) {
        if (Math.abs((bearArray[i].xPos + 100) - (bearArray[j].xPos + 100)) < 30
          && Math.abs((bearArray[i].yPos + 100) - (bearArray[j].yPos + 100)) < 30) {
          bearArray[j].xPos += Math.random() * 400;
          bearArray[j].yPos += Math.random() * 200;
          console.log("collision")
        }
      }
    }


  }

  if (!killed) {
  window.requestAnimationFrame(animateImage);
}
  else{
    gameOver();
  }

}

function gameOver(){
  context.font = "50px Ariel";
  context.fillStyle = "red";
  context.fillText("game over", 450 , 300);
}

function mainDrawing(){
  window.requestAnimationFrame(animateImage);
}

function handleMouse(e){
  RunGuy.xPos = e.x;
  RunGuy.yPos = e.y;
}

function eraseOld(){
  //add layer on top of previous image
  context.fillStyle="rgb(185,232,169)";
  context.fillRect(0,0, canvasW, canvasH);
}

window.onload=mainDrawing;

document.onmousemove = handleMouse;


// // this function will flip the imagedata
// function flipImage() {
//   // create a canvas that will present the output image
//   const outputImage = document.createElement('canvas');
//
//   // set it to the same size as the image
//   outputImage.width = BearGuy.naturalWidth;
//   outputImage.height = BearGuy.naturalHeight;
//
//   // get the drawing context, needed to draw the new image
//   const ctx = outputImage.getContext('2d');
//
//   ctx.scale(-1, 1);
//
//   // draw our image at position [-width, 0] on the canvas, we need
//   // a negative offset because of the negative scale transform
//   ctx.drawImage(BearGuy, -outputImage.width, 0);
//
//   // insert the output image after the input image
//   BearGuy.parentNode.insertBefore(
//     outputImage,
//     BearGuy.nextElementSibling
//   );
// }
