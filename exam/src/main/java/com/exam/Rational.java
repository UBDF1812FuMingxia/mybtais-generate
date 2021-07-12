package com.exam;

/**
 * @ClassName : RationalExam
 * @Description : 分数类的加、减、乘、除计算
 * @Author : fmx
 * @Date: 2021-07-09 17:14
 */
public class Rational extends Number implements Comparable {
    private long numerator = 0;
    private long denominator = 1;

    public Rational() {
        this(0, 1);
    }

    public Rational(long numerator, long denominator) {
        // TODO Auto-generated constructor stub
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    public static String geneter(long numerator, long denominator){
        Rational rational = new Rational(numerator, denominator);
        return rational.toString().replace("/",":");
    }

    private static long gcd(long n, long d) {
        // TODO Auto-generated method stub
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;

        for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0)
                gcd = k;
        }
        return gcd;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public Rational add(Rational secondRational) {
        long n = numerator * secondRational.getDenominator() +
                denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    public Rational subtract(Rational secondRational) {
        long n = numerator * secondRational.getDenominator() -
                denominator * secondRational.getNumerator();
        long d = denominator * secondRational.getDenominator();
        return new Rational(n, d);
    }

    public Rational multiply(Rational sR) {
        long n = numerator * sR.getNumerator();
        long d = denominator * sR.getDenominator();
        return new Rational(n, d);
    }

    public Rational divide(Rational sR) {
        long n = numerator * sR.denominator;
        long d = denominator * sR.numerator;
        return new Rational(n, d);
    }

    public String toString() {
        if (denominator == 1)
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    public boolean equals(Object parm1) {
        return (this.subtract((Rational) (parm1))).getNumerator() == 0;
    }


    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        if ((this.subtract((Rational) o)).getNumerator() > 0)
            return 1;
        else if ((this.subtract((Rational) o)).getNumerator() < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public int intValue() {
        // TODO Auto-generated method stub
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        // TODO Auto-generated method stub
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        // TODO Auto-generated method stub
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        // TODO Auto-generated method stub
        return numerator * 1.0 / denominator;
    }

    public static void main(String[] args) {
        //Rational rational = new Rational(300, 8);
        System.out.println(Rational.geneter(3000,800));
    }
}
