import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //CREATE SERVER-SOCKET
        // Server-sockets wait for the client (attached to the server)
        ServerSocket server = new ServerSocket(8080);


       //(wait forever same as while true)
        while (true) {
            Socket client = server.accept();

            Thread thread = new Thread(new myRunnable(client));
            thread.start();
        }
    }
}