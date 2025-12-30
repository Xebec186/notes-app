package com.xebec.notes_app.controller;

import com.xebec.notes_app.dto.NoteDto;
import com.xebec.notes_app.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @ModelAttribute("username")
    public String username(@AuthenticationPrincipal(expression = "username") String username) {
        return username;
    }

    @GetMapping("/notes")
    public String showNotesPage(@AuthenticationPrincipal(expression = "username") String username, Model model) {
        List<NoteDto> notes = noteService.getNotesForUser(username);
        model.addAttribute("notes", notes);
        return "notes";
    }

    @GetMapping("/notes/{id}")
    public String showNotePage(@PathVariable Long id, Model model) {
        NoteDto noteDto = noteService.getNoteById(id);
        model.addAttribute("note", noteDto);
        return "note";
    }

    @GetMapping("/notes/add")
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

    @GetMapping("/notes/edit/{id}")
    public String showEditNotePage(@PathVariable Long id, Model model) {
        NoteDto noteDto = noteService.getNoteById(id);
        model.addAttribute("note", noteDto);
        return "edit-note";
    }

    @PostMapping("/notes/edit/{id}")
    public String editNote(@PathVariable Long id, @ModelAttribute NoteDto noteDto) {
        noteService.updateNote(id, noteDto);
        return "redirect:/notes/" + id;
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}
