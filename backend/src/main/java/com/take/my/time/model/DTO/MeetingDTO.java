package com.take.my.time.model.DTO;

import java.util.ArrayList;
import java.util.List;

import com.take.my.time.model.User;

public class MeetingDTO {

  private String guid;

  private String title;

  private User author;

  private List<OpportunityDTOInput> opportunities;

  public MeetingDTO() {

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

  public List<OpportunityDTOInput> getOpportunities() {
    if (opportunities == null) {
      opportunities = new ArrayList<>();
    }
    return opportunities;
  }

  public void setOpportunities(List<OpportunityDTOInput> opportunities) {
    this.opportunities = opportunities;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User name) {
    this.author = name;
  }


}
