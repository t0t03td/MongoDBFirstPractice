package com.example.MongoDBFirstPractice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.MongoDBFirstPractice.Repository.PersonRepository;
import com.example.MongoDBFirstPractice.collections.Person;


@Service
public class PersonServiceImpl implements PersonService {
	 
	 private final PersonRepository personRepository;

	    @Autowired
	    public PersonServiceImpl(PersonRepository personRepository) {
	        this.personRepository = personRepository;
	    }
	    
	    @Override
	    public String save(Person person) {
	        return personRepository.save(person).getPersonId();
	    }
        
	    @Override
	    public List<Person> getPersonStartWith(String name) {
	        return personRepository.findByFirstNameStartsWith(name);
	    }

	    @Override
	    public void delete(String id) {
	        personRepository.deleteById(id);
	    }

	    @Override
	    public List<Person> getByPersonAge(Integer minAge, Integer maxAge) {
	        return personRepository.findPersonByAgeBetween(minAge,maxAge);
	    }
}
