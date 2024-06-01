package Logging;

import Timing.Timer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {
    private BufferedWriter writer;

    public FileLogger(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }

    @Override
    public void write(long value) {
        try {
            writer.write(String.valueOf(value));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String value) {
        try {
            writer.write(value);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Object... values) {
        try {
            for (Object value : values) {
                writer.write(value + " ");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeTime(long nanoseconds, Timer.TimeUnit unit) {
        Timer timer = new Timer();
        double convertedTime = timer.convertTime(nanoseconds, unit);
        String unitLabel = unit.toString().toLowerCase();
        try {
            writer.write(String.format("Elapsed time: %.3f %s", convertedTime, unitLabel));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


