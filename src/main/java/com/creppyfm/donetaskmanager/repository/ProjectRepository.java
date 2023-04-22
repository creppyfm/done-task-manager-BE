package com.creppyfm.donetaskmanager.repository;

import com.creppyfm.donetaskmanager.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findProjectByProjectId(String projectId);

}
