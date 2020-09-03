package com.take.my.time.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.take.my.time.model.Meeting;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {


  Optional<Meeting> findByGuid(UUID guid);

  List<Meeting> findAll();


}

