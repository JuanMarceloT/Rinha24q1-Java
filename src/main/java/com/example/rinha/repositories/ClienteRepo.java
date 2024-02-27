package com.example.rinha.repositories;

import com.example.rinha.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepo extends JpaRepository<ClienteModel, Integer> {

}
