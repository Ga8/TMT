package com.take.my.time.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "opportunity")
@JsonIgnoreProperties(value = {"id"})
public class Opportunity implements Comparable<Opportunity> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private String opportunityDate;

  @OneToMany(cascade = CascadeType.ALL)
  private List<UserOpportunityHour> userHour;

  @Column(name = "color")
  private String state;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_opportunity", joinColumns = {
      @JoinColumn(name = "opportunity_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private Set<User> users;

  private String label;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOpportunityDate() {
    return opportunityDate;
  }

  public void setOpportunityDate(String opportunityDate) {
    this.opportunityDate = opportunityDate;
  }

  public String getState() {
    return state;
  }

  public Set<User> getUsers() {
    if (users == null) {
      users = new HashSet<User>();
    }
    return users;
  }

  public void setState(String state) {
    this.state = state;

  }

  public List<UserOpportunityHour> getUserHour() {
    if (userHour == null) {
      userHour = new ArrayList<UserOpportunityHour>();
    }

    return userHour;
  }

  public void setUserHour(List<UserOpportunityHour> userHour) {
    this.userHour = userHour;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }



  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String computeLabel() {

    String labellv = "";

    for (UserOpportunityHour hourDetails : userHour) {

      if (hourDetails.getHour() != null && hourDetails.getUser().getName() != null) {
        labellv =
            labellv + hourDetails.getUser().getName() + " - " + hourDetails.getHour() + " /\n ";
      }
    }
    label = labellv;

    return label;

  }

  @Override
  public int compareTo(Opportunity o) {
    Integer result = -1;
    if (((Opportunity) o).getOpportunityDate().equals(this.opportunityDate)) {
      result = 0;
    }
    return result;

  }


}
