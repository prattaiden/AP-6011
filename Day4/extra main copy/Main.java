import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {

        //create the object that is going to wait and that is the server socket
        ServerSocket server = new ServerSocket(8080);
        //need to wait for the client
        //socket for the client using .accept

        String fileFound = "";

        while (true) {//wait forever until the client socket is there
            Socket client = server.accept();

            Scanner scanner = new Scanner(client.getInputStream());

            //if statement, not while, because the socket might not have something
            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                fileFound = input.split(" ")[1];
                System.out.println(fileFound);
            }

            //Wrapping in print writer class so we don't have to do it byte by byte
            PrintWriter outStream = new PrintWriter(client.getOutputStream());

            //basic headers
            File f = new File("/Users/aidenpratt/Documents/AP-6011/Day4/HTTPWebServerHW" + fileFound);


            // Get the extension from the fileFound string
            //make this a method
            String fileNameExtension = fileFound;
            String extension = "";
            // Extract the extension from the file name
            int index = fileNameExtension.lastIndexOf('.');
            if (index > 0) {
                extension = fileNameExtension.substring(index + 1);
                System.out.println(extension);
            }

                //Creating a filestream to read the contents of the file
                FileInputStream fileStream = new FileInputStream(f);
                OutputStream outputStream = client.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);


                //IF STATEMENT FOR IF THE FILE EXISTS
                if (extension.equals("html")) {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: text/html\n".getBytes());
                }
                else if (extension.equals("css")) {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: text/css\n".getBytes());
                }
                else if (extension.equals("jpeg")) {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: image/jpeg\n".getBytes());
                }
                outputStream.write("\n".getBytes());


                fileStream.transferTo(outputStream);

                outputStream.flush();
                outputStream.close();

        }


    }


}
