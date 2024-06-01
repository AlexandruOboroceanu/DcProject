package Timing;

public class Timer implements ITimer {
    private long startTime;
    private long endTime;
    private long pausedTime;
    private boolean isPaused;

    @Override
    public void start() {
        startTime = System.nanoTime();
        isPaused = false;
    }

    @Override
    public void stop() {
        if (isPaused) {
            resume();
        }
        endTime = System.nanoTime();
    }

    @Override
    public void pause() {
        if (!isPaused) {
            pausedTime = System.nanoTime();
            isPaused = true;
        }
    }

    @Override
    public void resume() {
        if (isPaused) {
            startTime += System.nanoTime() - pausedTime;
            isPaused = false;
        }
    }

    @Override
    public long getElapsedTime() {
        if (isPaused) {
            return pausedTime - startTime;
        } else {
            return endTime - startTime;
        }
    }

    @Override
    public long getElapsedTimeMillis() {
        return getElapsedTime() / 1_000_000;
    }

    public double convertTime(long nanoseconds, TimeUnit unit) {
        switch (unit) {
            case MICROSECONDS:
                return nanoseconds / 1_000.0;
            case MILLISECONDS:
                return nanoseconds / 1_000_000.0;
            case SECONDS:
                return nanoseconds / 1_000_000_000.0;
            default:
                throw new IllegalArgumentException("Unsupported time unit");
        }
    }

    public enum TimeUnit {
        MICROSECONDS,
        MILLISECONDS,
        SECONDS
    }
}



