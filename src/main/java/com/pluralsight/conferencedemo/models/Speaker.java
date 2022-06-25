package com.pluralsight.conferencedemo.models.Speaker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Speakers")
public class Speaker implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long speaker_id;
  private String first_name;
  private String last_name;
  private String title;
  private String company;
  private String speaker_bio;

  public Speaker() {
  }
}
