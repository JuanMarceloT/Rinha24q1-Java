package com.example.rinha.model;

import java.time.OffsetDateTime;
import java.util.Date;

public record saldoModel(int total, OffsetDateTime data_extrato, int limite){}
