package com.creppyfm.donetaskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "SubTask")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    @Id
    private String id;
    private String taskId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime created;
    private LocalDateTime updated;
}
