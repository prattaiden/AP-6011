"use strict"

const roomName = document.getElementById("RoomName");
const message = document.getElementById("Message");
//message.readOnly = true;
const userName = document.getElementById("UserName");

const chatDiv = document.getElementById("chatBox");
const statusDiv = document.getElementById("statusBox");

const MSGButton = document.getElementById("messageSend");
const LeaveButton = document.getElementById("leave");
const SubButton = document.getElementById("submit");
let wsOpen = false;

let ws =  new WebSocket("ws://localhost:8080");



ws.onopen = function (){
  wsOpen = true;
}

//---------------------------------------ON MESSAGE------------------------------------
ws.onmessage = function (e) {
  let data = JSON.parse(e.data);
  let msg = document.createElement("p");

  if (data.type === "join" && data.user != "" && data.room != ""){
    let users = document.createElement("p");

    msg.textContent = data.user + " has joined the room : " + data.room;
    users.textContent = data.user;
    users.id = data.user;
    statusDiv.appendChild(users);
  }
  else if (data.type === "message"){
    msg.textContent = data.user + ": " + data.message;
  }

  else if (data.type === "leave"){
    console.log("received leave msg")
    let userThatLeft = document.getElementById(data.user);
    //msg.textContent = "";
    msg.textContent = data.user + " left the room";
    statusDiv.removeChild(userThatLeft);
    statusDiv.innerHTML = "";

  }
  chatDiv.appendChild(msg);
}

//---------------------------------METHODS-------------------------------
function handleKeyPressForJoinRoom(e){
  //let data = JSON.parse(e.data);

  if (e.keyCode === 13){  // reminder: 13 is enter

    let string = "join " + userName.value + " " + roomName.value;

    ws.send(string);

    userName.readOnly = true;
    roomName.readOnly = true;
//

  }
}

function handleClickJoinRoom(e){
  //let data = JSON.parse(e.data);

  if (e != null){
    let string = "join " + userName.value + " " + roomName.value;

    ws.send(string);

    userName.readOnly = true;
    roomName.readOnly = true;


  }
}

function handleSendMessageEnter(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
    return;
  }

  if (e.keyCode === 13) {
    let stringM = "message " + message.value;

    ws.send(stringM);
    message.value = "";
  }
}

function handleSendMessageClick(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
    return;
  }

  if (e != null) {
    let stringM = "message " + message.value;

    ws.send(stringM);
    message.value = "";
  }
}

function handleEscape(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
    return;
  }
    let stringE = "leave " + "";
    ws.send(stringE);

  userName.readOnly = false;
  userName.value = "";
  roomName.readOnly = false;
  roomName.value = "";
  message.value = "";
  message.readOnly = true;
  statusDiv.innerHTML = "";
  chatDiv.innerHTML = "";
}


//Listeners for joining a room and entering a username
userName.addEventListener("keypress", handleKeyPressForJoinRoom);
roomName.addEventListener("keypress", handleKeyPressForJoinRoom);
SubButton.addEventListener("click", handleClickJoinRoom);

//Listeners for sending a message
message.addEventListener("keypress", handleSendMessageEnter);
MSGButton.addEventListener("click", handleSendMessageClick);

//Listener for leaving the room
LeaveButton.addEventListener("click", handleEscape);


