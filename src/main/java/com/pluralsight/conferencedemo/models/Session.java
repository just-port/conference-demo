package com.pluralsight.conferencedemo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sessions")
public class Session {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long session_id;
  private String session_name;
  private String session_description;
  private Integer session_length;

  public Session() {
  }

  public Long getSession_id() {
    return session_id;
  }

  public void setSession_id(Long session_id) {
    this.session_id = session_id;
  }

  public String getSession_name() {
    return session_name;
  }

  public void setSession_name(String session_name) {
    this.session_name = session_name;
  }
}
