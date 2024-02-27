package com.example.rinha.controller;


import com.example.rinha.model.*;
import com.example.rinha.repositories.ClienteRepo;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class ClienteController {
    private final ClienteRepo DB;
    @Autowired
    public ClienteController(ClienteRepo db) {
        this.DB = db;
    }
    @PostMapping("/clientes/{id}/transacoes")
    public ResponseEntity<SaldoResponse> saldo(@PathVariable int id, @RequestBody transacoesDto trancoes) {
        ClienteModel cliente = DB.getById(id);
        boolean Approved = false;
        switch (trancoes.tipo()) {
            case 'c':
                Approved = cliente.Credito(trancoes.valor(),trancoes.descricao());
                break;
            case 'd':
                Approved = cliente.debito(trancoes.valor(),trancoes.descricao());
                break;
            default:
                break;
        }
        if (Approved) {
            DB.save(cliente);
            return ResponseEntity.ok().body(new SaldoResponse(cliente.getLimite(),cliente.getSaldo()));
        } else {
            return ResponseEntity.status(422).build();
        }
    }

    @GetMapping("/clientes/{id}/extrato")
    public ResponseEntity<ExtratoResponse> extrato(@PathVariable int id) {
        ClienteModel cliente = DB.getById(id);
        return ResponseEntity.ok().body(new ExtratoResponse(cliente.getSaldo(), cliente.getLimite(),cliente.getList()));
    }
}