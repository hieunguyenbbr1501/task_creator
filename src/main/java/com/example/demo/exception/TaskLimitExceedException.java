package com.example.demo.exception;

import lombok.Getter;

@Getter
public class TaskLimitExceedException extends Exception {
    private String message = "Task limit exceeded!";

}
