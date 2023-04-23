package com.creppyfm.donetaskmanager.repository;

import com.creppyfm.donetaskmanager.model.Project;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findProjectById(String id);

}
