package com.surveySpringBoot.controller;

import com.surveySpringBoot.repository.CategoryRepository;
import com.surveySpringBoot.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")

public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = new ArrayList<>();

            categories.addAll(repository.findAll());

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        Optional<Category> data = repository.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<List<Category>> getCategoryByNameContaining(@PathVariable("name") String name) {
        List<Category> categories = new ArrayList<>();

        repository.findByNameContaining(name).forEach(categories::add);

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}