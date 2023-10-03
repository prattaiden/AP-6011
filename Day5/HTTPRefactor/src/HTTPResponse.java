import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HTTPResponse {

        private OutputStream outputStream;

        public HTTPResponse(String filename, File file, OutputStream outputStream, File failFile) throws IOException {
        // File exists, send it
            String extension = HTTPRequest.getExtension(filename);

            //Send file
            try{
            sendFile(file, outputStream, extension);
            } catch(FileNotFoundException e){

            // File does not exist, send 404 error
            sendFailFile(failFile, outputStream, extension);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            outputStream.flush();
            outputStream.close();
        }


//    public static String getContentType(String extension) {
//        return switch (extension.toLowerCase()) {
//            case "html" -> "text/html";
//            case "css" -> "text/css";
//            case "jpeg", "jpg" -> // Include additional JPEG extension
//                    "image/jpeg";
//            default -> "application/octet-stream";
//        };
//    }


    private static void sendFile(File file, OutputStream outputStream, String extension) throws IOException {

        //READ REQUESTED FILE
        //Creating a file stream to read the contents of the file
        try (FileInputStream fileStream = new FileInputStream(file)) {

            // Set the Content-type header based on the file extension
            switch (extension) {
                case "html" -> {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: text/html\n".getBytes());
                }
                case "css" -> {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: text/css\n".getBytes());
                }
                case "jpeg" -> {
                    outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outputStream.write("Content-type: image/jpeg\n".getBytes());
                }
            }

                    // Add an empty line to separate headers from the content
                    // crucial delimiter that separates the HTTP headers from the content.
                    outputStream.write("\n".getBytes());

                    //SEND-FILE-TO-CLIENT
                    // Transfer the file's content directly to the client's output stream
                    // use the transferTo method to efficiently transfer the content of the requested file (fileStream) directly
                    // to the client's output stream (outputStream).
                    // avoids reading the file line by line and is more efficient for sending binary data like images
                    fileStream.transferTo(outputStream);



        }
    }

    private static void sendFailFile(File file, OutputStream outputStream, String extension) throws IOException {

        //read fail file html
        FileInputStream failFileStream = new FileInputStream(file);

        //UNSuccess header
        outputStream.write("HTTP/1.1 404 NOT OK\n".getBytes());

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

