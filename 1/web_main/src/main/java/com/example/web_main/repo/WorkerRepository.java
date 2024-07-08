package com.example.web_main.repo;



import org.springframework.data.repository.CrudRepository;

import com.example.web_main.models.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    Iterable<Worker> findByFirstName(String FirstName);
    Iterable<Worker> findByFirstNameContainingIgnoreCase(String name);

}
