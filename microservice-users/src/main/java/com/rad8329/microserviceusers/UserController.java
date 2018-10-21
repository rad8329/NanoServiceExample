package com.rad8329.microserviceusers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users/default", produces = "application/json;charset=UTF-8")
    @ResponseBody
    List<User> getDefault() {
        return getList();
    }

    @RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
    @ResponseBody
    List<User> getList() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", produces = "application/json;charset=UTF-8")
    @ResponseBody
    boolean addOne(@RequestBody User user) {
        return service.addOne(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    ResponseEntity<User> removeOne(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        Optional<User> user = service.removeOne(id);

        return user.map(u -> new ResponseEntity<>(u, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/users/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    ResponseEntity<User> getOne(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        Optional<User> user = service.getOne(id);

        return user.map(u -> new ResponseEntity<>(u, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    ResponseEntity<User> updateOne(@RequestBody User userData, @PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        Optional<User> user = service.updateOne(id, userData);

        return user.map(u -> new ResponseEntity<>(u, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }
}
