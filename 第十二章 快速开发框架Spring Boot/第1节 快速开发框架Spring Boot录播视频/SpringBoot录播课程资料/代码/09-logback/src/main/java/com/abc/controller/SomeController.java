package com.abc.controller;

import com.abc.bean.Student;
import com.abc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/some")
public class SomeController {

    @Autowired
    private StudentService service;

    @PostMapping("/register")
    public String registerHandler(Student student, Model model) throws Exception {
        model.addAttribute("student", student);
        service.addStudent(student);
        return "/jsp/welcome.jsp";
    }

}
