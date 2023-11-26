package com.example.MongoDBFirstPractice.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MongoDBFirstPractice.collections.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

	List<Person> findByFirstNameStartsWith(String name);

	//List<Person> findPersonByAgeBetween(Integer minAge, Integer maxAge);
	@Query(value = "{ 'age' : { $gt : ?0, $lt : ?1}}",
	       fields = "{addresses:  0}")
	 List<Person> findPersonByAgeBetween(Integer min, Integer max);

}
