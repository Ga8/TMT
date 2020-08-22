package com.take.my.time.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.take.my.time.exceptions.IncorrectMeetingException;
import com.take.my.time.exceptions.UnparseableException;
import com.take.my.time.model.Meeting;
import com.take.my.time.model.Opportunity;
import com.take.my.time.model.User;
import com.take.my.time.model.UserOpportunityHour;
import com.take.my.time.model.DTO.MeetingDTO;
import com.take.my.time.model.DTO.MeetingDTOOutput;
import com.take.my.time.model.DTO.OpportunityDTOInput;
import com.take.my.time.model.DTO.OpportunityDTOOutput;
import com.take.my.time.model.DTO.ParticipationDTO;
import com.take.my.time.repositories.MeetingRepository;
import com.take.my.time.services.MeetingService;
import com.take.my.time.services.OpportunityService;
import com.take.my.time.services.StateService;

@Service
public class MeetingServiceImpl implements MeetingService {


  private MeetingRepository meetingRepository;

  private StateService stateService;

  private ObjectMapper objectMapper = new ObjectMapper();


  private OpportunityService opportunityService;



  public MeetingServiceImpl(MeetingRepository pMeetingRepository, StateService pStateService,
      OpportunityService pOpportunityService) {
    meetingRepository = pMeetingRepository;
    stateService = pStateService;
    opportunityService = pOpportunityService;
  }

  @Override
  public Meeting addMeeting(Meeting meeting) {
    stateService.computeOpportunityState(meeting);
    return meetingRepository.save(meeting);
  }

  @Override
  public Optional<Meeting> getMeeting(String uuid) throws Exception {
    if (uuid.contains(">") || uuid.contains("Java")) {

      throw new Exception("illegal uuid");
    }

    Optional<Meeting> meeting = Optional.ofNullable(null);
    try {
      UUID mappedUUID = UUID.fromString(uuid);
      meeting = meetingRepository.findByGuid(mappedUUID);

    } catch (Exception e) {
      throw new Exception("illegal uuid");

    }
    return meeting;
  }

  @Override
  public List<Meeting> getAllMeeting() {
    return meetingRepository.findAll();
  }

  @Override
  public Meeting updateMeeting(ParticipationDTO updateDTO) throws Exception {


    Meeting updatedmeeting = null;

    try {

      UUID uuid = UUID.fromString(updateDTO.getGuid());
      Optional<Meeting> optionalMeeting = meetingRepository.findByGuid(uuid);

      if (optionalMeeting.isPresent()) {

        Meeting oldMeeting = optionalMeeting.get();

        User user = new User(updateDTO.getName());

        // si on a déjà le user avec le meme nom on ecrase fallait faire gaffe + penser a ajouter
        // les user cotés ihm pour voir qui a voté sinon on peut ecraser sans faire exprès
        if (oldMeeting.getUsers().stream().map(User::getName).collect(Collectors.toList())
            .contains(updateDTO.getName())) {

          deleteUserFromMeeting(oldMeeting, updateDTO.getName());


        } else {

          oldMeeting.getUsers().add(user);

        }

        opportunityService.updateMeetingOpportunity(oldMeeting, updateDTO.getOpportunities(), user);

        stateService.computeOpportunityState(oldMeeting);

        updatedmeeting = meetingRepository.save(oldMeeting);

      } else {
        throw new Exception("Invalid Event Code");
      }

    } catch (Exception e) {

      throw new Exception(e.getMessage());
    }

    return updatedmeeting;
  }

  @Override
  public Meeting mapMeeting(String pMeeting) throws IncorrectMeetingException {

 
    MeetingDTO meetingDto = new MeetingDTO();
    Meeting meeting = new Meeting();
    try {
      // DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      // objectMapper.setDateFormat(df);
      meetingDto = objectMapper.readValue(pMeeting, MeetingDTO.class);

      User user = new User(meetingDto.getAuthor().getName());

      meeting.getUsers().add(user);
      meeting.setAuthor(meetingDto.getAuthor());
      meeting.setTitle(meetingDto.getTitle());
      for (OpportunityDTOInput op : meetingDto.getOpportunities()) {
        Opportunity opp = new Opportunity();
        opp.setOpportunityDate(op.getDate());

        UserOpportunityHour userHour = new UserOpportunityHour(user, op.getHour());
        opp.getUsers().add(userHour);


        meeting.getOpportunities().add(opp);
      }

    } catch (Exception e) {
      throw new UnparseableException("Unparseable Payload");
    }

    return meeting;
  }

  @Override
  public MeetingDTOOutput mapToMeetingDTO(Meeting meeting) throws Exception {


    List<OpportunityDTOOutput> opportunitiesDTO =
        opportunityService.mapOpportunityToOpportunityDTO(meeting.getOpportunities());


    return mapMeetingDTO(meeting, opportunitiesDTO);
  }



  private MeetingDTOOutput mapMeetingDTO(Meeting meeting,
      List<OpportunityDTOOutput> opportunitiesDTO) {

    MeetingDTOOutput dto = new MeetingDTOOutput();
    dto.setGuid(meeting.getGuid().toString());
    dto.setTitle(meeting.getTitle());
    dto.setAuthor(meeting.getAuthor().getName());
    dto.setOpportunitiesOutput(opportunitiesDTO);

    return dto;
  }

  @Override
  @Transactional
  public ParticipationDTO mapToParticpationDTO(String updateDTO) {

    ParticipationDTO participation = null;
    try {
      participation = objectMapper.readValue(updateDTO, ParticipationDTO.class);
    } catch (Exception e) {
      throw new UnparseableException("Unparseable Payload");
    }


    return participation;
  }

  /**
   * delete user and compute label
   * 
   * @param meeting
   * @param username
   * @return Meeting
   */
  @Override
  @Transactional
  public Meeting deleteUserFromMeeting(Meeting meeting, String username) {

    for (Opportunity op : meeting.getOpportunities()) {
      op.getUsers().removeIf(userDetail -> userDetail.getUser().getName().equals(username));

      op.computeLabel();
    }

    return meeting;
  }


}
