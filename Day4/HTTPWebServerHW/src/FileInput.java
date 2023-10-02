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
public class FileInput {



    public static String getExtension(File file) {

        // Get the extension from the fileFound string
        String fileNameExtension = fileNameExtension;
        String extension = "";
        // Extract the extension from the file name
        int index = fileNameExtension.lastIndexOf('.');
        if (index > 0) {
            extension = fileNameExtension.substring(index + 1);

            return extension;
        }


    }
}
