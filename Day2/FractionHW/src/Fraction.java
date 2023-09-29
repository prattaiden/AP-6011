public class Fraction {

  //Empty Constructor
    public  Fraction(){
        _denominator = 1;
        _numerator = 0;
        _realRepresentation = 0.0;
        GCD_ = 1;
        _remainder = 0;
        _quotient = 0;
    }
//Constructor with inputted long for numerator and denominator
    public Fraction(long n, long d){

        _numerator = n;
        _denominator = d;
        //throwing an error if denominator is 0
        if (_denominator == 0) throw new AssertionError();

       // if statement to correct the location of - sign
        if(_denominator < 0){
            _numerator = _numerator * -1;
            _denominator = _denominator * -1;
        }

        reduce();
    }

    //ADDITION
    //creating two long variables called result
    //if statement to check if the denominators of lhs fraction equals rhs fraction
    //if true, resultDenominator = _denominator and resultNumerator is the addition of lhs and rhs numerator
    //else statement occurs if the denominators are not equal for lhs and rhs
    //multiplies lhs and rhs denominator
    //cross multiplying numerators and denominators of lhs and rhs then adding
    //returning a "new" fraction with the result variables
    public Fraction plus(Fraction rhs){

        long resultNumerator, resultDenominator;

        if (_denominator == rhs._denominator){
            resultDenominator = _denominator;
            resultNumerator = _numerator + rhs._numerator;
        }
        else {
            resultDenominator = _denominator * rhs._denominator;
            resultNumerator = _numerator*(rhs._denominator) + rhs._numerator*(_denominator);
        }

        return new Fraction(resultNumerator, resultDenominator);
    }

    //SUBTRACTION
    //creating two long variables called result
    //if statement to check if the denominators of lhs fraction equals rhs fraction
    //if true, resultDenominator = _denominator and resultNumerator is the difference of lhs and rhs numerator
    //else statement occurs if the denominators are not equal for lhs and rhs
    //multiplies lhs and rhs denominator
    //cross multiplying numerators and denominators of lhs and rhs then subtracting
    //returning a "new" fraction with the result variables
    public Fraction minus(Fraction rhs){
        long resultNumerator, resultDenominator;

        if (_denominator == rhs._denominator){
            resultDenominator = _denominator;
            resultNumerator = _numerator - rhs._numerator;
        }
        else {
            resultDenominator = _denominator * rhs._denominator;
            resultNumerator = _numerator*(rhs._denominator) - rhs._numerator*(_denominator);
        }

        return new Fraction(resultNumerator, resultDenominator);
    }

    //MULTIPLICATION
    //creating long type variables as resultNumerator and resultDenominator
    //multiplying the lhs and rhs denominator
    //multiplying the lhs and rhs numerator
    //returning a new fraction with these results
    //the reduction function occurs in the constructor so is not needed here
    public Fraction times(Fraction rhs){
        long resultNumerator, resultDenominator;

        resultDenominator = _denominator * rhs._denominator;
        resultNumerator = _numerator * rhs._numerator;

        return new Fraction(resultNumerator, resultDenominator);
    }

    //DIVISION
    //declaring new long variables as resultNum and resultDen
    //finding the reciprocal, inverse, of the rhs
    //saving resultNum as the multiplication of lhs and rhs numerator
    //saving resultDen as the denominator of the lhs and rhs
    //returning a new fraction with the results
    public Fraction divide(Fraction rhs){
        long resultNum, resultDen;
        Fraction rhs1 =  rhs.reciprocal();
        resultNum = _numerator * rhs1._numerator;
        resultDen = _denominator * rhs1._denominator;

        return new Fraction(resultNum, resultDen);
    }

    //RECIPROCAL
    //swapping the denominator and the numerator
    public Fraction reciprocal(){
//    long temp = 0;
//   temp = _numerator;
//   _numerator = _denominator;
//   _denominator = temp;
//
//   return new Fraction(_numerator, _denominator);
   return new Fraction(_denominator, _numerator);

    }

    //converting the fraction to a string
    //overloading the toString method
    public String toString(){
    return _numerator + "/" + _denominator;
    }

    //converting the fraction to a double
    public double toDouble(){
        return (double)_numerator / _denominator;
    }

    //PRIVATE VARIABLES
    private long _numerator, _denominator;
    private double _realRepresentation;
    private long _quotient, _remainder;

    private long GCD_;


    //GCD
    //saving variable _gcd as the numerator
    //saving long variable remainder as denominator
    //
    private long gcd(long n, long d) {
        long gcd = _numerator;
        long remainder = _denominator;
        while (remainder != 0) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        if (gcd <0){
            gcd= gcd * -1;
        }
        return gcd;
    }

    //reduce function
    //this function uses the GCD function
    //divides numerator and denominator by the GCD
    private void reduce(){
       GCD_ = gcd((long) _numerator, (long) (_denominator));
       _numerator /= GCD_;
       _denominator /= GCD_;
       _remainder = _numerator % _denominator;
       _realRepresentation =  (_numerator) / (_denominator);
    }

}