package com.example.crud.controller;

import com.example.crud.model.Student;
import com.example.crud.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // LIST - barcha talabalarni ko'rsatish
    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.findAll());
        return "students/list";
    }

    // CREATE FORM - yangi talaba qo'shish formasi
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("formTitle", "Yangi Talaba Qo'shish");
        return "students/form";
    }

    // CREATE SAVE - yangi talabani saqlash
    @PostMapping
    public String create(@Valid @ModelAttribute Student student,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Yangi Talaba Qo'shish");
            return "students/form";
        }
        service.save(student);
        return "redirect:/students";
    }

    // EDIT FORM - talabani tahrirlash formasi
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.findById(id));
        model.addAttribute("formTitle", "Talabani Tahrirlash");
        return "students/form";
    }

    // UPDATE - talabani yangilash
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Student student,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Talabani Tahrirlash");
            return "students/form";
        }
        student.setId(id);
        service.save(student);
        return "redirect:/students";
    }

    // DELETE - talabani o'chirish
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/students";
    }
}
