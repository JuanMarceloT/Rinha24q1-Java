package com.example.rinha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.util.StreamUtils;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Entity
@Data
public class ClienteModel {
    @jakarta.persistence.Id
    @Id
    private int id;
    private int limite;
    private int saldo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "join_id")
    private final List<ExtratoModel> list = new ArrayList<>();



    public ClienteModel(ClienteDto cliente) {
        this.limite = cliente.limite();
        this.saldo = cliente.saldo();
    }

    public ClienteModel() {

    }


    private void SalvarExtrato (int valor, char tipo, String descricao){
        ExtratoModel ex = new ExtratoModel(valor, tipo, descricao, OffsetDateTime.now());
        while(list.size() >= 10) {
            list.removeLast();
        }
        list.addFirst(ex);

    }

    public boolean Credito(int valor, String descricao){
        saldo += valor;
        SalvarExtrato(valor, 'c', descricao);
        return true;
    }

    public List<ExtratoModel> GetExtrato(){
        return this.list;
    }


    public boolean debito(int valor, String descricao){

        if(saldo - valor < -limite)
            return false;

        saldo -= valor;
        SalvarExtrato(valor, 'd', descricao);
        return true;
    }


}
