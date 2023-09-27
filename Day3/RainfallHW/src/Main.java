import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RainData r1 = new RainData("/Users/aidenpratt/Documents/AP-6011/Day3/RainfallHW/rainfall_data.txt");
        r1.ReadFile();
        r1.WriteFile();
    }
}