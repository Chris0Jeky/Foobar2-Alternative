import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Tester {

    public static void test() {

        printOutput(Solution.solution(new int[]{4, 30, 50}));              // [12, 1] Base Test Case
        printOutput(Solution.solution(new int[]{4, 17, 50}));              // [-1, -1] Negative Test
        printOutput(Solution.solution(new int[]{1, 100}));                 // [66, 1] 2 Pegs
        printOutput(Solution.solution(new int[]{375, 3850, 7328, 8630}));  // [866, 1] Even
        printOutput(Solution.solution(new int[]{13, 130, 234, 327, 394})); // [78, 1] Odd
        printOutput(Solution.solution(new int[]{9377, 9447, 9569, 9646})); // [50, 3] Hard to make into Fraction (Radii is 16.666666..)

        //findValidTestSets(4, true, true);
    }

    private static void printOutput(int[] output) {

        System.out.println(
                Arrays.stream(output)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(",", "[", "]"))
        );
    }

    // To come up with test cases
    private static void findValidTestSets(int numberOfPegs,
                                          boolean mustBeValid,
                                          boolean mustBeAHardFraction) {

        while (true) {

            int[] pegs = new int[numberOfPegs];
            pegs[0] = getRandomInteger(1, 10000);
            for (int i = 1; i < numberOfPegs; i++) {
                pegs[i] = getRandomInteger(pegs[i - 1], 10000);
            }

            int[] sol = Solution.solution(pegs);
            if (mustBeValid && sol[0] != -1) {
                if (mustBeAHardFraction && sol[1] != 1) {
                    System.out.println(Arrays.toString(pegs));
                    printOutput(sol);
                    break;
                }
            }
        }
    }

    private static int getRandomInteger(int low, int high) {
        return new Random().nextInt(high - low) + low;
    }
}
