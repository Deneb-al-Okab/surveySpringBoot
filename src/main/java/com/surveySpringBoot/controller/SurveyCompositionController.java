package com.surveySpringBoot.controller;


import com.surveySpringBoot.model.SurveyComposition;
import com.surveySpringBoot.model.User;
import com.surveySpringBoot.repository.SurveyCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

public class SurveyCompositionController {

    @Autowired
    SurveyCompositionRepository repository;

    @GetMapping("/survey-composition")
    public ResponseEntity<List<SurveyComposition>> getSurveyComposition() {
        try {
            List<SurveyComposition> survey_composition = new ArrayList<>();

            survey_composition.addAll(repository.findAll());


            if (survey_composition.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(survey_composition, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/survey-composition",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> surveyComposition(@RequestBody SurveyComposition sc) {
        try {
            //System.out.println(user.toString());
            //Optional<User> _user = repository.findByMail(user.getMail());

            /*if (_user.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }*/

            SurveyComposition newSC = repository.save(new SurveyComposition(sc.getId_survey(), sc.getId_question_answer()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
