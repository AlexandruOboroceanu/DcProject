package Benchmark;

import java.util.Random;

import java.util.Random;

public class DummyBenchmark implements IBenchmark {
    private int[] array;
    private boolean initialized = false;

    @Override
    public void initialize(Object... params) {
        if (params.length < 1 || !(params[0] instanceof Integer)) {
            throw new IllegalArgumentException("Invalid parameters. Expected: array size (Integer).");
        }

        int size = (Integer) params[0];
        array = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(); // Fill the array with random integers
        }

        initialized = true;
    }

    @Override
    public void run() {
        if (!initialized) {
            throw new IllegalStateException("Benchmark not initialized. Call initialize() first.");
        }
        bubbleSort(array);
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void run(Object... params) {
        run();
    }

    @Override
    public void clean() {
        // Clean up any resources if necessary
        array = null;
        initialized = false;
    }

    @Override
    public void cancel() {
        // Implement if needed
    }
}
