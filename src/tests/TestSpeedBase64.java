package tests;

import com.captainredbeard.xor.Base64;

/**
 * @author captain-redbeard
 * @version 1.00
 * @since 3/01/17
 */
public class TestSpeedBase64 {

    public static void main(String[] args) {
        final String encodeString = "Hello World!";
        final String decodeString = "SGVsbG8gV29ybGQh";
        final int tests = 10000;

        speedTester(tests, tests, encodeString, true);
        speedTester(tests, tests, decodeString, false);
    }

    public static void speedTester(int warmups, int tests, String testString, boolean encode) {
        long total = 0;
        long average;

        System.out.println("Warming up.");

        //Warm up
        for (int i = 0; i < warmups; i++) {
            speedTest(tests, testString, encode);
        }

        System.out.println("Warm up complete.");
        System.out.println("Starting real tests.");

        //Actual test
        for (int i = 0; i < tests; i++) {
            total += speedTest(tests, testString, encode);
        }

        //Average
        average = total / tests;

        //Echo results
        System.out.println();
        System.out.println("-- Speed Test --");
        System.out.println("Total Tests: " + (tests * tests));
        System.out.println(" - Averaged tests");
        System.out.println(" \tTests: \t\t\t" + tests);
        System.out.println(" \tAverage time: \t" + (average / 1000000.0) + "ms");
        System.out.println(" \tString length: \t" + testString.length());
        System.out.println();
    }

    public static long speedTest(int tests, String testString, boolean encode) {
        long start, end;
        long total = 0;

        for (int i = 0; i < tests; i++) {
            start = System.nanoTime();

            //Perform the operation
            if (encode) {
                Base64.encode(testString.getBytes());
            } else {
                Base64.decode(testString.getBytes());
            }

            end = System.nanoTime() - start;

            //Add to total
            total += end;
        }

        return total;
    }

}
