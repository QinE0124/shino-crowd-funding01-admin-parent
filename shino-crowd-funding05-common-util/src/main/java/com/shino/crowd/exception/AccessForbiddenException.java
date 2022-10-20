package com.shino.crowd.exception;

/**
 * @author QinE
 * @create 2022-10-19 17:45
 */
public class AccessForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 10404L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }
}
