package com.AndroidAPI.service;

import com.AndroidAPI.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTask();
    Task getTaskById(Integer id);
    Task createTask(Task task);
    Task updateTask(Integer id, Task newTask);
    boolean deleteTask(Integer id);
    List<Task> addAll(List<Task> tasks);
}
