package com.take.my.time.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class EntityWithUUID {
  @Type(type = "pg-uuid")
  @Column(name = "guid")
  private UUID guid;

  public EntityWithUUID() {
    this.guid = UUID.randomUUID();
  }

  public UUID getGuid() {
    return guid;
  }

  public void setUuid(UUID guid) {
    this.guid = guid;
  }
}
