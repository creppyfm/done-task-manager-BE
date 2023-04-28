package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.Project;
import com.creppyfm.donetaskmanager.model.Task;
import com.creppyfm.donetaskmanager.model.User;
import com.creppyfm.donetaskmanager.repository.ProjectRepository;
import com.creppyfm.donetaskmanager.repository.TaskRepository;
import com.creppyfm.donetaskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

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

    // TODO: incorporate task description into prompt
    public void generateTasksForProject(String id) throws IOException {
        Project project = findProjectById(id);

        String projectInfo = String.format("Title: %s\nDescription: %s\n\nList of tasks to complete the project:", project.getTitle(), project.getDescription());
        String prompt =
                "Read the project title and description included below, and generate a list " +
                "of no more than 10 steps to complete the project. Each step must be " +
                "on its own line." +
                "Here is the project information:\n"
                + projectInfo;

        // Call the OpenAIAPIManager to get the list of tasks
        OpenAIAPIManager openAIAPIManager = new OpenAIAPIManager();
        List<String> tasks;
        try {
            tasks = openAIAPIManager.buildsTaskList(prompt);
        } catch (IOException | InterruptedException e) {
            throw new IOException(e);
        }

        for (String taskTitle : tasks) {
            Task task = taskService.createTask(id, taskTitle, "", "in-progress");
            project.getTaskList().add(task);
        }

        projectRepository.save(project);
    }


    public Project updateProject(String id, Project updatedProject) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setTitle(updatedProject.getTitle());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setPhase(updatedProject.getPhase());
            existingProject.setUpdated(LocalDateTime.now());

            // Update project in the user's projects list
            User user = userRepository.findByProjectsContaining(existingProject);
            if (user != null) {
                int projectIndex = user.getProjects().indexOf(existingProject);
                if (projectIndex >= 0) { // Check if the index is within bounds
                    user.getProjects().set(projectIndex, existingProject);
                    userRepository.save(user);
                }            }

            return projectRepository.save(existingProject);
        } else {
            return null;
        }
    }

    public boolean deleteProject(String id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();

            // Remove project from the user's projects list
            User user = userRepository.findByProjectsContaining(existingProject);
            if (user != null) {
                user.getProjects().remove(existingProject);
                userRepository.save(user);
            }

            // Remove associated tasks from the Task collection
            taskRepository.deleteAll(existingProject.getTaskList());

            projectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
