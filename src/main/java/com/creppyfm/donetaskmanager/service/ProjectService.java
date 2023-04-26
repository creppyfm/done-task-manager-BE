package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.model.Task;
import com.creppyfm.donetaskmanager.model.User;
import com.creppyfm.donetaskmanager.repository.ProjectRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project findProjectById(String id) {
        return projectRepository.findProjectById(id);
    }

    public Project createProject(String userId, String title, String description, String status) {
        Project project = projectRepository.insert(new Project(userId, title, description, status, LocalDateTime.now(), LocalDateTime.now()));
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("projects").value(project))
                .first();
        return project;
    }
}
