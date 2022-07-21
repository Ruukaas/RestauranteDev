package com.example.restaurant.model;

public class Pedido {

    Cliente donoDoPedido;
    int donoDoPedidoID;
    Prato prato;
    FormaDePagamento formaDePagamento;
    String observacao;
    int ID;

    public Pedido() {
    }

    public Pedido(Cliente donoDoPedido, Prato prato, FormaDePagamento formaDePagamento, String observacao) {
        this.donoDoPedido = donoDoPedido;
        this.prato = prato;
        this.formaDePagamento = formaDePagamento;
        this.observacao = observacao;
    }

    public Cliente getDonoDoPedido() {
        return donoDoPedido;
    }

    public void setDonoDoPedido(Cliente donoDoPedido) {
        this.donoDoPedido = donoDoPedido;
    }

    public int getDonoDoPedidoID() {
        return donoDoPedidoID;
    }

    public void setDonoDoPedidoID(int donoDoPedidoID) {
        this.donoDoPedidoID = donoDoPedidoID;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }
}
