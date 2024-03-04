package edu.javaLessons.City;

public class CheckPersonException extends Exception{

    public CheckPersonException() {
    }

    public CheckPersonException(String message) {
        super(message);
    }

    public CheckPersonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckPersonException(Throwable cause) {
        super(cause);
    }

    public CheckPersonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
