package com.surveySpringBoot.controller;


import com.surveySpringBoot.model.Survey;
import com.surveySpringBoot.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class SurveyController {

    @Autowired
    SurveyRepository repository;

    @GetMapping("/surveys")
    public ResponseEntity<List<Survey>> getAllSurveys() {
        try {
        List<Survey> surveys = new ArrayList<Survey>();

//        repository.findAll().forEach(surveys::add);
        surveys.addAll(repository.findAll());

        if (surveys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }
        catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}