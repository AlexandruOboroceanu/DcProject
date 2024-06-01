package Timing;

public interface ITimer {
    void start();
    void stop();
    void pause();
    void resume();
    long getElapsedTime();
    long getElapsedTimeMillis();

}
