package com.pluralsight.conferencedemo.services;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    public List<Speaker> retrieveAllSpeakers() {
        return speakerRepository.findAll();
    }

    public Speaker retrieveSpecificSpeaker(Long id) {
        return speakerRepository.getOne(id);
    }

    public Speaker save(Speaker session) {
        return speakerRepository.saveAndFlush(session);
    }

    public Speaker save(Long id, Speaker session) {
        // TODO: Add validation that all attributes are input mandatorily. Otherwise return BAD REQUEST (400)
        Speaker existingSpeaker = retrieveSpecificSpeaker(id);
        BeanUtils.copyProperties(session, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

    public void delete(Long id) {
        speakerRepository.deleteById(id);
    }
}
