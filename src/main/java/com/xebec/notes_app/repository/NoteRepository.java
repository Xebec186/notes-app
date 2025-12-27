package com.xebec.notes_app.repository;

import com.xebec.notes_app.model.Note;
import com.xebec.notes_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findAllNotesByUser(User user);
}
