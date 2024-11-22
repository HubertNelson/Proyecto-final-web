package com.example.gestion_proyectos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_proyectos.Services.ProjectService;
import com.example.gestion_proyectos.models.Project;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/view/{id}")
    public String viewProject(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "view_project";
    }

    @GetMapping("/edit/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "edit_project";
    }

    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project project) {
        Project existingProject = projectService.findById(id);
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        projectService.save(existingProject);
        return "redirect:/project/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create_project";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/dashboard";
    }
}

