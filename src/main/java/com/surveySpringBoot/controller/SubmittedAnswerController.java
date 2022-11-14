package com.surveySpringBoot.controller;

import com.surveySpringBoot.model.SubmittedAnswer;
import com.surveySpringBoot.model.SubmittedSurvey;
import com.surveySpringBoot.repository.SubmittedAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class SubmittedAnswerController {

    @Autowired
    SubmittedAnswerRepository repository;
    @GetMapping("/sub_answ")
    public ResponseEntity<List<SubmittedAnswer>> getAllSubAnswers() {
        try {
            List<SubmittedAnswer> answers = new ArrayList<SubmittedAnswer>();

            answers.addAll(repository.findAll());

            if (answers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(answers, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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

    @PostMapping(
            value = "/submitted-answers",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createSubmittedAnswer(@RequestBody List<SubmittedAnswer> submitted_answers) {
        try {
            for (SubmittedAnswer answ: submitted_answers
                 ) {
                 repository.save(new SubmittedAnswer(answ.getId_submitted_survey(), answ.getId_question_answer()));
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
