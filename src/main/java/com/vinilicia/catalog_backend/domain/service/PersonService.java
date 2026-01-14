package com.vinilicia.catalog_backend.domain.service;

import com.vinilicia.catalog_backend.domain.model.Person;
import com.vinilicia.catalog_backend.domain.model.Product;
import com.vinilicia.catalog_backend.domain.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void createPerson(String name) {
        personRepository.save(new Person(name));
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        return personRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Transactional
    public void updatePerson(Long id, String name) {
        Person person = personRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        if (name != null) {
            person.rename(name);
        }
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = personRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        for (Product product : person.getProducts()) {
            product.removeOwner(person);
        }

        personRepository.delete(person);
    }
}
