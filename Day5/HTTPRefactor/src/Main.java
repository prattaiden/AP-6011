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
        String filename = "";

       //(wait forever same as while true)
        while (true) {
            Socket client = server.accept();

            //Reading input from the client, wrap in scanner stream
            Scanner scanner = new Scanner(client.getInputStream());

            //READ-&-GRAB-FILE-NAME
            //HTTPRequest class
            filename = HTTPRequest.getFileName(HTTPRequest.getRequest(scanner));



            //Opening the file
            //relative path, bc its using current director, don't need '/', if included, won't find file
            File file = new File("Resources" + filename);
            File failfile = new File("Resources/error.html");


            // Get the output stream from the client socket to send the HTTP response
            //we obtain the output stream (outputStream) from the client socket (client). This stream allows
            // us to send data back to the client. and also create a PrintWriter (printWriter) to write text-based
            // data to the output stream.
            OutputStream outputStream = client.getOutputStream();

            //Create a httpResponse
            //HTTPResponse httpResponse = new HTTPResponse(filename, file, outputStream, failfile);

        }

    }
}