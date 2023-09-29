import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class FractionTest {

    @Test
    public void runAllTests(){
        testTimes();
        testDivision();
        testDouble();
        testNegatives();
        testMinus();
        testPlus();
        testDenominatorZero();
    }

    @Test
    public void testNegatives() {
        Fraction f1 = new Fraction(-1, 2);
        Fraction f2 = new Fraction(1, -4);
        Fraction f3 = new Fraction(-1, -6);
        Fraction f4 = new Fraction(1, 8);


        Assertions.assertEquals(f1.toString(), "-1/2");
        Assertions.assertEquals(f2.toString(), "-1/4");
        Assertions.assertEquals(f3.toString(), "1/6");
        Assertions.assertEquals(f4.toString(), "1/8");
    }

    @Test
    public void testTimes() {
        Fraction f1 = new Fraction(1, 2 );
        Fraction f2 = new Fraction( 1, 3 );
        Fraction f3 = f1.times( f2 );

        Assertions.assertEquals( f3.toString(), "1/6" );
    }

    @Test
    public void testPlus(){
        Fraction f1 = new Fraction(1, 2 );
        Fraction f2 = new Fraction( 1, 3 );
        Fraction f3 = f1.plus( f2 );

        Assertions.assertEquals( f3.toString(), "5/6" );
    }

    @Test
    public void testMinus(){
        Fraction f1 = new Fraction(1, 2 );
        Fraction f2 = new Fraction( 1, 3 );
        Fraction f3 = f1.minus( f2 );

        Assertions.assertEquals( f3.toString(), "1/6" );
    }


    @Test
    public void testDivision(){
        Fraction f1 = new Fraction(1, 2 );
        Fraction f2 = new Fraction( 1, 3 );
        Fraction f3 = f1.divide( f2 );

        Assertions.assertEquals( f3.toString(), "3/2" );
    }


    @Test
    public void testDouble(){
        Fraction f1 = new Fraction(6, 3 );
        Double f1double = f1.toDouble();
        Assertions.assertEquals( f1double, 2.0 );
    }

    @Test
    public void testDenominatorZero() {
        try {
            Fraction f1 = new Fraction(10, 0);
        }
        catch(Exception e){
            System.out.println("Denominator cannot be zero.");
        }
    }
}

