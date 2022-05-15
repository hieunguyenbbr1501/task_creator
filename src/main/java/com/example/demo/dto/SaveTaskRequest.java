package com.example.demo.dto;

import lombok.Data;

@Data
public class SaveTaskRequest {
    private TaskData taskData;
    private String userId;
}
