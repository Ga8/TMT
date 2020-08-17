package com.take.my.time.services;

import com.take.my.time.model.Meeting;
import com.take.my.time.model.OpportunityStateEnum;

public interface StateService {

  void computeOpportunityState(Meeting meeting);

  OpportunityStateEnum getOpportunityState(Double ratio);

}
