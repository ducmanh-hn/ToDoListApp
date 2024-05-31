package com.AndroidAPI.controller;

import com.AndroidAPI.entity.Task;
import com.AndroidAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public Task getUserById(@PathVariable Integer id){
        return taskService.getTaskById(id);
    }

    @PostMapping("")
    public Task createUser(@RequestBody Task task){
        return taskService.createTask(task);
    }
    @PostMapping("/post")
    public Task createUserParams(@RequestParam(name = "taskContent")String taskContent,
                                 @RequestParam(name = "status")Boolean status){
        Task newTask = new Task(taskContent,status);
        return taskService.createTask(newTask);
    }

    @PostMapping("/{id}")
    public Task updateUser(@PathVariable Integer id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return taskService.deleteTask(id);
    }
    @PostMapping("/updateAll")
    public String getUpdatedTasks(@RequestBody List<Task> tasks){
        taskService.addAll(tasks);
        return " Ok";
    }
}
