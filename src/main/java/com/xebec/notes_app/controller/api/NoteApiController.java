package com.xebec.notes_app.controller.api;

import com.xebec.notes_app.dto.NoteDto;
import com.xebec.notes_app.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@AllArgsConstructor
public class NoteApiController {

    private NoteService noteService;
    private static final String username = "Test user";

    @PostMapping
    public NoteDto addNote(@RequestBody NoteDto noteDto) {
        return noteService.addNote(noteDto, username);
    }

    @GetMapping("{id}")
    public NoteDto getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping
    public List<NoteDto> getAllNotesForUser() {
        return noteService.getNotesForUser(username);
    }

    @PutMapping("{id}")
    public NoteDto updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {
        return noteService.updateNote(id, noteDto);
    }

    @DeleteMapping("{id}")
    public String deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }
}
