package com.example.rinha.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Data
@Transactional
@JsonIgnoreProperties(value = { "id" })
public class ExtratoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int valor;
    private char tipo;
    private String descricao;
    private OffsetDateTime realizadaEm;


    public ExtratoModel() {
    }

    public ExtratoModel(int valor, char tipo, String descricao, OffsetDateTime realizadaEm) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }
}