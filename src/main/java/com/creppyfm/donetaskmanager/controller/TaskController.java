package com.creppyfm.donetaskmanager.controller;

import com.creppyfm.donetaskmanager.model.Task;
import com.creppyfm.donetaskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
