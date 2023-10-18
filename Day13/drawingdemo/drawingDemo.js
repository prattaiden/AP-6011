"use strict";

//let canvas = document.getElementsByTagName("canvas")[0];
let canvas = document.getElementById("canvasDrawing");
let context = canvas.getContext("2d");
let canvasW = canvas.width;
let canvasH = canvas.height;
let myIMG= new Image();
myIMG.src = "avatar.jpeg";

function mainDrawing(){
  //change these static values to more dynamic ones
  //x,y, width, height
  context.drawImage(myIMG, 20, 20, 100, 100);
  context.strokeRect(20, 20, 100, 100);
}

window.onload=mainDrawing;
//wihtout () bc passing function to another function?
