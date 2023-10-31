import java.io.*;
import java.net.Socket;
import java.lang.Exception;
import java.security.NoSuchAlgorithmException;

public class myRunnable implements Runnable{

    private Socket client_;

    String filename = "";

    Room room_;

    myRunnable(Socket client){
        client_ = client;
    }

    @Override
    public void run() {
        try {
            //READ-&-GRAB-FILE-NAME
            //HTTPRequest class
            HTTPRequest http = new HTTPRequest();
            try {
                http.getRequest(client_);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            //if it is a websocket is true
            if( http.typeIsWebSocket ) {

                //helper static function in websocket helper class
                //Sending the handshake back
                try {
                    WebSocketHelper.sendWebSocketHandshake(client_.getOutputStream(), http);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }

                //boolean for when to stop connection, occurs when leave statement happens
                boolean stop = false;
                //while true loop for while the type is a web socket
                //will read and then parse through the bits being sent
                while (!stop) {
                    try {
                        //decoding the message so that it comes in as a JSON
                       String message = WebSocketHelper.decodeIncomingWSMessage(client_.getInputStream());

                       //finding the username target value
                        String userNameFound = WebSocketHelper.findTargetValue(message, "user");

                        //finding the type target value
                        String typeFound = WebSocketHelper.findTargetValue(message, "type");


                        //PARSING JSON IF STATEMENTS-------------------------------------------------------

                        if (typeFound.equals("join")) {
                            ;
                            //  System.out.println("joining");
                            String joinString = message;
                            //finding the room from the JSON
                            String roomNameFound = WebSocketHelper.findTargetValue(joinString, "room");
                            //creating or joining the room
                            room_ = Room.getRoom(roomNameFound);

                            room_.sendMessage(joinString);

//                            CHECKING IF USER ALREADY EXISTS SO NO DOUBLE MESSAGES
                            if (!room_.containsUser(userNameFound)) {
                                //adds clients to scoket list so they see eachother
                                room_.addMessage(joinString);
                                room_.addClient(client_, userNameFound);

                               // System.out.println("added user: " + userNameFound + " to room: " + roomNameFound);

                                //looping through the message history and sending it to an new user joining the room
                                for(String history : room_.getMessageHistory()){
                                    room_.sendMessageToClient(history, client_);
                                }
                            }
                        }

                        if(typeFound.equals("leave")){
                            //System.out.println("leaving");
                            room_.removeClient(client_, userNameFound);

                           // System.out.println("array list of user names in this room: " + room_.getUserNAmes());
                            room_.sendMessage(message);
                            stop = true;
                        }

                        if(typeFound.equals("message")){
                            String messageString = message;
                            //fining the message from the JSON
                            String messageFound = WebSocketHelper.findTargetValue(messageString, "message");
                            //System.out.println("the message is: " + messageFound);

                            //adding the message to the message history string array
                            room_.addMessage(messageString);
                            room_.sendMessage(messageString);
                        }


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            //---------------------------else get file name, not WS--------------------------------------

            else {

                filename = http.getFileName();


                //Opening the file
                //relative path, bc its using current director, don't need '/', if included, won't find file
                File file = new File("Resources" + filename);
                File failfile = new File("Resources/error.html");

                // Get the output stream from the client socket to send the HTTP response
                //we obtain the output stream (outputStream) from the client socket (client). This stream allows
                // us to send data back to the client. and also create a PrintWriter (printWriter) to write text-based
                // data to the output stream.

                OutputStream outputStream = client_.getOutputStream();

                //Create a httpResponse
                new HTTPResponse(filename, file, outputStream, failfile, http);


            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
