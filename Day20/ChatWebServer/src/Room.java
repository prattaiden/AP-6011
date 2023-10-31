import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String roomName_;

    private static HashMap<String, Room> rooms = new HashMap<>();

    private ArrayList<Socket> clientSockets = new ArrayList<>();

   private ArrayList<String> userNAmes = new ArrayList<>();

   private ArrayList<String> messageHistory = new ArrayList<>();

   private ArrayList<String> userHistory = new ArrayList<>();

    //constructor
    private Room( String name ) {
        roomName_ = name;
    }

    //if username is already in the array list UserNAmes
    public boolean containsUser(String userName){
        return userNAmes.contains(userName);
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


    //---------------------------adding and removing from the SOCKET array list of clients--------------------
    public synchronized void addClient(Socket client, String userName) {
        clientSockets.add(client);
        userNAmes.add(userName);
        //System.out.println("user added" + clientSockets);
    }

    public synchronized void removeClient(Socket client, String userName) {
        clientSockets.remove(client);
        userNAmes.remove(userName);
        //System.out.println("user removed" + clientSockets);
    }

    //--------------------------------------------MESSAGES-------------------------------------------
    public synchronized void addMessage(String message){
        messageHistory.add(message);
    }

    //USERS
    public synchronized void addUser(String Users){
        userHistory.add(Users);
    }

    public synchronized void constructMSG(String msg, DataOutputStream dos) throws IOException {

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

        dos.writeBytes(msg);
        dos.flush();

    }
    public synchronized void sendMessageToClient(String msg, Socket client) throws IOException {
        OutputStream outputStream = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);

        constructMSG(msg, dos);

              }

    public synchronized void sendMessage(String msg) throws IOException {
        System.out.println("number of users to send to: " + clientSockets.size());
        for (Socket c : clientSockets) {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            constructMSG(msg, dos);
        }
    }


}


