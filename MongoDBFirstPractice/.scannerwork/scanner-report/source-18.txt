package com.example.MongoDBFirstPractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MongoDBFirstPractice.Services.PersonService;
import com.example.MongoDBFirstPractice.collections.Person;

import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/person")

public class PersonController {

    @Autowired
    private PersonService personService;
    
    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    /*
    @PostMapping
    public String save(@RequestBody Person person) {
        return personService.save(person);
    } */
    
    @GetMapping
    public List<Person> getPersonStartWith(@RequestParam("name") String name) {
        return personService.getPersonStartWith(name);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }
    
    @GetMapping("/age")
    public List<Person> getByPersonAge(@RequestParam Integer minAge,
                                       @RequestParam Integer maxAge) {
        return personService.getByPersonAge(minAge,maxAge);
    }
      
    
}
