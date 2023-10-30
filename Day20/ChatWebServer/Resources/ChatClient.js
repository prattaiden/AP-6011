"use strict"

//getting elements from the html so they can be edited
const roomName = document.getElementById("RoomName");
const message = document.getElementById("Message");
//message.readOnly = true;
const userName = document.getElementById("UserName");

const chatDiv = document.getElementById("chatBox");
const UsersDiv = document.getElementById("statusBox");

const MSGButton = document.getElementById("messageSend");
const LeaveButton = document.getElementById("leave");
const SubButton = document.getElementById("submit");

let userNamesList = [];
let wsOpen = false;

let ws =  new WebSocket("ws://localhost:8080");
console.log("created the web socket");



ws.onopen = function (){
  console.log("ws is now open!!!")
  wsOpen = true;
}

//---------------------------------------ON MESSAGE------------------------------------
ws.onmessage = function (e) {
  //JSON from server to parse through the string
  //console.log("Message Sai :: "+e.data);
  let data = JSON.parse(e.data);

  //pargraph element for the message field of the app
  let msg = document.createElement("p");

  //if statement to check the type of data and ensure that a user and room is enterted in their fields
  if (data.type === "join" && data.user !== "" && data.room !== "") {
    // Create a <p> element for the user
    let users = document.createElement("p");
    users.textContent = data.user;

    // Add the 'users' element to the 'UsersDiv'
    UsersDiv.appendChild(users);
    msg.textContent = data.user + " joined: " +data.room;

    // Save the user's name to 'userNamesList'
    userNamesList.push(data.user);
    console.log(userNamesList);
  }


      // Set the text content of the msg element to the user and entering the room
    //--------------------------------------------------------------------------
    //   userNamesList.push(data.user);
    //
    //   // Add usernames to UsersDiv
    //   for (let i = 0; i < userNamesList.length; i++) {
    //     msg.textContent = data.user + " joined: " + data.room;
    //     let user = userNamesList[i];
    //     let userElement = document.createElement('p');
    //     userElement.textContent = user;
    //     UsersDiv.appendChild(userElement);
    //   }
    //
    //   console.log("name list " + userNamesList.toString());
    //
    // }
    //---------------------------------------------------------------------------

    // Set the text content of the 'users' element to the name of the user

    // Output 'userNamesList' for debugging

//-------------------------------------------------------------------------------
  // if (data.type === "join" && data.user != "" && data.room != "") {
  //   //users element for the users field of the app
  //   let users = document.createElement("p");
  //
  //   //sets the text content of msg element to the user and entering the room
  //   msg.textContent = data.user + " joined: " + data.room;
  //
  //   //setting the text content of users to the name of the user
  //   users.textContent = data.user;
  //   //saving the user id as the data.user
  //   users.id = data.user;
  //   //adding the users to the users div
  //   //sending back to client
  //   userNamesList.push(users.textContent);
  //   console.log("name list " + userNamesList.toString());
  //
  //   for (let i = 0; i <= userNamesList.length; i++) {
  //   UsersDiv.appendChild(userNamesList[i]);
  //   }


      //-----------------------------------messages------------------------------------------------

  //if data from JSON is a message
  //makes so it empty messages cannot be sent
 else if (data.type === "message" && data.message != null && data.user != null) {
    //text content is the output onto the chat screen
    msg.textContent =  data.user + ": " + data.message;
  }

 //-------------------------------------leaving-----------------------------------------------------

  //if data from the JSON is leave
  else if (data.type === "leave"){
    console.log("received leave msg")
    let userThatLeft = document.getElementById(data.user);

    msg.textContent = data.user + " left: " + data.room;

    if(userThatLeft) {
      UsersDiv.removeChild(userThatLeft);

    }


  }

  //sending the message type back to the client by appending the child
  chatDiv.appendChild(msg);
}

//-------------------------------------METHODS-------------------------------------------
function handleKeyPressForJoinRoom(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
  }

  if (e.keyCode === 13) { //13 is the enter key
    const name = roomName.value;

    // Define a regular expression pattern to match only lowercase letters
    //^ = start matching from the beginning of the string. $ = continue matching until the end of the string.
    const lowercaseLetters = /^[a-z]+$/;

    //test method returns true if the string matches the pattern, so if not true, do the following
    if (!lowercaseLetters.test(name) || name.includes(' ')) {
      alert("Your room-name must contain only lowercase letters (and no spaces).");
    } else {

      //string for the JSON to send to the web socket
      let joinJSON = {"type": "join", "user": userName.value, "room": roomName.value}
      //"join " + userName.value + " " + roomName.value;


      //userNamesList.push(joinJSON);
      //console.log("LENGTH" + userNamesList.length);



      ws.send(JSON.stringify(joinJSON));


      userName.readOnly = true;
      roomName.readOnly = true;

    }
  }
}

function handleClickJoinRoom(e){
  handleKeyPressForJoinRoom(e.keyCode.type(13));
}

function handleSendMessageEnter(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
  }

  if (e.keyCode === 13) {
    //string for JSON then sending to web socket
    let stringMessage= {"type":"message", "user":userName.value, "room":roomName.value, "message":message.value}

    ws.send(JSON.stringify(stringMessage));


    //erases what the user entered into the message field
    message.value = "";
  }
}

function handleSendMessageClick(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
  }

  if (e != null) {
    let stringMessage = {"type":"message", "user":userName.value, "room":roomName.value, "message":message.value}

    ws.send(JSON.stringify(stringMessage));
    message.value = "";
  }
}

function handleEscape(e){
  if(!wsOpen){
    console.log("web socket is not open yet..");
  }
    let stringEscape = {"type":"leave", "user":userName.value, "room":roomName.value}
    ws.send(JSON.stringify(stringEscape));

  //can enter text into username field
  //username field set to empty
  userName.readOnly = false;
  userName.value = "";

  //can enter text into room field
  //room field set to empty
  roomName.readOnly = false;
  roomName.value = "";

  //cannot enter text into message field
  //set message field set to empty
  message.readOnly = true;
  message.value = "";

//removing any text from the chat and user fields
  UsersDiv.innerHTML = "";
  chatDiv.innerHTML = "you left the room";
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


