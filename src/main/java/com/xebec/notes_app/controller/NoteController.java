package com.xebec.notes_app.controller;

import com.xebec.notes_app.dto.NoteDto;
import com.xebec.notes_app.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @GetMapping("/notes")
    public String notes(Model model) {
        List<NoteDto> notes = noteService.getAllNotesByUserId(1L);
        model.addAttribute("username", "glenn");
        model.addAttribute("notes", notes);
        return "notes";
    }
}
