package com.surveySpringBoot.controller;

import com.surveySpringBoot.model.Question;
import com.surveySpringBoot.model.Survey;
import com.surveySpringBoot.repository.QuestionRepository;
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

public class QuestionController {

    @Autowired
    QuestionRepository repository;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = new ArrayList<Question>();

            repository.findAll().forEach(questions::add);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/surveyQ")
    public ResponseEntity<List<Question>> getSurveyQuestions() {
        try {
            List<Question> questions = new ArrayList<Question>();

            repository.findAll().forEach(questions::add);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/category-questions")
    public ResponseEntity<List<Question>> getCategoryQuestions(@RequestParam long id ) {
        try {
            List<Question> questions = new ArrayList<Question>();

            questions.addAll(repository.findByCategory_Id(id));

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
