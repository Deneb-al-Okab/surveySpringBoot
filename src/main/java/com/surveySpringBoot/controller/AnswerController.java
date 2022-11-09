package com.surveySpringBoot.controller;

import com.google.gson.Gson;
import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.User;
import com.surveySpringBoot.repository.AnswerRepository;
import com.surveySpringBoot.tool.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class AnswerController {

    @Autowired
    AnswerRepository repository;

    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> getAllAnswers(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody Optional<String> sort
    ) {
        try {
            List<Sort.Order> orders = new ArrayList<>();

            if (sort.isPresent()) {
                String sorts = sort.get();
                Gson gson = new Gson();
                SortCriteria[] criteria = gson.fromJson(sorts, SortCriteria[].class);
                for(SortCriteria criteriaI : criteria) {
                    orders.add(new Sort.Order(criteriaI.getSortDirection(), criteriaI.getField()));
                }
            }
            else {
                orders.add(new Sort.Order(Sort.Direction.ASC, "answer"));
            }

            Pageable pageCurrent   = PageRequest.of(page, size, Sort.by(orders));
            Page<Answer> pageRecords = repository.findAll(pageCurrent);

            List<Answer> answers = pageRecords.getContent();

            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

