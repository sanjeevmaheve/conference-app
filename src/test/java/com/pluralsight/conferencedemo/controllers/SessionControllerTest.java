package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.controllers.SessionController;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void listAllSessionsTest() throws Exception {

        when(sessionRepository.findAll()).thenReturn(
                Arrays.asList(new Session((long) 1, "Name1", "Description1", 45),
                        new Session((long) 2, "Name2", "Description2", 50)
                )
        );
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/sessions")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                // Compare first JSON value in the array and ignore the second one (i.e. put empty curly braces),
                // just to check how the assertion of array is done using the mocking framework.
                // Order does not matter unless set as strict.
                .andExpect(content().json("[{session_id:1,session_name:Name1,session_description:Description1,session_length:45},{}]"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    public void listOneSessionTest() throws Exception {
        when(sessionRepository.findAll()).thenReturn(
                Arrays.asList(new Session((long) 1, "Name1", "Description1", 45))
        );
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/sessions/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{session_id:1,session_name:Name1,session_description:Description1,session_length:45}]"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
