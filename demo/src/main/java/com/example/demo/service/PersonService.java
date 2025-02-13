package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    /**
     * Create a new person.
     *
     * @param person The person object to save.
     * @return The saved person.
     */
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Retrieve all persons.
     *
     * @return List of all persons.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieve a person by ID.
     *
     * @param id The person ID.
     * @return The person object if found.
     */
    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    /**
     * Update an existing person.
     *
     * @param id The person ID.
     * @param updatedPerson The updated person object.
     * @return The updated person if found.
     */
    public Person updatePerson(Integer id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    existingPerson.setName(updatedPerson.getName());
                    existingPerson.setFamily(updatedPerson.getFamily());
                    existingPerson.setAccount(updatedPerson.getAccount());
                    existingPerson.setAddress(updatedPerson.getAddress());
                    return personRepository.save(existingPerson);
                })
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
    }

    /**
     * Delete a person by ID.
     *
     * @param id The person ID to delete.
     */
    public void deletePerson(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with ID: " + id);
        }
        personRepository.deleteById(id);
    }
}

