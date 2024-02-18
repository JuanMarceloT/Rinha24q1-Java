package com.example.rinha.repositories;

import com.example.rinha.model.ClienteDto;
import com.example.rinha.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface ClienteRepo extends Repository<ClienteModel, UUID> {

    ClienteModel save(ClienteModel cliente);

    Optional<ClienteModel> findById(UUID id);
}