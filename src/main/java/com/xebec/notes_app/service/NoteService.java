package com.xebec.notes_app.service;

import com.xebec.notes_app.dto.NoteDto;

import java.util.List;

public interface NoteService {
    public NoteDto addNote(NoteDto note);

    public NoteDto getNoteById(Long id);

    public List<NoteDto> getAllNotesByUserId(Long userId);

    public NoteDto updateNote(Long id, NoteDto noteDto);

    public String deleteNote(Long id);
}
