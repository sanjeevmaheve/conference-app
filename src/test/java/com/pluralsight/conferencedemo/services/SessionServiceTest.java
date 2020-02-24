package com.pluralsight.conferencedemo.services;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessionServiceTest {

    @InjectMocks
    SessionService sessionService;

    @Mock
    SessionRepository sessionRepository;

    @Test
    public void retrieveAllSessionsTest() {
        when(sessionRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Session((long) 1, "Name1", "Description1", 45),
                        new Session((long) 2, "Name2", "Description2", 50)
                )
        );
        List<Session> sessions;
        sessions = sessionService.retrieveAllSessions();
        assertEquals(1, sessions.get(0).getSession_id());
        assertEquals(2, sessions.get(1).getSession_id());
    }
}
