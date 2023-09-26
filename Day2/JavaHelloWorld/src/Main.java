import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.printf("hello world\n");

        //creating an arraylist of Integers
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        System.out.println(list);

        //getting the sum of my array
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += list.get(i);
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

