package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.services.SpeakerService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SpeakerController.class)
public class SpeakerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SpeakerService speakerService;

    @Test
    public void listAllSpeakersTest() throws Exception {
        when(speakerService.retrieveAllSpeakers()).thenReturn(
                Arrays.asList(new Speaker((long) 1, "FirstName1", "LastName1", "title1", "company1", "speaker_bio1"),
                        new Speaker((long) 2, "FirstName2", "LastName2", "title2", "company2", "speaker_bio2")
                )
        );
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/speakers")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                // Compare second JSON value in the array and ignore the second one (i.e. put empty curly braces),
                // just to check how the assertion of array is done using the mocking framework.
                // Order does not matter unless set as strict.
                .andExpect(content().json("[{}, {speaker_id:2,first_name:FirstName2,last_name:LastName2,title:title2,company:company2,speaker_bio:speaker_bio2}]"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
