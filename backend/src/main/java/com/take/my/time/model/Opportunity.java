package com.take.my.time.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "opportunity")
@JsonIgnoreProperties(value = {"id"})
public class Opportunity implements Comparable<Opportunity> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="opportunity_id")
  private Long id;

  @Column(name = "date")
  private String opportunityDate;


  @Column(name = "color")
  private String state;

 
  @OneToMany(mappedBy = "opportunity")
  private Set<UserOpportunityHour> users;

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

//  public Set<User> getUsers() {
//    if (users == null) {
//      users = new HashSet<User>();
//    }
//    return users;
//  }

  public void setState(String state) {
    this.state = state;

  }

//  public List<UserOpportunityHour> getUserHour() {
//    if (userHour == null) {
//      userHour = new ArrayList<UserOpportunityHour>();
//    }
//
//    return userHour;
//  }

//  public void setUserHour(List<UserOpportunityHour> userHour) {
//    this.userHour = userHour;
//  }

  public void setUsers(Set<UserOpportunityHour> users) {
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

    for (UserOpportunityHour userDetails : this.users) {

      if (userDetails.getHour() != null && userDetails.getUser().getName() != null) {
        labellv =
            labellv + userDetails.getUser().getName() + " - " + userDetails.getHour() + " /\n ";
      }else {
    	  labellv = labellv + userDetails.getUser().getName();
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

public Set<UserOpportunityHour> getUsers() {
	if (users==null) {
		users = new HashSet<UserOpportunityHour>();
	}
	return users;
}

  

}
