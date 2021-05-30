package com.labforward.task.model;

import lombok.Data;

/**
 * 
 * @author Purushotham Yanamala
 *
 */
@Data
public class NotesResponse {

  private int occurrences;
  
  private String[] similarWords;

}
