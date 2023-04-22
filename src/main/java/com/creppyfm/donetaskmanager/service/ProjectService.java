package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.model.Task;
import com.creppyfm.donetaskmanager.repository.ProjectRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project findProjectById(String projectId) {
        return projectRepository.findProjectByProjectId(projectId);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
