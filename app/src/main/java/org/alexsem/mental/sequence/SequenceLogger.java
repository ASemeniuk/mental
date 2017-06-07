package org.alexsem.mental.sequence;

/**
 * Used to log sequences and results
 * Use {@link #log(int)} method to log sequences and {@link #logResult(int)} to finalize logging with result
 * Logging is only possible until result is logged. Any subsequent log calls will result in {@link java.lang.IllegalStateException}
 * Use {@link #reset()} method to clear logger and re-open it for further usage
 */

public class SequenceLogger {

    private StringBuilder data;
    private boolean isOpen = true;

    public SequenceLogger() {
        data = new StringBuilder();
    }

    /**
     * Clear logger and open it for logging
     */
    public void reset() {
        data.setLength(0);
        isOpen = true;
    }

    /**
     * Log sequence number
     * @param number Number to log
     */
    public void log(int number) {
        if (!isOpen) {
            throw new IllegalStateException("Logger is already closed");
        }
        if (data.length() == 0) {
            data.append(number).append(' ');
        } else {
            data.append(number < 0 ? '-' : '+').append(' ');
            data.append(Math.abs(number)).append(' ');
        }
    }

    /**
     * Log result of the sequence and finalize logger
     * @param result Result to log
     */
    public void logResult(int result) {
        if (!isOpen) {
            throw new IllegalStateException("Logger is already closed");
        }
        data.append("= ").append(result);
        isOpen = false;
    }

    /**
     * Return currently logged sequence
     * @return Logged data
     */
    public String print() {
        return data.toString().trim();
    }




}
