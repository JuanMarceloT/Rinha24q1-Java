package com.example.rinha.controller;


import com.example.rinha.model.ClienteDto;
import com.example.rinha.model.ClienteModel;
import com.example.rinha.model.saldoExtrato;
import com.example.rinha.model.transacoesDto;
import com.example.rinha.repositories.ClienteRepo;


import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ClienteController(ClienteRepo db) {
        DB = db;
        ClienteModel u = new ClienteModel(new ClienteDto(100000,0));

        ClienteModel d = new ClienteModel(new ClienteDto(80000,0));
        ClienteModel t = new ClienteModel(new ClienteDto(1000000,0));
        ClienteModel q = new ClienteModel(new ClienteDto(10000000,0));
        ClienteModel c = new ClienteModel(new ClienteDto(500000,0));
        DB.Create(u);
        System.out.println(u.getId());
        DB.Create(d);
        System.out.println(d.getId());
        DB.Create(t);
        System.out.println(t.getId());
        DB.Create(q);
        System.out.println(q.getId());
        DB.Create(c);
        System.out.println(c.getId());
    }
    @PostMapping("/clientes/{id}/saldo")
    public ResponseEntity<Map<String, Object>> saldo(@PathVariable UUID id, @RequestBody transacoesDto trancoes) {
        int valor = trancoes.valor();
        char tipo = trancoes.tipo();
        String descricao = trancoes.descricao();
        Optional<ClienteModel> clienteOptional = DB.getClienteById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClienteModel cliente = clienteOptional.get();
        boolean Approved = false;
        switch (tipo) {
            case 'c':
                Approved = cliente.Credito(valor, descricao);
                break;
            case 'd':
                Approved = cliente.debito(valor, descricao);
                break;
            default:
                break;
        }
        if (Approved) {
            Map<String, Object> response = new HashMap<>();
            response.put("limite", cliente.getLimite());
            response.put("saldo", cliente.getSaldo());
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(422).build();
        }
    }

    @GetMapping("/clientes/{id}/extrato")
    public ResponseEntity<Map<String, Object>> extrato(@PathVariable UUID id) {
        Optional<ClienteModel> clienteOptional = DB.getClienteById(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClienteModel cliente = clienteOptional.get();

        OffsetDateTime dataExtrato = OffsetDateTime.now();
            Map<String, Object> response = new HashMap<>();
            response.put("saldo", new saldoExtrato(cliente.getSaldo(), dataExtrato.toString(), cliente.getLimite()));
            response.put("ultimas_transacoes", cliente.getList());
            return ResponseEntity.ok().body(response);

    }
}