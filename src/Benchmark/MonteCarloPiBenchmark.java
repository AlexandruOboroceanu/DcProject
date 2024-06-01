package Benchmark;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MonteCarloPiBenchmark implements IBenchmark {

    private int numPoints;

    @Override
    public void initialize(Object... params) {
        this.numPoints = (int) params[0];
    }

    @Override
    public void run() {
        monteCarloPi(numPoints);
    }

    @Override
    public void run(Object... params) {
        this.numPoints = (int) params[0];
        monteCarloPi(numPoints);
    }

    private double monteCarloPi(int numPoints) {
        Random random = new Random();
        int pointsInsideCircle = 0;

        for (int i = 0; i < numPoints; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }

        return 4.0 * pointsInsideCircle / numPoints;
    }

    @Override
    public void clean() {
        // No cleanup needed for this benchmark
    }

    @Override
    public void cancel() {
        // Not implemented
    }

    public void warmUp() {
        for (int i = 0; i < 5; i++) {
            monteCarloPi(100000); // Warm-up with a smaller number of points
        }
    }
}

