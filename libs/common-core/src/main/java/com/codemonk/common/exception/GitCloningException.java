package com.codemonk.common.exception;

public class GitCloningException extends InternalServerException {

    public GitCloningException(String message) {
        super(message);
    }

    public GitCloningException(String message, Throwable cause) {
        super(message, cause);
    }
}
