import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HTTPRequest {


    static String _filename;

//CONSTRUCTOR
public HTTPRequest(Scanner scanner) {
    String input = getRequest(scanner);
    _filename = getFileName(input);

}

//HANDLE REQUEST
public static String getRequest(Scanner scanner) {
    String _input = "";
    //READ-IN-FILE-NAME
    // Check if there is any data available to read from the client socket.
    if (scanner.hasNext()) {
        // Read the next line of input from the client socket.
         _input = scanner.nextLine();


    }
    return _input;
}

//FILE NAME
    // Split the input string using space as a delimiter to separate elements.
    // expecting an HTTP request, and we're interested in the second element,
    // which is the requested filename.
    public static String getFileName (String input){
        _filename = input.split(" ")[1];

        if(_filename.equals("/")){
            _filename = "/index.html";
        }

        System.out.println(_filename);

        return _filename;
    }

//FILE EXTENSION
    public static String getExtension (String filename){


// Initialize an empty string to store the file extension
        String extension = "";
// Find the last occurrence of the dot (.) character in the filename
        int i = filename.lastIndexOf('.');
// Check if a dot was found in the filename
        if (i > 0) {
            // Extract the substring after the last dot, which represents the file extension
            extension = filename.substring(i + 1);
            // Print the extracted file extension to the console for debugging
        }
        return extension;
    }
}