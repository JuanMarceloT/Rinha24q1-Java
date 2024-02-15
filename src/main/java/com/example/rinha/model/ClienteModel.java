package com.example.rinha.model;

import lombok.Getter;
import org.springframework.util.StreamUtils;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
public class ClienteModel {
    private UUID id;
    private int limite;
    private int saldo;
    private final List<ExtratoModel> list = new ArrayList<ExtratoModel>();



    public ClienteModel(ClienteDto cliente) {
        this.id = UUID.randomUUID();
        this.limite = cliente.limite();
        this.saldo = cliente.saldo();
    }

    private void SalvarExtrato (int valor, char tipo, String descricao){
        ExtratoModel ex = new ExtratoModel(valor,tipo,descricao, OffsetDateTime.now());
        if (list.size() >= 10) {
            list.removeFirst();
        }
        list.add(ex);
    }

    public boolean Credito(int valor, String descricao){
        saldo += valor;
        SalvarExtrato(valor, 'c', descricao);
        return true;
    }


    public boolean debito(int valor, String descricao){

        if(saldo - valor < -limite)
            return false;

        saldo -= valor;
        SalvarExtrato(valor, 'd', descricao);
        return true;
    }

    public int Subtrair_saldo(int valor){
        saldo -= valor;
        return saldo;
    }
    public int Incrementar_Saldo(int valor){
        saldo += valor;
        return saldo;
    }
}
