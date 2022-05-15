package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.TaskData;
import com.example.demo.exception.InternalException;
import com.example.demo.exception.TaskLimitExceedException;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/task")
    public List<Task> getTasks(@RequestHeader("Authorization") String jwtToken) {
        int userIdFromJWT = tokenProvider.getUserIdFromJWT(jwtToken);
        return taskService.getTaskByUser(userIdFromJWT);
    }

    @PostMapping(value = "/task/add")
    public ResponseEntity addTask(@RequestHeader("Authorization") String jwtToken, @RequestBody TaskData taskData) {
        int userIdFromJWT = tokenProvider.getUserIdFromJWT(jwtToken);
        ResponseEntity responseEntity = null;
        try {
            taskService.saveTask(taskData, userIdFromJWT);
            responseEntity = ResponseEntity.ok("Task saved successfully!");
        } catch (TaskLimitExceedException e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        } catch (InternalException e) {
            responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
        }
        return responseEntity;
    }
}
