package com.take.my.time.services;

import java.util.List;
import java.util.Optional;

import com.take.my.time.exceptions.IncorrectMeetingException;
import com.take.my.time.model.Meeting;
import com.take.my.time.model.DTO.MeetingDTOOutput;
import com.take.my.time.model.DTO.ParticipationDTO;

public interface MeetingService {

  Meeting addMeeting(Meeting meeting);

  Optional<Meeting> getMeeting(String UUID) throws Exception;

  List<Meeting> getAllMeeting();

  Meeting updateMeeting(ParticipationDTO meeting) throws Exception;

  Meeting mapMeeting(String meeting) throws IncorrectMeetingException;

  MeetingDTOOutput mapToMeetingDTO(Meeting meeting) throws Exception;

  ParticipationDTO mapToParticpationDTO(String updateDTO);

  Meeting deleteUserFromMeeting(Meeting meeting, String username);
}
