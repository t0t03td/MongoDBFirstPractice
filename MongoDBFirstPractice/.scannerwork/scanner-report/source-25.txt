package com.example.MongoDBFirstPractice.Services;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.MongoDBFirstPractice.collections.Person;

public interface PersonService {

    String save(Person person);

    List<Person> getPersonStartWith(String name);

    void delete(String id);

    List<Person> getByPersonAge(Integer minAge, Integer maxAge);
		

}
