package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.model.Task;
import com.creppyfm.donetaskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Task createTask(String projectId, String title, String description, String status) {
        Task task = taskRepository.insert(new Task(projectId, title, description, status, LocalDateTime.now(), LocalDateTime.now())); //insert into 'Task' collection
        mongoTemplate.update(Project.class) //insert into 'Project->taskList' array
                .matching(Criteria.where("id").is(projectId))
                .apply(new Update().push("taskList").value(task))
                .first();

        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public Task updateTask(String id, Task updatedTask) { //must update in associated 'Project' as well
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public void deleteTask(String id) { //must delete from associated 'Project' as well
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }
}
