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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public NoteDto addNote(NoteDto noteDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist with username: " + username));

        Note note = modelMapper.map(noteDto, Note.class);
        note.setUser(user);

        Note addedNote = noteRepository.save(note);
        return modelMapper.map(addedNote, NoteDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));
        return modelMapper.map(note, NoteDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteDto> getNotesForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist with username: " + username));

        return getAllNotesByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteDto> getAllNotesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));
        List<Note> notes = noteRepository.findAllNotesByUser(user);

        return notes.stream()
                .map((note) -> modelMapper.map(note, NoteDto.class))
                .toList();
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteDto noteDto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));

        note.setContent(noteDto.getContent());
        note.setTitle(noteDto.getTitle());

        Note updatedNote = noteRepository.save(note);
        return modelMapper.map(updatedNote, NoteDto.class);
    }

    @Transactional
    @Override
    public String deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note does not exist with id: " + id));

        noteRepository.delete(note);
        return "Note deleted successfully";
    }
}
