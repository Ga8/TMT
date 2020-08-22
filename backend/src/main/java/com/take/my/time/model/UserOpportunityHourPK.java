package com.take.my.time.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserOpportunityHourPK implements Serializable {


	private static final long serialVersionUID = 1L;

		@Basic(optional = false)
	    @Column(name = "UserOpportunityHour_user_id")
	    private Long userId;
	    
	    @Basic(optional = false)
	    @Column(name = "UserOpportunityHour_opportunity_id")
	    private Long opportunityId;
	}



