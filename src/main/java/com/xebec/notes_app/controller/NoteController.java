package com.xebec.notes_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {

    @GetMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("name", "Glenn");
        return "notes";
    }
}
