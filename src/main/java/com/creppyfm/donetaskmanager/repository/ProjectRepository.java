package com.creppyfm.donetaskmanager.repository;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.model.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    Project findProjectById(String id);

    Project findByTaskListContaining(Task task);

}
