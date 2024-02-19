package com.example.rinha.controller;


import com.example.rinha.model.ClienteDto;
import com.example.rinha.model.ClienteModel;
import com.example.rinha.model.saldoExtrato;
import com.example.rinha.model.transacoesDto;
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
    public ResponseEntity<Map<String, Object>> saldo(@PathVariable UUID id, @RequestBody transacoesDto trancoes) {
        String myEnvVariable = System.getenv("POSTGRES_PASSWORD");
		System.out.println("test");
        System.out.println("MY_ENV_VARIABLE value is: " + myEnvVariable);
        int valor = trancoes.valor();
        char tipo = trancoes.tipo();
        String descricao = trancoes.descricao();
    
        ClienteModel cliente = DB.getById(id);
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
            DB.save(cliente);
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
        ClienteModel cliente = DB.getById(id);

        OffsetDateTime dataExtrato = OffsetDateTime.now();
            Map<String, Object> response = new HashMap<>();
            response.put("saldo", new saldoExtrato(cliente.getSaldo(), dataExtrato.toString(), cliente.getLimite()));
            response.put("ultimas_transacoes", cliente.getList());
            return ResponseEntity.ok().body(response);

    }
}
