package com.halo.hamso.common.exception;

import java.time.LocalTime;

public class TimeDuplicateException extends RuntimeException{
    public TimeDuplicateException(String message) {
        super(message);
    }
}
