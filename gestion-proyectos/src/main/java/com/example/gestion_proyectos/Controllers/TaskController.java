package com.example.gestion_proyectos.Controllers;

import com.example.gestion_proyectos.models.Project;
import com.example.gestion_proyectos.models.Task;
import com.example.gestion_proyectos.Services.ProjectService;
import com.example.gestion_proyectos.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "edit_task";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, @RequestParam("dueDate") String dueDateString) {
        Task existingTask = taskService.findById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());

        // Convertir la fecha de cadena a Date
        try {
            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
            existingTask.setDueDate(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        existingTask.setCompleted(task.getCompleted());
        taskService.save(existingTask);
        return "redirect:/project/view/" + existingTask.getProject().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        Long projectId = task.getProject().getId();
        taskService.deleteById(id);
        return "redirect:/project/view/" + projectId;
    }

    @GetMapping("/create/{projectId}")
    public String createTaskForm(@PathVariable Long projectId, Model model) {
        Task task = new Task();
        Project project = projectService.findById(projectId);
        task.setProject(project);
        model.addAttribute("task", task);
        return "create_task";
    }

    @PostMapping("/create/{projectId}")
    public String createTask(@PathVariable Long projectId, @ModelAttribute Task task, @RequestParam("dueDate") String dueDateString) {
        Project project = projectService.findById(projectId);
        task.setProject(project);

        // Convertir la fecha de cadena a Date
        try {
            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
            task.setDueDate(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskService.save(task);
        return "redirect:/project/view/" + projectId;
    }
}
