package com.take.my.time.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_opportunity",
uniqueConstraints = { @UniqueConstraint( columnNames = { "UOH_user_id", "UOH_opportunity_id" } ) } )
@JsonIgnoreProperties(value = { "user", "hour" })
public class UserOpportunityHour implements Serializable {

	private static final long serialVersionUID = -6544878575922081356L;
	 
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="UOH_id")
	  private Long id;
	
	
	@OneToOne()
	@JoinColumn(name = "UOH_user_id")
	private User user;

	@Column(name = "hour")
	private String hour;

	public UserOpportunityHour() {
		super();
	}

	public UserOpportunityHour(String name) {
		super();
		this.user = new User(name);

	}

	public UserOpportunityHour(User pUser, String pHour) {
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



}
