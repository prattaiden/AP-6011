import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    long numerator = scan.nextLong();
    long denominator = scan.nextLong();

    Fraction f1 = new Fraction(numerator, denominator);

    System.out.println(f1.toString());
    }
}