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

            String fileName = "";
            //if statement, not while, because the socket might not have something
            if (scanner.hasNext()) {
                fileFound = scanner.nextLine();
                String[] parts = fileFound.split(" ");
                System.out.println(fileFound);
                fileName = parts[1];
                if (parts[1].equals("/")) {
                    fileName = "/index.html";
                }

                // add / for default
            }



                //basic headers
                File f = new File("/Users/aidenpratt/Documents/AP-6011/Day4/HTTPWebServerHW/Resources" +fileName);
                File error = new File("/Users/aidenpratt/Documents/AP-6011/Day4/HTTPWebServerHW/Resources/error.html");

                //if file exists, else send error code
                OutputStream outputStream = client.getOutputStream();

                if (f.exists()) {
                    // Get the extension from the fileFound string
                    //make this a method
                    String fileNameExtension = fileName;
                    String extension = "";
                    // Extract the extension from the file name
                    int index = fileNameExtension.lastIndexOf('.');
                    if (index > 0) {
                        extension = fileNameExtension.substring(index + 1);
                        System.out.println(extension);
                    }

                    //Creating a filestream to read the contents of the file
                    FileInputStream fileStream = new FileInputStream(f);

                    PrintWriter printWriter = new PrintWriter(outputStream);


                    //IF STATEMENT FOR IF THE FILE EXISTS
                    if (extension.equals("html")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: text/html\n".getBytes());
                    } else if (extension.equals("css")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: text/css\n".getBytes());
                    } else if (extension.equals("jpeg")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: image/jpeg\n".getBytes());
                    }
                    outputStream.write("\n".getBytes());


                    fileStream.transferTo(outputStream);

                    outputStream.flush();
                    outputStream.close();

                }
                else {
                    //read fail file html
                    FileInputStream failFileStream = new FileInputStream(error);

                    //UNSuccess header
                    outputStream.write("HTTP/1.1 404 ERROR\n".getBytes());

                    //Content header
                    outputStream.write("Content-type: text/html\n".getBytes());

                    //Header spacer
                    outputStream.write("\n".getBytes());

                    //Send file
                    failFileStream.transferTo(outputStream);

                    //Flush and close file
                    outputStream.flush();
                    outputStream.close();

                }
            }
        }
}

