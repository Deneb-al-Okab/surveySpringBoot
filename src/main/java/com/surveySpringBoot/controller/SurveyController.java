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
            //System.out.println("Mail = " + mail);
            List<Survey> surveys = new ArrayList<Survey>();
            surveys.addAll(repository.SurveyToDo(mail, start, step));
            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            surveys.removeIf(s -> s.getEndingDate().before(Calendar.getInstance()));
            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/howManySurveysToDo")
    public ResponseEntity<Integer> howManyToDoSurveys(@RequestParam  String mail) {
        try {
            Integer howManyToDo = 0;
            howManyToDo = repository.countHowManyToDo(mail);
            return new ResponseEntity<>(howManyToDo, HttpStatus.OK);
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
            surveys.addAll(repository.SurveyDone(mail, start, step));
            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/howManySurveysDone")
    public ResponseEntity<Integer> howManyDoneSurveys(@RequestParam  String mail) {
        try {
            Integer howManyDone = 0;
            howManyDone = repository.countHowManyDone(mail);
            return new ResponseEntity<>(howManyDone, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/surveysCreated")
    public ResponseEntity<List<Survey>> getCreatedSurveys(
            @RequestParam(defaultValue = "0")  int start,
            @RequestParam(defaultValue = "10") int step,
            @RequestParam  String mail) {
        try {
            List<Survey> surveys = new ArrayList<Survey>();
            surveys.addAll(repository.findAllByIdMail(mail, start, step));
            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/howManySurveysCreated")
    public ResponseEntity<Integer> howManyCreatedSurveys(@RequestParam  String mail) {
        try {
            Integer howManyCreated = 0;
            howManyCreated = repository.countHowManyCreated(mail);
            return new ResponseEntity<>(howManyCreated, HttpStatus.OK);
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
            //System.out.println(sur.toString());
            Optional<Survey> _sur = repository.findById(sur.getId());
            if (_sur.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            if (sur.getEndingDate().before(sur.getPublishDate())){
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }

            Survey newSurvey = repository.save(new Survey(sur.getIdMail(),sur.getCategory(),sur.getName(),sur.getDescription(),sur.getPublishDate(),sur.getEndingDate()));
            return new ResponseEntity<>(newSurvey, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}