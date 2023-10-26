import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class myRunnable implements Runnable{

    Socket client_;

    String filename = "";

    myRunnable(Socket client){
        client_ = client;
    }

    @Override
    public void run() {

        System.out.println("Entered the server");

        //Reading input from the client, wrap in scanner stream
        Scanner scanner = null;
        try {
            scanner = new Scanner(client_.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //READ-&-GRAB-FILE-NAME
        //HTTPRequest class
        HTTPRequest http = new HTTPRequest(scanner);
        http.getRequest(scanner);
        filename = http.getFileName();



        //Opening the file
        //relative path, bc its using current director, don't need '/', if included, won't find file
        File file = new File("Resources" + filename);
        File failfile = new File("Resources/error.html");

        // Get the output stream from the client socket to send the HTTP response
        //we obtain the output stream (outputStream) from the client socket (client). This stream allows
        // us to send data back to the client. and also create a PrintWriter (printWriter) to write text-based
        // data to the output stream.
        OutputStream outputStream = null;
        try {
            outputStream = client_.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Create a httpResponse
        try {
            HTTPResponse httpResponse = new HTTPResponse(filename, file, outputStream, failfile, http);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
