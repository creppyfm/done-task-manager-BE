package com.creppyfm.donetaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    private String projectId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime created;
    private LocalDateTime updated;


    //manual constructor for associating 'Task' with 'Project'
    public Task(String projectId, String title, String description, String status, LocalDateTime created, LocalDateTime updated) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }
    // Getters and setters
    public String getId() {
        return id;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return created;
    }

    public void setCreationDate(LocalDateTime created) {
        this.created = created;
    }
    public LocalDateTime getUpdatedDate() {
        return updated;
    }

    public void setUpdatedDate(LocalDateTime updated) {
        this.updated = updated;
    }
}
