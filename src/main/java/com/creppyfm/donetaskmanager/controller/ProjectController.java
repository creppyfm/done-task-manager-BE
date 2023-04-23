package com.creppyfm.donetaskmanager.controller;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.service.ProjectService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<List<Project>>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getSingleProject(@PathVariable String id) {
        return new ResponseEntity<Project>(projectService.findProjectById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }
}
