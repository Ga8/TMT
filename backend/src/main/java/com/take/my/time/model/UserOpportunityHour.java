package com.take.my.time.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hour_user_opportunity")
@JsonIgnoreProperties(value = {"id", "user", "hour"})

public class UserOpportunityHour implements Serializable {

  private static final long serialVersionUID = -6544878575922081356L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne()
  private User user;


  @Column(name = "hour")
  private String hour;


  public UserOpportunityHour() {
    super();
  }

  public UserOpportunityHour(User pUser, String pHour) {
    super();
    this.user = pUser;
    this.hour = pHour;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getHour() {
    return hour;
  }

  public void setHour(String hour1) {
    hour = hour1;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
