package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
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
@RequestMapping("/api/v1/sessions")
public class SessionsController {

  private final SessionRepository sessionRepository;

  public SessionsController(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @GetMapping
  public List<Session> list() {
    return sessionRepository.findAll();
  }

  @GetMapping
  @RequestMapping("{id}")
  public Session findById(@PathVariable Long id) {
    Optional<Session> opt;
    opt = sessionRepository.findById(id);
    if (opt.isEmpty()) {
      throw new NoSuchElementException("Session not found");
    }
    return opt.get();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Session create(@RequestBody final Session session) {
    return sessionRepository.saveAndFlush(session);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    // Also need to check for children records before deleting
    sessionRepository.deleteById(id);
  }

  @PutMapping("{id}")
  public Session update(@PathVariable Long id, @RequestBody Session session) {
    // TODO: add validation that all attributes are passed in, otherwise return a 400 bad payload
    Optional<Session> opt;
    opt = sessionRepository.findById(id);
    if (opt.isEmpty()) {
      throw new NoSuchElementException("Session not found");
    }
    var exisitingSession = opt.get();
    BeanUtils.copyProperties(session, exisitingSession, "session_id");
    return sessionRepository.saveAndFlush(exisitingSession);
  }
}
