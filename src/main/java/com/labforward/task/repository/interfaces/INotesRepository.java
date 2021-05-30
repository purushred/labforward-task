package com.labforward.task.repository.interfaces;

import com.labforward.task.model.Note;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 
 * @author Purushotham Yanamala
 *
 */
public interface INotesRepository extends ElasticsearchRepository<Note, String> {

}
