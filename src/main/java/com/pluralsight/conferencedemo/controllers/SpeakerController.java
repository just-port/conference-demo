package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

  private final SpeakerRepository speakerRepository;

  public SpeakerController(SpeakerRepository speakerRepository) {
    this.speakerRepository = speakerRepository;
  }

  @GetMapping
  public List<Speaker> list() {
    return speakerRepository.findAll();
  }

  @GetMapping
  @RequestMapping("{id}")
  public Speaker findById(@PathVariable Long id) {
    Optional<Speaker> opt;
    opt = speakerRepository.findById(id);
    if (opt.isEmpty()) {
      throw new NoSuchElementException("Speaker not found");
    }
    return opt.get();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Speaker create(@RequestBody final Speaker speaker) {
    return speakerRepository.saveAndFlush(speaker);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    // Also need to check for children records before deleting
    speakerRepository.deleteById(id);
  }

  @PutMapping("{id}")
  public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
    // TODO: add validation that all attributes are passed in, otherwise return a 400 bad payload
    Optional<Speaker> opt;
    opt = speakerRepository.findById(id);
    if (opt.isEmpty()) {
      throw new NoSuchElementException("Speaker not found");
    }
    var existingSpeaker = opt.get();
    BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
    return speakerRepository.saveAndFlush(existingSpeaker);
  }
}
