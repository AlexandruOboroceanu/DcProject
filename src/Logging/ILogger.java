package Logging;

import Timing.Timer;

public interface ILogger {
    void write(long value);
    void write(String values);
    void write(Object... values);
    void close();
    void writeTime(long nanoseconds, Timer.TimeUnit unit);

}
