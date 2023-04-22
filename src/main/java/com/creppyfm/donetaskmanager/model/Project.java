package com.creppyfm.donetaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "Project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private ObjectId id;
    private String projectId;
    private String userId;
    private String title;
    private String description;
    private String phase; //ex: yet to begin, working on it, finished
    @DocumentReference
    private List<Task> taskList;

    public String getId() {
        return projectId;
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
}
