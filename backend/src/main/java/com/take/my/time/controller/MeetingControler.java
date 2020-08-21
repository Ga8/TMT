package com.take.my.time.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.take.my.time.exceptions.IncorrectMeetingException;
import com.take.my.time.model.Meeting;
import com.take.my.time.model.DTO.MeetingDTOOutput;
import com.take.my.time.model.DTO.ParticipationDTO;
import com.take.my.time.services.MeetingService;

@RestController
@RequestMapping("api/")
public class MeetingControler {

  private MeetingService meetingService;


  public MeetingControler(MeetingService meetingService) {
    this.meetingService = meetingService;

  }


  @Transactional
  @PostMapping(path = "/addmeeting", consumes = "application/json", produces = "application/json")
  public ResponseEntity addMeeting(@RequestBody String pMeeting) {

    Meeting meeting = new Meeting();
    ResponseEntity<?> response = null;

    try {
      isValid(pMeeting);
      meeting = meetingService.mapMeeting(pMeeting);
      Meeting meetingReturn = meetingService.addMeeting(meeting);

      response = ResponseEntity.ok().body(meetingReturn.getGuid());
    } catch (Exception e) {
      response = ResponseEntity.badRequest().body(e.getMessage());
    }
    return response;
  }


  @Transactional
  @RequestMapping(path = "/getmeeting", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity getMeeting(@RequestParam("guid") String guid) {


    Meeting meeting = new Meeting();
    Optional<Meeting> meetingOptional;
    ResponseEntity<?> response = null;
    try {

      isValid(guid);
      meetingOptional = meetingService.getMeeting(guid);


      if (meetingOptional.isPresent()) {
        meeting = meetingOptional.get();

        MeetingDTOOutput meetingDTOOutput = meetingService.mapToMeetingDTO(meeting);


        response = ResponseEntity.ok().body(meetingDTOOutput);
      } else {
        response = ResponseEntity.notFound().build();
      }

    } catch (Exception e) {
      response = ResponseEntity.badRequest().body(e.getMessage());
    }

    return response;
  }

  /**
   * 
   * @param guid
   * @return
   */

  private Boolean isValid(String pMeeting) throws Exception {

    if (pMeeting.contains("!") || pMeeting.contains("<") || pMeeting.contains(")")
        || pMeeting.length() > 1000) {
      throw new IncorrectMeetingException();
    }

    return true;
  }

  /**
   * 
   * @return
   */
  @Transactional
  @RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity getMeeting() {

    List<Meeting> meetingOptional = meetingService.getAllMeeting();

    ResponseEntity<?> response = ResponseEntity.ok().body(meetingOptional);

    return response;
  }


  /**
   * 
   * @param meeting
   * @return
   */
  @Transactional
  @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> updateMeeting(@RequestBody String updateDTO) {

    ResponseEntity<?> response = null;

    MeetingDTOOutput meetingReturn = null;
    try {

      isValid(updateDTO);

      ParticipationDTO participation = meetingService.mapToParticpationDTO(updateDTO);

      Meeting meeting = meetingService.updateMeeting(participation);

      meetingReturn = meetingService.mapToMeetingDTO(meeting);

      response = ResponseEntity.ok().body(meetingReturn);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return response;
  }


}
