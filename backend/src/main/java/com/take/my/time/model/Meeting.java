package com.take.my.time.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "meeting")
@JsonIgnoreProperties(value = {"id"})
public class Meeting extends EntityWithUUID implements Serializable {


  private static final long serialVersionUID = 8847779259453342958L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @Column(name = "date")
  private Date meetingDate;

  private String title;


  private String author;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_meeting", joinColumns = {
      @JoinColumn(name = "meeting_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private Set<User> users;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "meeting_id")
  private Set<Opportunity> opportunities;

  @Column(name = "state")
  private String state;



  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getMeetingDate() {
    return meetingDate;
  }

  public void setMeetingDate(Date meetingDate) {
    this.meetingDate = meetingDate;
  }


  public Set<Opportunity> getOpportunities() {
    if (opportunities == null) {
      opportunities = new HashSet<>();
    }

    return opportunities;
  }

  public void setOpportunities(Set<Opportunity> opportunities) {
    this.opportunities = opportunities;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Set<User> getUsers() {
    if (users == null) {
      users = new HashSet<>();
    }

    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Meeting [id=" + id + ", meetingDate=" + meetingDate + ", title=" + title + ", users="
        + users + ", opportunities=" + opportunities + ", state=" + state + "]";
  }



}
