package com.labforward.task.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.labforward.task.model.Note;
import com.labforward.task.model.NotesResponse;

/**
 * 
 * @author Purushotham Yanamala
 *
 */
public interface INotesService {

  Note saveNotes(Note notes);
  
  void deleteNotes();
  
  List<Note> getNotes() throws IOException;
  
  NotesResponse searchNotes(String searchStr) throws IOException;
}
