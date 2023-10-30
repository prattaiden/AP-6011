import java.io.*;
import java.net.Socket;
import java.lang.Exception;
import java.security.NoSuchAlgorithmException;

public class myRunnable implements Runnable{

    public Socket client_;

    String filename = "";

    Room room_;

    myRunnable(Socket client){
        client_ = client;
    }

    @Override
    public void run() {
        try {
           // System.out.println("Entered the client handler thread");
            //READ-&-GRAB-FILE-NAME
            //HTTPRequest class
            HTTPRequest http = new HTTPRequest();
            try {
                http.getRequest(client_);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
           // System.out.println(client_);

            //if it is a websocket is true
            if( http.typeIsWebSocket ) {

                //helper static function in websocket helper class
                //Sending the handshake back
                try {
                    WebSocketHelper.sendWebSocketHandshake(client_.getOutputStream(), http);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }

                //while true loop for while the type is a web socket
                //will read and then parse through the bits being sent
                while (true) {
                    try {
                        //decoding the message so that it comes in as a JSON
                       String message = WebSocketHelper.decodeIncomingWSMessage(client_.getInputStream());
                       // System.out.println("decoded JSON msg: " + message);


                        //writing the message through binary
                       // String nextMSG = WebSocketHelper.writeWSMessage(message,client_.getOutputStream());
                       // System.out.println("next message sending back?: " + nextMSG);


                        String userNameFound = WebSocketHelper.findUser(message);

                        //finding the type
                        String typeFound = WebSocketHelper.findType(message);
//                        System.out.println("type is: " + typeFound);

                        //PARSING JSON IF STATEMENTS-------------------------------------------------------

                        if (typeFound.equals("join")) {
                            ;
                            //  System.out.println("joining");
                            String joinString = message;
                            //finding the room from the JSON
                            String roomNameFound = WebSocketHelper.findRoom(joinString);
                            //creating or joining the room
                            room_ = Room.getRoom(roomNameFound);
                            // room_.sendMessage(nextMSG);
                            //room_.addMessage(joinString);

//                            CHECKING IF USER ALREADY EXISTS SO NO DOUBLE MESSAGES
                            if (!room_.containsUser(userNameFound)) {
                                //adds clients to scoket list so they see eachother
                                room_.addClient(client_);
                                //add username to username string list
                                room_.addUserName(userNameFound);
                                System.out.println("added user: " + userNameFound + " to room: " + roomNameFound);

                                room_.addUser(joinString);

                                //HISTORY TEST
                                //send history in this if statement to only this clinet
                                if(!room_.isHistorySent()) {
                                for (String history : room_.getUsersHistory()) {
                                    room_.sendMessage(history);
                                }
                                room_.markHistorySent();
                            }
                            room_.unMarkHistorySent();


                            }
//                            if(!room_.isHistorySent()) {
//                                for (String history : room_.getMessageHistory()) {
//                                    room_.sendMessage(history);
//                                }
//                                room_.markHistorySent();
//                            }
//                            room_.unMarkHistorySent();
//
//                            room_.addClient(client_);

                            //room_.addMessage(joinString);
                            room_.sendMessage(joinString);

                        }


                        if(typeFound.equals("leave")){
                            //System.out.println("leaving");
                            room_.removeClient(client_);
                            room_.removeUserName(userNameFound);
                            System.out.println("array list of user names in this room: " + room_.getUserNAmes());
                            room_.sendMessage(message);
                        }

                        if(typeFound.equals("message")){
                            String messageString = message;
                            //fining the message from the JSON
                            String messageFound = WebSocketHelper.findMessage(messageString);
                            System.out.println("the message is: " + messageFound);

                            //adding the message to the message histroy string array
                            room_.addMessage(messageString);
                            //room_.sendMessage(messageString);

                            //checking for history
                            if(!room_.isHistorySent()) {
                                for (String history : room_.getMessageHistory()) {
                                    room_.sendMessage(history);
                                }
                                room_.markHistorySent();
                            }
                            room_.unMarkHistorySent();

                            //room_.sendMessage(messageString);

//                            for(String history : room_.getMessageHistory()) {
//                                room_.sendMessage(history);
//                            }

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

                //System.out.println("output stream of client created " + outputStream);

                //Create a httpResponse
                HTTPResponse httpResponse = new HTTPResponse(filename, file, outputStream, failfile, http);
                //System.out.println("httpreponse created ? but notin " + httpResponse);

            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
