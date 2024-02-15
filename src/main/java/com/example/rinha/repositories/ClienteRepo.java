package com.example.rinha.repositories;

import com.example.rinha.model.ClienteDto;
import com.example.rinha.model.ClienteModel;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepo {
    public ClienteModel Create(ClienteModel cliente);
    public Optional<ClienteModel> getClienteById(UUID id);
}
