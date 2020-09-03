package com.take.my.time.services.impl;

import org.springframework.stereotype.Component;

import com.take.my.time.model.Meeting;
import com.take.my.time.model.Opportunity;
import com.take.my.time.model.OpportunityStateEnum;
import com.take.my.time.services.StateService;

@Component
public class StateServiceImpl implements StateService {

  @Override
  public void computeOpportunityState(Meeting meeting) {

    int voterCount = meeting.getUsers().size();

    for (Opportunity op : meeting.getOpportunities()) {
      // pourcentage de votant pour cette date
      Double ratio = (Double.valueOf(op.getUsers().size()) / voterCount) * 100;
      op.setState(getOpportunityState(ratio));
    }
  }



  @Override
  public String getOpportunityState(Double ratio) {

    String state = null;

    if (ratio == 100) {

      state = OpportunityStateEnum.GREEN.getCouleurCss();

    } else if (ratio >= 75) {

      state = OpportunityStateEnum.YELLOW.getCouleurCss();

    } else if (ratio >= 50) {

      state = OpportunityStateEnum.ORANGE.getCouleurCss();

    } else if (ratio < 50) {

      state = OpportunityStateEnum.RED.getCouleurCss();
    }

    return state;
  }



}
