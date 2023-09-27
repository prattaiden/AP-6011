import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



//RainData Class
public class RainData {
    private String path;
    private String path2;

    //constructor
    RainData(String path) {
        this.path = path;
    }

    //GLOBAL ARRAYS
    //making ArrayList of Strings for the City
    ArrayList<String> CityString = new ArrayList<>();

    //making ArrayList of Strings for the Months
    ArrayList<String> MonthString = new ArrayList<>();

    //making ArrayList of Ints for the Years
    ArrayList<String> YearInt = new ArrayList<>();

    //making ArrayList of Doubles for the Rainfall in inches
    ArrayList<Double> RainfallDouble = new ArrayList<>();

    //READING and scanning file in
    public void ReadFile() throws FileNotFoundException {
        //Scanner to read through the file path
        Scanner fileReader = new Scanner(new FileInputStream(path));
        //while loop reading through the file line by line
        while (fileReader.hasNextLine()) {
            String[] line = fileReader.nextLine().split("\\s+");
            //every 12 values come backto Janurary,
            if (line.length == 1) {
                CityString.add(line[0]);
            } else if (line.length > 2) {
                RainfallDouble.add(Double.valueOf(line[2]));
                MonthString.add(line[0]);
                YearInt.add(line[1]);
            }
        }
    }

    public ArrayList<Double> getAverageRains() {

        //making ArrayList of doubles for the average rainfalls of each month
        ArrayList<Double> AverageRainFall = new ArrayList<>();

        double janSum = 0;
        double janAvg = 0;

        double febSum = 0;
        double febAvg = 0;

        double marSum = 0;
        double marAvg = 0;

        double aprSum = 0;
        double aprAvg = 0;

        double maySum = 0;
        double mayAvg = 0;

        double junSum = 0;
        double junAvg = 0;

        double julSum = 0;
        double julAvg = 0;

        double augSum = 0;
        double augAvg = 0;

        double sepSum = 0;
        double sepAvg = 0;

        double octSum = 0;
        double octAvg = 0;

        double novSum = 0;
        double novAvg = 0;

        double decSum = 0;
        double decAvg = 0;


        for (int i = 0; i < MonthString.size(); i++) {

            if (MonthString.get(i).equals("January")) {
                janSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("February")) {
                febSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("March")) {
                marSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("April")) {
                aprSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("May")) {
                maySum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("June")) {
                junSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("July")) {
                julSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("August")) {
                augSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("September")) {
                sepSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("October")) {
                octSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("November")) {
                novSum += RainfallDouble.get(i);
            } else if (MonthString.get(i).equals("December")) {
                decSum += RainfallDouble.get(i);
            }
        }

        janAvg = janSum / 20;
        febAvg = febSum / 20;
        marAvg = marSum / 20;
        aprAvg = aprSum / 20;
        mayAvg = maySum / 20;
        junAvg = junSum / 20;
        julAvg = julSum / 20;
        augAvg = augSum / 20;
        sepAvg = sepSum / 20;
        octAvg = octSum / 20;
        novAvg = novSum / 20;
        decAvg = decSum / 20;

       AverageRainFall.add(janAvg);
       AverageRainFall.add(febAvg);
       AverageRainFall.add(marAvg);
       AverageRainFall.add(aprAvg);
       AverageRainFall.add(mayAvg);
       AverageRainFall.add(junAvg);
       AverageRainFall.add(julAvg);
       AverageRainFall.add(augAvg);
       AverageRainFall.add(sepAvg);
       AverageRainFall.add(octAvg);
       AverageRainFall.add(novAvg);
       AverageRainFall.add(decAvg);

       return AverageRainFall;

    }


    public void WriteFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("/Users/aidenpratt/Documents/AP-6011/Day3/RainfallHW/raindata_results.txt"));

        for(int i = 0; i < 12; i ++) {
            pw.write("The average rainfall amount for " + MonthString.get(i) + " is " + getAverageRains().get(i) + " inches\n\n");
        }
        pw.close();
    }


//CLASS FINAL
}