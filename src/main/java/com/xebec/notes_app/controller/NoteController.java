package com.xebec.notes_app.controller;

import com.xebec.notes_app.dto.NoteDto;
import com.xebec.notes_app.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @GetMapping("/notes")
    public String notes(Model model) {
        Long userId = 1L;
        List<NoteDto> notes = noteService.getAllNotesByUserId(userId);
        model.addAttribute("username", "glenn");
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/add-note")
    public String showAddNotePage(Model model) {
        model.addAttribute("note", new NoteDto());
        return "add-note";
    }

    @PostMapping("/notes/add")
    public String addNote(@ModelAttribute NoteDto noteDto) {
        Long userId = 1L;
        noteService.addNote(noteDto, 1L);
        return "redirect:/notes";
    }
}
