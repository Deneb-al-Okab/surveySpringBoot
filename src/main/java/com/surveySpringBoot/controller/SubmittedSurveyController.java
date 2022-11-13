package com.surveySpringBoot.controller;

import com.google.gson.Gson;
import com.surveySpringBoot.model.Category;
import com.surveySpringBoot.model.SubmittedSurvey;
import com.surveySpringBoot.model.Survey;
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
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class SubmittedSurveyController extends SubmittedSurvey {

    @Autowired
    SubmittedSurveyRepository repository;
    @GetMapping("/sub_survey")
    public ResponseEntity<List<SubmittedSurvey>> getAllSubmittedSurvey() {
        try {
            List<SubmittedSurvey> surveys = new ArrayList<SubmittedSurvey>();

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
    @GetMapping(value = "/get-sub-survey"
    )
    public ResponseEntity<SubmittedSurvey> getSubSurveys(
            @RequestParam long idSurvey,
            @RequestParam  String idMail) {
        try {
            Optional<SubmittedSurvey> data = repository.findByIdSurveyAndIdMail(idSurvey, idMail);
            if (data.isPresent()) {
                return new ResponseEntity<>(data.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/submitted-survey",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createSubSurvey(@RequestBody SubmittedSurvey SubS) {
/*        System.out.println("prima del primo try");
        Gson gson = new Gson();
        System.out.println(gson.toJson(SubS));*/
        try {
            Optional<SubmittedSurvey> _SubS = repository.findByIdSurveyAndIdMail(SubS.getIdSurvey(), SubS.getIdMail());
            if (_SubS.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            SubmittedSurvey newSubS = repository.save(new SubmittedSurvey(SubS.getIdSurvey(), SubS.getIdMail()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}