package com.surveySpringBoot.controller;

import com.surveySpringBoot.model.SubmittedAnswer;
import com.surveySpringBoot.repository.SubmittedAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SubmittedAnswerController {

    @Autowired
    SubmittedAnswerRepository repository;

    @PostMapping(
            value = "/submitted-answer",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createSubmittedAnswer(@RequestBody SubmittedAnswer submitted_answer) {
        try {
            SubmittedAnswer newSubmittedAnswer = repository.save(new SubmittedAnswer(submitted_answer.getId_submitted_survey(), submitted_answer.getId_question_answer()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
