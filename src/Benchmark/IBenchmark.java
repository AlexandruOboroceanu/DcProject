package Benchmark;

public interface IBenchmark {
    void initialize(Object... params);
    void run();
    void run(Object... params);
    void clean();
    void cancel();
}
