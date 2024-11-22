package com.example.gestion_proyectos.Services;

import com.example.gestion_proyectos.models.Project;
import com.example.gestion_proyectos.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
