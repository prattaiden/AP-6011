import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String roomName_;

    private static HashMap<String, Room> rooms = new HashMap<>();

    private ArrayList<Socket> clientSockets = new ArrayList<>();

   private ArrayList<String> userNAmes = new ArrayList<>();

   private ArrayList<String> messageHistory = new ArrayList<>();

   private boolean historySent = false;


   //history and contains -----
    //if username is already in the array list UserNAmes
    public boolean containsUser(String userName){
        return userNAmes.contains(userName);
    }

    public void markHistorySent(){
        historySent = true;
    }

    public void unMarkHistorySent(){
        historySent = false;
    }

    public boolean isHistorySent(){
        return historySent;
    }

   //getter methods -------------------------
    public ArrayList<String> getMessageHistory(){
        return messageHistory;
    }

    public ArrayList<Socket> getClientSockets(){
        return clientSockets;
    }

    public ArrayList<String> getUserNAmes(){
        return userNAmes;
    }

    public synchronized static Room getRoom(String name) {
        Room room = rooms.get(name);
        if (room == null) {
            room = new Room(name);
            rooms.put(name, room);
            System.out.println("creating new room");
        }
        System.out.println("room exists");
        return room;
    }


    //adding and removing from the SOCKET array list of clients--------------
    public synchronized void addClient(Socket client) {
        clientSockets.add(client);
        //System.out.println("user added" + clientSockets);
    }

    public synchronized void removeClient(Socket client) {
        clientSockets.remove(client);
        //System.out.println("user removed" + clientSockets);
    }


    //adding and removing from the STRING array list of clients
    public  void addUserName(String userName) {
        userNAmes.add(userName);
        System.out.println("user name added: " + userName);
    }

    public  void removeUserName(String userName) {
        userNAmes.remove(userName);
        System.out.println("user name removed: " + userName);
    }

    //MESSAGES
    public synchronized void addMessage(String message){
        messageHistory.add(message);
    }

    public synchronized void sendMessage(String msg) throws IOException {
        System.out.println("number of users to send to: " + clientSockets.size());
        for (Socket c : clientSockets) {

            DataOutputStream dos = new DataOutputStream(c.getOutputStream());

            //first byte sending back
            //FIN and opcode as text message
            byte firstByte = (byte) 0x81;
            dos.write(firstByte);

            int decodedMessage = msg.length();

            if (decodedMessage <= 125) {
                dos.writeByte(msg.length());
            } else if (decodedMessage == 126) {
                dos.write(126);
                dos.writeShort(msg.length());
            } else if (decodedMessage == 127) {
                dos.write(127);
                dos.writeLong(msg.length());
            }
//
//            save message into a string
//            get the string after "room":


            dos.writeBytes(msg);
            c.getOutputStream().flush();
        }
    }


    //constructor
    private Room( String name ) {
        roomName_ = name;
    }


}


