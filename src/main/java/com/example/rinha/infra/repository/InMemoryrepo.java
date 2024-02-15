package com.example.rinha.infra.repository;

import com.example.rinha.model.ClienteDto;
import com.example.rinha.model.ClienteModel;
import com.example.rinha.repositories.ClienteRepo;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class InMemoryrepo implements ClienteRepo {
    private final Map<UUID, ClienteModel> table;

    public InMemoryrepo() {
        this.table = new ConcurrentHashMap<>();
    }
    @Override
    public ClienteModel Create(ClienteModel cliente) {
        this.table.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public Optional<ClienteModel> getClienteById(UUID id) {
        return Optional.ofNullable(this.table.get(id));
    }


}
