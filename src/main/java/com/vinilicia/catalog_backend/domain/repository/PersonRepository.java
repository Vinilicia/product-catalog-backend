package com.vinilicia.catalog_backend.domain.repository;

import com.vinilicia.catalog_backend.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
