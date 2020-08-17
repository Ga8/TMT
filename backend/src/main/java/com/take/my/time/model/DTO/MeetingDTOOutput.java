package com.take.my.time.model.DTO;

import java.io.Serializable;
import java.util.List;

public class MeetingDTOOutput implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 5736562968814494724L;

  private String guid;

  private String title;



  private String author;

  private List<OpportunityDTOOutput> opportunitiesOutput;



  public MeetingDTOOutput() {
    super();
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public List<OpportunityDTOOutput> getOpportunitiesOutput() {
    return opportunitiesOutput;
  }

  public void setOpportunitiesOutput(List<OpportunityDTOOutput> opportunitiesOutput) {
    this.opportunitiesOutput = opportunitiesOutput;
  }



}
