package com.take.my.time.services;

import java.util.List;
import java.util.Set;

import com.take.my.time.model.Meeting;
import com.take.my.time.model.Opportunity;
import com.take.my.time.model.User;
import com.take.my.time.model.DTO.OpportunityDTOInput;
import com.take.my.time.model.DTO.OpportunityDTOOutput;

public interface OpportunityService {


  void updateMeetingOpportunity(Meeting oldMeeting, List<OpportunityDTOInput> opportunities,
      User user);


  List<OpportunityDTOOutput> mapOpportunityToOpportunityDTO(Set<Opportunity> opportunities)
      throws Exception;

}
