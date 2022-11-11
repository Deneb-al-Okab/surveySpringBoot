package com.surveySpringBoot.controller;

import com.surveySpringBoot.model.SubmittedSurvey;
import com.surveySpringBoot.repository.SubmittedSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class SubmittedSurveyController extends SubmittedSurvey {

    @Autowired
    SubmittedSurveyRepository repository;

    @PostMapping(
            value = "/submitted-survey",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createSubSurvey(@RequestBody SubmittedSurvey SubS) {
        System.out.println("prima del primo try");
        try {
            Optional<SubmittedSurvey> _SubS = repository.findByIdSurveyAndIdMail(SubS.getIdSurvey(), SubS.getIdMail());
            if (_SubS.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            SubmittedSurvey newSubS = repository.save(new SubmittedSurvey(SubS.getIdSurvey(), SubS.getIdMail()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}