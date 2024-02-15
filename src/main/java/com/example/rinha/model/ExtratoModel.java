package com.example.rinha.model;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Date;

public record ExtratoModel (int valor, char tipo, String descricao, OffsetDateTime realizada_em){}

