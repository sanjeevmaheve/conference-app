package com.pluralsight.conferencedemo.services;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> retrieveAllSessions() {
        return sessionRepository.findAll();
    }

    public Session retrieveSpecificSession(Long id) {
        return sessionRepository.getOne(id);
    }

    public Session save(Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    public Session save(Long id, Session session) {
        // TODO: Add validation that all attributes are input mandatorily. Otherwise return BAD REQUEST (400)
        Session existingSession = retrieveSpecificSession(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
}
