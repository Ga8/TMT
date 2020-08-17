package com.take.my.time.model.DTO;

import java.io.Serializable;
import java.util.List;

public class ParticipationDTO implements Serializable {

  private static final long serialVersionUID = 1225164282883770733L;

  private String guid;

  private String name;

  private List<OpportunityDTOInput> opportunities;


  public ParticipationDTO() {
    super();
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public List<OpportunityDTOInput> getOpportunities() {
    return opportunities;
  }

  public void setOpportunities(List<OpportunityDTOInput> opportunities) {
    this.opportunities = opportunities;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



}
