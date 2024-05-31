package com.AndroidAPI.service.impl;

import com.AndroidAPI.entity.Task;
import com.AndroidAPI.repository.TaskRepo;
import com.AndroidAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Override
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Integer id, Task newTask) {
        newTask.setId(id);
        return taskRepo.save(newTask);
    }

    @Override
    public boolean deleteTask(Integer id) {
        if(taskRepo.existsById(id)){
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Task> addAll(List<Task> tasks) {
        taskRepo.deleteAll();
        taskRepo.flush();
        return taskRepo.saveAll(tasks);
    }
}
