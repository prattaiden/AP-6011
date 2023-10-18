"use strict";

//let canvas = document.getElementsByTagName("canvas")[0];
let canvas = document.getElementById("canvasDrawing");
let context = canvas.getContext("2d");
let canvasW = canvas.width;
let canvasH = canvas.height;

let myIMG= new Image();
myIMG.src = "avatar.jpeg";

let MSDIMG = new Image();
MSDIMG.src = "msd.png";

//1 - let xPos and let yPos
MSDIMG.xPos = 10;
MSDIMG.yPos = 10;

let moveRight = true;

function animateImage(){
  eraseOld();
  context.drawImage(MSDIMG, MSDIMG.xPos, MSDIMG.yPos);

  context.drawImage(myIMG, myIMG.xPos - 50, myIMG.yPos - 50, 100, 100);
  context.strokeRect(myIMG.xPos - 50, myIMG.yPos - 50, 100, 100);

  if(moveRight) {
    MSDIMG.xPos += 5;
  }
  else{
    MSDIMG.xPos-=5;
  }

  if(MSDIMG.xPos > canvasW - MSDIMG.width){
    moveRight = false;
   // flipHorizontally(MSDIMG, 100, 100);
  }
  else if (MSDIMG.xPos< 0){
    moveRight = true;
  }


  // if(myIMG.xPos > canvasW - myIMG.width){
  //
  // }

  window.requestAnimationFrame(animateImage);

  // if(MSDIMG.xPos <= canvasW - MSDIMG.width){
  //   window.requestAnimationFrame(animateImage);
  // }

}

// function flipHorizontally(img,x,y){
//   // move to x + img's width
//   canvas.translate(x+img.width,y);
//
//   // scaleX by -1; this "trick" flips horizontally
//   canvas.scale(-1,1);
//
//   // draw the img
//   // no need for x,y since we've already translated
//   canvas.drawImage(img,0,0);
//
//   // always clean up -- reset transformations to default
//   canvas.setTransform(1,0,0,1,0,0);
// }

function mainDrawing(){
  //change these static values to more dynamic ones
  //x,y, width, height
  // context.drawImage(myIMG, 200, 200, 100, 100);
  // context.strokeRect(200, 200, 100, 100);
  window.requestAnimationFrame(animateImage);

  //call animation //happens once
  // window.requestAnimationFrame(animateImage);
}

function handleClick(e){
  //update x and y pos of avatar
  myIMG.xPos = e.x;
  myIMG.yPos = e.y;
  //move box around image
  //erase the old image
  //functiom to erase
  //eraseOld();
  // context.drawImage(myIMG, e.x - 50, e.y - 50, 100, 100);
  // context.strokeRect(e.x - 50, e.y - 50, 100, 100);
}

function eraseOld(){
  //add layer on top of previous image
  context.fillStyle="rgb(114,5,128)";
  context.fillRect(0,0, canvasW, canvasH);
}

window.onload=mainDrawing;
//wihtout () bc passing function to another function?

//calling handleclick function when mose is moved, erases odl and draws/
document.onmousemove = handleClick;
