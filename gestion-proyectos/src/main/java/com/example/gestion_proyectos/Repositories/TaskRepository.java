package com.example.gestion_proyectos.Repositories;

import com.example.gestion_proyectos.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteByProjectId(Long projectId);
}
