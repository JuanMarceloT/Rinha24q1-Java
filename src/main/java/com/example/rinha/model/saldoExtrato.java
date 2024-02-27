package com.example.rinha.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Setter
@Getter
public class saldoExtrato {
    private int total;
    private String data_extrato;
    private int limite;

    public saldoExtrato(int total, int limite, String data) {
        this.total = total;
        this.data_extrato = data;
        this.limite = limite;
    }


}
