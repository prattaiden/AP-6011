import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;


public class WebSocketHelper {

    //public static Room room_;
    static Boolean masked_ = false;

    static int finalLength_ = 0;

    // Room room_;

    //constructor
    public WebSocketHelper() {

    }

    public static Boolean handshakeComplete_ = false;

    public static void sendWebSocketHandshake(OutputStream outputStream, HTTPRequest httpRequest) throws NoSuchAlgorithmException, IOException {

        HashMap headers = httpRequest.headers_;
        System.out.println("ready to send handshake");

        String encodeKey = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1")
                .digest((headers.get("Sec-WebSocket-Key")
                        + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8")));
        if (httpRequest.typeIsWebSocket) {
            System.out.println("handshake made websocket true");

            outputStream.write("HTTP/1.1 101 Switching Protocols\r\n".getBytes());
            outputStream.write("Upgrade: websocket\r\n".getBytes());
            outputStream.write("Connection: Upgrade\r\n".getBytes());
            outputStream.write(("Sec-WebSocket-Accept: " + encodeKey + "\r\n").getBytes());
            outputStream.write("\r\n".getBytes()); // blank line means end of headers

            //once these are written handshake is complete
            //switching the boolean

            outputStream.flush();

            handshakeComplete_ = true;
        }

    }

    public static String decodeIncomingWSMessage(InputStream inputStream) throws IOException {

        DataInputStream DIS = new DataInputStream(inputStream);

        byte[] byteArrayShort1 = DIS.readNBytes(2);

        Byte zeroByte = byteArrayShort1[0];
        // System.out.println(zeroByte);
        Byte firstByte = byteArrayShort1[1];

        //PARSE FOR MASK AND LENGTH
        //Find maskKey, is it masked?
        byte maskKey = (byte) (firstByte & 0x80);
        if (maskKey != 0) {
            masked_ = true;
        }
        //is it a text message or binary? opcode
        byte opCode = (byte) (zeroByte & 0xF0);
        //somehow determine

        byte payloadLengthChecker = (byte) (firstByte & 0x7F);

        //if length is 125 or less, length is just found in B1 here
        if (payloadLengthChecker <= 125) {
            finalLength_ = payloadLengthChecker;
        } else if (payloadLengthChecker == 126) {
            // finalLength_ = payloadLengthChecker;
            byte byteTwoAndThree = (byte) DIS.readShort();
            finalLength_ = byteTwoAndThree;
            //  unsigned Short shortBytes = input.readShort();
        } else if (payloadLengthChecker == 127) {
            //finalLength_ = payloadLengthChecker;
            byte byteTwoToNine = (byte) DIS.readLong();
            finalLength_ = byteTwoToNine;
        }

        //if masked is true, read the next four bytes
        byte[] decodedArray = new byte[finalLength_];
        if (masked_) {
            //masked array bytes is 4 bytes
            byte[] maskedArray = DIS.readNBytes(4);
            byte[] encodedArray = DIS.readNBytes(finalLength_);
            for (int i = 0; i < finalLength_; i++) {
                //maskedArray[i] = DIS.readNByte();
                decodedArray[i] = (byte) (encodedArray[i] ^ maskedArray[i % 4]);
            }

        } else {
            decodedArray = DIS.readNBytes(finalLength_);
        }

        return new String(decodedArray);
    }


    //-------------------------UGLY METHODS TO GO THROUGH JSON------------------------------------
    public static String findTargetValue(String message, String targetKey) {


        // Step 1: Remove the curly braces from the JSON string
        String jsonContent = message.substring(1, message.length() - 1);
        String[] pairsSplit = jsonContent.split(",");

        String targetValue = "";

        for (String pair : pairsSplit) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].replaceAll("\"", "").trim();
            String value = keyValue[1].replaceAll("\"", "").trim();

            if (key.equals(targetKey)) {
                targetValue = value;
            }
        }

        return targetValue;
    }
}

//    public static String writeWSMessage(String message, OutputStream outputStream) throws IOException {
//
//        DataOutputStream DOS = new DataOutputStream(outputStream);
//
//        //first byte sending back
//        //FIN and opcode as text message
//        byte firstByte = (byte) 0x81;
//        DOS.write(firstByte);
//
//        int decodedMessage = message.length();
//
//        if (decodedMessage <= 125) {
//            DOS.writeByte(message.length());
//        } else if (decodedMessage == 126) {
//            DOS.write(126);
//            DOS.writeShort(message.length());
//        } else if (decodedMessage == 127) {
//            DOS.write(127);
//            DOS.writeLong(message.length());
//        }
//
//        //System.out.println(message);
//
//        //save message into a string
//        //get the string after "room":
//
//
//        DOS.writeBytes(message);
//
//        DOS.flush();
//
//        return message;
//    }
