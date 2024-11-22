package com.example.gestion_proyectos.Repositories;

import com.example.gestion_proyectos.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
