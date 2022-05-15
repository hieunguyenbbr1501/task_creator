package com.example.demo.service;

import com.example.demo.cache.TaskCache;
import com.example.demo.dto.TaskData;
import com.example.demo.exception.InternalException;
import com.example.demo.exception.TaskLimitExceedException;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskCache taskCache;

    @Override
    public void saveTask(TaskData taskData, int userId) throws TaskLimitExceedException, InternalException {
        User user = userRepository.findById(userId);
        boolean canAddTask = taskCache.validateMaximum(userId, user.getTaskLimit());
        if (!canAddTask) {
            throw new TaskLimitExceedException();
        }
        try {
            Task toSave = new Task();
            toSave.setName(taskData.getName());
            toSave.setDescription(taskData.getDescription());
            toSave.setCreated_at(new Date());
            toSave.setUserId(userId);
            taskRepository.save(toSave);
            taskCache.increaseTaskCount(userId);
        } catch (Exception e) {
            throw new InternalException();
        }


    }

    @Override
    public List<Task> getTaskByUser(int userId) {
        return null;
    }
}
