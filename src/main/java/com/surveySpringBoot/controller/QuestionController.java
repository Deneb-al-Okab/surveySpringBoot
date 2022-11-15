package com.surveySpringBoot.controller;

import com.surveySpringBoot.model.Question;
import com.surveySpringBoot.model.QuestionAnswer;
import com.surveySpringBoot.model.Survey;
import com.surveySpringBoot.model.SurveyComposition;
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

    @PostMapping(
            value = "/createQuestion",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<HttpStatus> createQuestion(@RequestBody Question question) {
        try {
            //System.out.println(question.getQuestion()+" "+question.getId_category());
            Question newQuestion = repository.save(new Question(question.getQuestion(),question.getId_category()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
