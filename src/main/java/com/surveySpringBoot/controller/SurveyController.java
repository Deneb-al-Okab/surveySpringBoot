package com.surveySpringBoot.controller;


import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.Survey;
import com.surveySpringBoot.model.User;
import com.surveySpringBoot.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/surveysToDo"
            )
    public ResponseEntity<List<Survey>> getToDoSurveys(
            @RequestParam(defaultValue = "0")  int start,
            @RequestParam(defaultValue = "10") int step,
            @RequestParam  String mail) {
        try {
            System.out.println("Mail = " + mail);
            List<Survey> surveys = new ArrayList<Survey>();
            repository.SurveyToDo(mail,start,step).forEach(surveys::add);
            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (Survey s: surveys) {
                if (s.getEndingDate().before(Calendar.getInstance())) {
                    surveys.remove(s);
                }
            }
            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/surveysDone")
    public ResponseEntity<List<Survey>> getDoneSurveys(
            @RequestParam(defaultValue = "0")  int start,
            @RequestParam(defaultValue = "10") int step,
            @RequestParam  String mail) {
        try {
            List<Survey> surveys = new ArrayList<Survey>();
            repository.SurveyDone(mail,start,step).forEach(surveys::add);
            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(
            value = "/createSurvey",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey sur) {
        try {
            System.out.println(sur.toString());
            Optional<Survey> _sur = repository.findById(sur.getId());
            if (_sur.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            Survey newSurvey = repository.save(new Survey(sur.getIdMail(),sur.getCategory(),sur.getName(),sur.getDescription(),sur.getPublishDate(),sur.getEndingDate()));
            return new ResponseEntity<>(newSurvey, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}