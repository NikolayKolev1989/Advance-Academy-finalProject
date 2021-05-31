package com.example.library.repository;

import com.example.library.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByFirstName(String name);

    Optional<Client> findByLastName(String name);
}
