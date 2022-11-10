package com.surveySpringBoot.controller;


import com.surveySpringBoot.model.Category;
import com.surveySpringBoot.model.QuestionAnswer;
import com.surveySpringBoot.repository.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class QuestAnsController {
    @Autowired
    QuestionAnswerRepository repository;


    @GetMapping(
            value ="/readSurvey",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<QuestionAnswer>> getSurveyQuestAns(@RequestParam int id) {
        try {
            List<QuestionAnswer> qA = new ArrayList<QuestionAnswer>();

            qA.addAll(repository.getQuestionAnswerByIdSurvey(id));

            if (qA.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(qA, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(
            value ="/questAns",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<QuestionAnswer>> getAllQA() {
        try {
            List<QuestionAnswer> qA = new ArrayList<>();

            qA.addAll(repository.findAll());

            if (qA.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(qA, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
