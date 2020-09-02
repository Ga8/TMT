package com.take.my.time.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.take.my.time.model.Meeting;
import com.take.my.time.model.Opportunity;
import com.take.my.time.model.User;
import com.take.my.time.model.UserOpportunityHour;
import com.take.my.time.model.DTO.OpportunityDTOInput;
import com.take.my.time.model.DTO.OpportunityDTOOutput;
import com.take.my.time.services.OpportunityService;

@Component
public class OpportunityServiceImpl implements OpportunityService {

  private final static String newLine = System.lineSeparator();



  @Override
  public void updateMeetingOpportunity(Meeting oldMeeting, List<OpportunityDTOInput> opportunities,
      User user) {

    Map<String, Opportunity> opportunityByDate = oldMeeting.getOpportunities().stream()
        .collect(Collectors.toMap(Opportunity::getOpportunityDate, op -> op));

    for (OpportunityDTOInput opInput : opportunities) {

      UserOpportunityHour hourOp = new UserOpportunityHour();
      // si une heure renseigné

      hourOp = new UserOpportunityHour(user, opInput.getHour());



      // si existant
      if (opportunityByDate.get(opInput.getDate()) != null) {
        Opportunity opportunityToUpdate = opportunityByDate.get(opInput.getDate());
        opportunityToUpdate.getUsers().add(hourOp);

        oldMeeting.getOpportunities().add(opportunityToUpdate);

      }

      // sinon il faut créé
      else {
        Opportunity newOp = new Opportunity();

        newOp.setOpportunityDate(opInput.getDate());
        newOp.getUsers().add(hourOp);

        oldMeeting.getOpportunities().add(newOp);

      }
    }

  }


  @Override
  public List<OpportunityDTOOutput> mapOpportunityToOpportunityDTO(Set<Opportunity> opportunities)
      throws Exception {


    List<OpportunityDTOOutput> opsReturn = new ArrayList<OpportunityDTOOutput>();

    for (Opportunity op : opportunities) {

      OpportunityDTOOutput opDTO = new OpportunityDTOOutput();
      opDTO.setColor(op.getState().toLowerCase());
      opDTO.setDate(op.getOpportunityDate());

      if (op.getUsers() != null) {
        String label = "";
        for (UserOpportunityHour userOpHour : op.getUsers()) {

          if (userOpHour.getHour() != null) {
            label = label + userOpHour.getUser().getName() + " - " + userOpHour.getHour() + " | ";
          } else {
            label = label + userOpHour.getUser().getName() + " | ";
          }
        }

        opDTO.setLabel(label.trim());
      }

      opsReturn.add(opDTO);
    }

    return opsReturn;
  }



}
