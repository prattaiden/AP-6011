import java.util.HashMap;
import java.util.Scanner;

public class HTTPRequest {


    public String _filename;

    public HashMap<String, String> header_ = new HashMap<>();

    Boolean typeIsWebSocket = false;

//CONSTRUCTOR
public HTTPRequest(Scanner scanner) {
    //String input = getRequest(scanner);

    _filename = getFileName();

}

//HANDLE REQUEST
public void getRequest(Scanner scanner) {
    String _input = "";
    //READ-IN-FILE-NAME
    // Check if there is any data available to read from the client socket.

    //for web socket::::
    //get first line and skip it
    _input = scanner.nextLine();

    System.out.println(_input);

    //then store
    _filename = _input.split(" ")[1];

    if(_filename.equals("/")){
        _filename = "/index.html";
    }

    System.out.println("file name requested: " + _filename);

    System.out.println(" input " + _input);


        while (!_input.equals("")) {
            // Read the next line of input from the client socket.
            _input = scanner.nextLine();
           // System.out.println("in the while loop , input: " + _input);

            if(!_input.equals("")) {
                String key = _input.split(": ")[0];
                String val = _input.split(": ")[1];
                header_.put(key, val);
                System.out.println(_input);
                //add the rest to the hashmap
            }
        }
        //what is going into the header
        if (header_.get("Connection").equals("Upgrade")) {
            typeIsWebSocket = true;
        }

}

//FILE NAME
    // Split the input string using space as a delimiter to separate elements.
    // expecting an HTTP request, and we're interested in the second element,
    // which is the requested filename.
    public String getFileName (){
        return _filename;
    }

//FILE EXTENSION
    public String getExtension (String filename){

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