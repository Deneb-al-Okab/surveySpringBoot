package com.surveySpringBoot.controller;
import com.google.gson.Gson;
import com.surveySpringBoot.model.User;
import com.surveySpringBoot.repository.UserRepository;
import com.surveySpringBoot.tool.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("surveySpringBoot/api")


public class UserController {
    @Autowired
    UserRepository repository;

    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<User> checkUser(@RequestBody User user) {
        try {
            Optional<User> _user = repository.findByMail(user.getMail());

            HttpStatus status = HttpStatus.UNAUTHORIZED;
            String adm ="error";
            if (_user.isPresent() && _user.get().getPass().equals(user.getPass())) {
                User usr = new User(_user.get().getMail(), _user.get().getPass(), _user.get().getIsAdmin());
                status = HttpStatus.OK;
                return new ResponseEntity<>(usr, status);
            }
            return new ResponseEntity<>(null, status);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/sign-up",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            System.out.println(user.toString());
            Optional<User> _user = repository.findByMail(user.getMail());

            if (_user.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            User newUser = repository.save(new User(user.getMail(), user.getPass(), user.getIsAdmin()));
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/update-adm",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<User> updateAdm(@RequestBody User user) {
        try {
            //System.out.println(user.toString());
            Optional<User> _user = repository.findByMail(user.getMail());

            if (_user.isPresent()) {
                System.out.println(_user.toString());
            }


            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
