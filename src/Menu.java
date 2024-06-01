import Benchmark.DummyBenchmark;
import Benchmark.IBenchmark;
import Benchmark.MonteCarloPiBenchmark;
import Logging.ConsoleLogger;
import Logging.FileLogger;
import Logging.ILogger;
import Timing.ITimer;
import Timing.Timer;

import java.io.IOException;
import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

import static Benchmark.RecursivePrimeGenerator.generatePrimesAndDisplayResults;

public class Menu {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice;
            boolean exit = false;

            do {
                System.out.println("Benchmark Menu:");
                System.out.println("1. Single Benchmark");
                System.out.println("2. Multiple Benchmarks");
                System.out.println("3. Benchmark with File Logging");
                System.out.println("4. Benchmark with Timer Pause and Resume");
                System.out.println("5. Run Benchmark");
                System.out.println("6. Test Timer with Main Thread Interruption");
                System.out.println("7. Run Benchmark with Time Conversion");
                System.out.println("8. Run Benchmark with MonteCarlo");
                System.out.println("9. Generate primes");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        singleBenchmark();
                        break;
                    case 2:
                        multipleBenchmarks();
                        break;
                    case 3:
                        benchmarkWithFileLogging();
                        break;
                    case 4:
                        benchmarkWithTimerPauseAndResume();
                        break;
                    case 5:
                        runBenchmark();
                        break;
                    case 6:
                        testTimerWithInterruption();
                        break;
                    case 7:
                        runBenchmarkWithTimeConversion();
                        break;
                    case 8:
                        runMonteCarloPiBenchmark();
                        break;
                    case 9:
                        generatePrimesAndDisplayResults();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (!exit);

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void singleBenchmark() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new ConsoleLogger();
            ITimer timer = new Timer();

            benchmark.initialize(10000);
            timer.start();
            benchmark.run();
            timer.stop();
            logger.write("Benchmark execution time: " + timer.getElapsedTimeMillis() + " milliseconds");
            benchmark.clean();
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void multipleBenchmarks() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new ConsoleLogger();
            ITimer timer = new Timer();

            int numRuns = 5;
            long totalExecutionTime = 0;

            for (int i = 0; i < numRuns; i++) {
                benchmark.initialize(10000);
                timer.start();
                benchmark.run();
                timer.stop();
                logger.write("Run " + (i + 1) + ": Benchmark execution time: " + timer.getElapsedTimeMillis() + " milliseconds");
                totalExecutionTime += timer.getElapsedTimeMillis();
                benchmark.clean();
            }

            logger.write("Average execution time over " + numRuns + " runs: " + (totalExecutionTime / numRuns) + " milliseconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void benchmarkWithFileLogging() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new FileLogger("benchmark_log.txt");
            ITimer timer = new Timer();

            benchmark.initialize(10000);
            timer.start();
            benchmark.run();
            timer.stop();
            logger.write("Benchmark execution time: " + timer.getElapsedTimeMillis() + " milliseconds");
            benchmark.clean();
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void benchmarkWithTimerPauseAndResume() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new ConsoleLogger();
            ITimer timer = new Timer();

            benchmark.initialize(10000);
            timer.start();
            benchmark.run();
            timer.pause();
            Thread.sleep(1000); // Simulate some pause time
            timer.resume();
            benchmark.run();
            timer.stop();
            logger.write("Total benchmark execution time: " + timer.getElapsedTimeMillis() + " milliseconds");
            benchmark.clean();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new ConsoleLogger();
            ITimer timer = new Timer();

            benchmark.initialize(10000);
            timer.start();
            benchmark.run();
            timer.stop();
            logger.write("Benchmark execution time: " + timer.getElapsedTimeMillis() + " milliseconds");
            benchmark.clean();
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testTimerWithInterruption() {
        try {
            ITimer timer = new Timer();

            // Start the timer
            timer.start();

            // Simulate some time-consuming task
            for (int i = 0; i < 10; i++) {
                System.out.println("Task in progress...");
                Thread.sleep(1000); // Simulate 1 second delay
            }

            // Interrupt the main thread
            Thread.currentThread().interrupt();

            // Stop the timer
            timer.stop();

            // Print the elapsed time
            System.out.println("Elapsed time: " + timer.getElapsedTimeMillis() + " milliseconds");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
        }
    }

    private static void runBenchmarkWithTimeConversion() {
        try {
            IBenchmark benchmark = new DummyBenchmark();
            ILogger logger = new ConsoleLogger();
            ITimer timer = new Timer();

            benchmark.initialize(10000);
            timer.start();
            benchmark.run();
            timer.stop();
            logger.writeTime(timer.getElapsedTime(), Timer.TimeUnit.SECONDS); // USER CNAT DIRECTLY INTERACT WITH THIS BUT THE DISPLAY CAN BE CHANGED BY CHANGING THE SECONDS
            benchmark.clean();
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runMonteCarloPiBenchmark() {
        try {
            MonteCarloPiBenchmark benchmark = new MonteCarloPiBenchmark();
            ILogger logger = new FileLogger("montecarloresults.txt");
            ITimer timer = new Timer();

            int[] testValues = {50, 100, 1000, 10000, 50000, 100000}; // Add more values as needed

            // Warm-up phase
            benchmark.warmUp();

            // Measure and log runtimes
            for (int n : testValues) {
                benchmark.initialize(n);
                timer.start();
                benchmark.run();
                timer.stop();
                long elapsedTime = timer.getElapsedTime();
                logger.write("n = " + n + ": " + elapsedTime + " nanoseconds");
                System.out.println("n = " + n + ": " + elapsedTime + " nanoseconds");
            }

            logger.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

