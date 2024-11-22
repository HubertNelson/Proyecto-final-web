package com.example.gestion_proyectos.config;

import com.example.gestion_proyectos.models.Project;
import com.example.gestion_proyectos.models.Task;
import com.example.gestion_proyectos.models.User;
import com.example.gestion_proyectos.Repositories.ProjectRepository;
import com.example.gestion_proyectos.Repositories.TaskRepository;
import com.example.gestion_proyectos.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
public class DataLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        return args -> {
            // Crear usuario de ejemplo
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);

            // Crear proyecto de ejemplo
            Project project = new Project();
            project.setName("Proyecto Ejemplo");
            project.setDescription("Descripción del Proyecto Ejemplo");
            project.setUser(user);
            projectRepository.save(project);

            // Crear tareas de ejemplo
            Task task1 = new Task();
            task1.setTitle("Tarea 1");
            task1.setDescription("Descripción de la Tarea 1");
            task1.setDueDate(new Date());
            task1.setCompleted(false);
            task1.setProject(project);
            task1.setAssignedUser(user);
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setTitle("Tarea 2");
            task2.setDescription("Descripción de la Tarea 2");
            task2.setDueDate(new Date());
            task2.setCompleted(false);
            task2.setProject(project);
            task2.setAssignedUser(user);
            taskRepository.save(task2);
        };
    }
}
