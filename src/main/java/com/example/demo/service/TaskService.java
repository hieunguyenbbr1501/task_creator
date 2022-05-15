package com.example.demo.service;

import com.example.demo.dto.TaskData;
import com.example.demo.exception.InternalException;
import com.example.demo.exception.TaskLimitExceedException;
import com.example.demo.model.Task;

import java.util.List;

public interface TaskService {
    public void saveTask(TaskData taskData, int userId) throws TaskLimitExceedException, InternalException;

    public List<Task> getTaskByUser(int userId);
}
