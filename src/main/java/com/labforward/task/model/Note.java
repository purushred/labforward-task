package com.labforward.task.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.labforward.task.constants.AppConstants;

import lombok.Data;

/**
 * 
 * @author Purushotham Yanamala
 *
 */
@Document(indexName = AppConstants.INDEX_NAME)
@Data
public class Note {

  @Id
  private String id;
  
  private Date time;
  
  private String data;
}
