package com.surveySpringBoot.controller;


import com.surveySpringBoot.model.*;
import com.surveySpringBoot.repository.QuestionAnswerRepository;
import com.surveySpringBoot.repository.QuestionRepository;
import com.surveySpringBoot.repository.SurveyCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class QuestAnsController {
    @Autowired
    QuestionAnswerRepository repository;
    @Autowired
    SurveyCompositionRepository repository2;


    @GetMapping("/readSurvey")
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

    @PostMapping(
            value = "/createQuestAns",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createQuestAns(@RequestBody List<QuestionAnswer> questAns,
                                                          @RequestParam Long id_survey) {
        try {
            for(QuestionAnswer q: questAns){
                QuestionAnswer newQA = repository.save(new QuestionAnswer(q.getId_question(), q.getId_answer()));
                repository2.save(new SurveyComposition(id_survey, newQA.getId()));
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
