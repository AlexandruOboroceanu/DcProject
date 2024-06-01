package Benchmark;

/*THERE IS A ISSUE THE GENERATION DOESN'T WORK*/

public class RecursivePrimeGenerator {

    public static void generatePrimesAndDisplayResults() {
        long startTime = System.currentTimeMillis();
        int lastPrime = 0;

        try {
            lastPrime = generatePrimes(2, 1000000); // Start from 2 and generate primes up to a large number
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow Exception occurred.");
        }

        long endTime = System.currentTimeMillis();
        long runtime = endTime - startTime;

        // Calculate custom score
        double customScore = calculateCustomScore(lastPrime, runtime);

        System.out.println("Last prime reached before crashing: " + lastPrime);
        System.out.println("Runtime: " + runtime + " milliseconds");
        System.out.println("Custom Score: " + customScore);
    }

    private static int generatePrimes(int current, int max) {
        int lastPrime = 0;
        if (isPrime(current)) {
            lastPrime = current;
            lastPrime = Math.max(lastPrime, generatePrimes(current + 1, max)); // Recursive call
        } else {
            if (current < max) {
                lastPrime = Math.max(lastPrime, generatePrimes(current + 1, max)); // Recursive call
            }
        }
        return lastPrime;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static double calculateCustomScore(int lastPrime, long runtime) {
        // Example custom score formula: number of primes generated divided by runtime
        double score = (double) lastPrime / runtime;
        return score;
    }
}




