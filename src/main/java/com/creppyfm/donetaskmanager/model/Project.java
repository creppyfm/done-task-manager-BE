package com.creppyfm.donetaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private ObjectId id;
    private String userId;
    private String title;
    private String description;
    private String phase; //ex: yet to begin, working on it, finished
    private LocalDateTime created;
    private LocalDateTime updated;
    @DocumentReference
    private List<Task> taskList;

    //manual constructor for associating 'Project' with 'User'
    public Project(String userId, String title, String description, String phase, LocalDateTime created, LocalDateTime updated) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.phase = phase;
        this.created = created;
        this.updated = updated;
    }

    public String getId() {
        return id.toString();
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> getTaskList () {
        return this.taskList;
    }
}
