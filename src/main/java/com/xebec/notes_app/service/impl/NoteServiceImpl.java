package com.xebec.notes_app.service.impl;

import com.xebec.notes_app.dto.NoteDto;
import com.xebec.notes_app.exception.ResourceNotFoundException;
import com.xebec.notes_app.model.Note;
import com.xebec.notes_app.model.User;
import com.xebec.notes_app.repository.NoteRepository;
import com.xebec.notes_app.repository.UserRepository;
import com.xebec.notes_app.service.NoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public NoteDto addNote(NoteDto noteDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));

        Note note = modelMapper.map(noteDto, Note.class);
        note.setUser(user);

        Note addedNote = noteRepository.save(note);
        return modelMapper.map(addedNote, NoteDto.class);
    }

    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));
        return modelMapper.map(note, NoteDto.class);
    }

    public List<NoteDto> getAllNotesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));
        List<Note> notes = noteRepository.findAllNotesByUser(user);

        return notes.stream()
                .map((note) -> modelMapper.map(note, NoteDto.class))
                .toList();
    }

    public NoteDto updateNote(Long id, NoteDto noteDto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));

        note.setContent(noteDto.getContent());
        note.setTitle(noteDto.getTitle());

        Note updatedNote = noteRepository.save(note);
        return modelMapper.map(updatedNote, NoteDto.class);
    }

    public String deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));

        noteRepository.delete(note);
        return "Note deleted successfully";
    }
}
