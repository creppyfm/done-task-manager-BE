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
        String projectId = task.getProjectId();
        String title = task.getTitle();
        String description = task.getDescription();
        String status = task.getStatus();

        return new ResponseEntity<Task>(taskService.
                createTask(projectId, title, description, status), HttpStatus.OK);
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
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}") //must delete from associated 'Project' as well
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
