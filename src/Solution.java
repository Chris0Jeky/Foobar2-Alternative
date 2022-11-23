public class Solution {
    // code for solving Java's limitation when it comes to fractions(compared to Python):
    public static int[] minFract(double decimal) {
        final int numerator;
        final int denominator;
        double tolerance = 1.0E-6;

        int minNumerator = (int) Math.floor(decimal);
        int minDenominator = 1;

        int maxNumerator = (int) Math.ceil(decimal);
        int maxDenominator = 1;

        double aproxDecimal;
        int aproxNum;
        int aproxDen;

        do {
            aproxNum = minNumerator + maxNumerator;
            aproxDen = minDenominator + maxDenominator;

            aproxDecimal = (double) aproxNum / aproxDen;

            if (aproxDecimal > decimal) {
                maxNumerator = aproxNum;
                maxDenominator = aproxDen;
            } else {
                minNumerator = aproxNum;
                minDenominator = aproxDen;
            }

        } while (Math.abs(aproxDecimal - decimal) > tolerance);

        int greatComDiv = greatComDivEqu(aproxNum, aproxDen);

        numerator = aproxNum / greatComDiv;
        denominator = aproxDen / greatComDiv;
        return new int[]{numerator, denominator};
    }

    public static int greatComDivEqu(int number1, int number2) {

        if (number2 == 0) {
            return number1;
        }
        return greatComDivEqu(number2, number1 % number2);
    }
    public final static int[] invalidCombination = new int[]{-1,-1};
    private final static int minRadius = 1;

    public static int[] solution(int[] pegs) {

        double firstGearRadius = equationResFirstG(pegs);

        if (!isGood(firstGearRadius, pegs)) {
            return invalidCombination;
        }

        // code for solving Java's limitation when it comes to fractions(compared to Python) END
        // return Simplest Fraction
        return new int[]{minFract(firstGearRadius)[0], minFract(firstGearRadius)[1]};
    }

    private static boolean isGood(double firstGearRadius, int[] pegs) {

        double gearRad = firstGearRadius;
        for (int i = 1; i < pegs.length; i++) {

            double distance = pegs[i] - pegs[i - 1];
            gearRad = distance - gearRad;
            if (gearRad < minRadius) {
                return false;
            }
        }
        return true;
    }

    private static double equationResFirstG(int[] pegs) {

        double evenIndexOddNeg = 0;
        for (int i = 0; i < pegs.length - 1 ; i++) {

            double distance = pegs[i + 1] - pegs[i];
            if (isEven(i)) {
                evenIndexOddNeg = evenIndexOddNeg + distance;
            } else {
                evenIndexOddNeg = evenIndexOddNeg - distance;

            }
        }
        if (isEven(pegs.length)) {
            return evenIndexOddNeg * 2d/3d;
        } else {
            return evenIndexOddNeg * 2;
        }

    }

        private static boolean isEven(int number) {
         return number % 2 == 0;
        }

    }