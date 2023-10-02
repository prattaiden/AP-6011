import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.printf("hello world\n");


        int[] newArray = new int[10];
        Random random = new Random();
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = random.nextInt(100);


            //getting the sum of my array
            int sum = 0;
            for (int o = 0; o < 10; o++) {
                sum += newArray[o];
            }
            System.out.println(sum);


            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your name: ");

            String name = scan.nextLine();

            System.out.println("Enter yout age: ");

            int age = scan.nextInt();

            System.out.println("name is: " + name);
            System.out.println("age is: " + age);

            if (age >= 80) {
                System.out.println("You are part of the greatest generation");
            } else if (age < 80 && age >= 60) {
                System.out.println("You are a baby boomer");
            } else if (age < 60 && age >= 40) {
                System.out.println("You are gen X");
            } else if (age < 40 && age >= 20) {
                System.out.println("You are a milliniel");
            } else {
                System.out.println("You are an iKid");
            }
        }
    }
}

