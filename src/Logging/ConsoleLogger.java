package Logging;

import Timing.Timer;

public class ConsoleLogger implements ILogger {
    @Override
    public void write(long value) {
        System.out.println(value);
    }

    @Override
    public void write(String value) {
        System.out.println(value);
    }

    @Override
    public void write(Object... values) {
        for (Object value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    @Override
    public void close() {
        // No resources to close for ConsoleLogger
    }

    @Override
    public void writeTime(long nanoseconds, Timer.TimeUnit unit) {
        Timer timer = new Timer();
        double convertedTime = timer.convertTime(nanoseconds, unit);
        String unitLabel = unit.toString().toLowerCase();
        System.out.printf("Elapsed time: %.3f %s%n", convertedTime, unitLabel);
    }
}


