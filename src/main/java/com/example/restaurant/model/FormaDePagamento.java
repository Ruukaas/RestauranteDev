package com.example.restaurant.model;

public class FormaDePagamento {
    private String nome;
    private int ID;
    
    public FormaDePagamento() {
    }

    public FormaDePagamento(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
}
