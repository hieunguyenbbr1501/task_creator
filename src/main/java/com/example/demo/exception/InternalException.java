package com.example.demo.exception;

import lombok.Getter;

@Getter
public class InternalException extends Exception {
    private final String message = "Internal Error";
}
