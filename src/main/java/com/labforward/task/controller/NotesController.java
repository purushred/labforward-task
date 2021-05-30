package com.labforward.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.task.model.Note;
import com.labforward.task.service.interfaces.INotesService;

/**
 * 
 * @author Purushotham Yanamala
 *
 */

@RequestMapping(value = { "/api/v1/lab-notes" })
@RestController

// Uncomment this line for local development testing.
//@CrossOrigin(origins = "http://localhost:4200")
public class NotesController {

    @Autowired
    private INotesService notesService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNotes(@RequestBody Note notes) {
        return ResponseEntity.ok(notesService.saveNotes(notes));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotes() {
        try {
            return ResponseEntity.ok(notesService.getNotes());
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotes() {
        notesService.deleteNotes();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).contentLength(0L).build();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchNotes(@RequestParam("q") String searchStr) {

        try {
            return ResponseEntity.ok(notesService.searchNotes(searchStr));
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
