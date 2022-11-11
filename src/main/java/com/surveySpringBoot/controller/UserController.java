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
            //System.out.println(user.toString());
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
    @DeleteMapping("/users/{mail}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("mail") String mail) {
        try {
            repository.deleteById(mail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{mail}")
    public ResponseEntity<User> updateUser(@PathVariable("mail") String mail, @RequestBody User user) {
        try {
            Optional<User> data = repository.findById(mail);

            if (data.isPresent()) {
                User _user = data.get();

                if (user.getPass() != null) _user.setPass(user.getPass());
                _user.setIsAdmin(user.getIsAdmin());

                return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users-page")
    public ResponseEntity<List<User>> getAllUsersPage(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody Optional<String> sort
    ) {
        try {
            List<Order> orders = new ArrayList<>();

            if (sort.isPresent()) {
                String sorts = sort.get();
                Gson gson = new Gson();
                SortCriteria[] criteria = gson.fromJson(sorts, SortCriteria[].class);
                for(SortCriteria criteriaI : criteria) {
                    orders.add(new Order(criteriaI.getSortDirection(), criteriaI.getField()));
                }
            }

            Pageable pageCurrent   = PageRequest.of(page, size, Sort.by(orders));
            Page<User> pageRecords = repository.findAll(pageCurrent);

            List<User> users = pageRecords.getContent();

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
