package sample;

public class Log {
    private int statusCode;
    private String threadName;
    private double timeTakenInSeconds;
    private boolean hasError;

    public Log(int statusCode, String threadName, double timeTakenInSeconds, boolean hasError) {
        this.statusCode = statusCode;
        this.threadName = threadName;
        this.timeTakenInSeconds = timeTakenInSeconds;
        this.hasError = hasError;

    }

    public boolean hasError() {
        return this.hasError;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getThreadName() {
        return threadName;
    }

    public double getTimeTakenInSeconds() {
        return timeTakenInSeconds;
    }

    @Override
    public String toString() {
        String specifiers = "%-30s %-30s %-5.2f%n";
        return String.format(specifiers, threadName, statusCode, timeTakenInSeconds);
    }
}
